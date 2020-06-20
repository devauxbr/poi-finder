package io.devaux.poifinder.rest;

import io.devaux.poifinder.model.Number;
import io.devaux.poifinder.model.ValueHolder;
import io.devaux.poifinder.model.Zone;
import io.devaux.poifinder.service.DensestZoneFinder;
import io.devaux.poifinder.service.ZonePoiFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PoiFinderController {

    private final ZonePoiFinder zonePoiFinder;
    private final DensestZoneFinder densestZoneFinder;

    @Autowired
    public PoiFinderController(ZonePoiFinder zonePoiFinder, DensestZoneFinder densestZoneFinder) {
        this.zonePoiFinder = zonePoiFinder;
        this.densestZoneFinder = densestZoneFinder;
    }

    @GetMapping("/nbpoi")
    public ValueHolder get(
            @RequestParam(value = "min_lat", required = false) Double minLatitude,
            @RequestParam(value = "min_lon", required = false) Double minLongitude,
            @RequestParam(value = "max_lat", required = false) Double maxLatitude,
            @RequestParam(value = "max_lon", required = false) Double maxLongitude
    ) {
        return zonePoiFinder.findPoiIn(Zone.builder()
                .minLatitude(minLatitude)
                .minLongitude(minLongitude)
                .maxLatitude(maxLatitude)
                .maxLongitude(maxLongitude)
                .build()
        );
    }

    @GetMapping("/densest/{n}")
    public List<Zone> get(
            @PathVariable(value = "n", required = false) int n
    ) {
        return densestZoneFinder.findDensestZones(new Number(n));
    }
}
