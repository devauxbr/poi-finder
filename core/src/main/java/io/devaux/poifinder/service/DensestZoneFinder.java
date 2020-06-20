package io.devaux.poifinder.service;

import io.devaux.poifinder.datasource.FileDataSource;
import io.devaux.poifinder.model.Point;
import io.devaux.poifinder.model.Zone;
import io.devaux.poifinder.model.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Service that finds the n densest zones
 */
@Service
public class DensestZoneFinder {

    private final FileDataSource fileDataSource;

    @Autowired
    public DensestZoneFinder(
            FileDataSource fileDataSource
    ) {
        this.fileDataSource = fileDataSource;
    }

    public List<Zone> findDensestZones(Number number) {
        Map<Zone, Integer> zonesByPointsNumber = fileDataSource.getPoints().stream()
                .map(this::getPointZone)
                // Gather each zone (as key) with the number of points it contains (as value)
                // Lombok generates the Zone "hashCode" method for us so that 2 zones with the same values are
                // considered the same object and, as a result, the same key in the final hash map :
                .collect(toMap(zone -> zone, zone -> 1, Integer::sum));

        return zonesByPointsNumber.entrySet().stream()
                .sorted(this::compareZoneDensity)
                .limit(number.getN())
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    /**
     * Compute the zone in which the given point resides in
     */
    private Zone getPointZone(Point point) {
        return Zone.builder()
                .minLatitude(getLowerCoordinate(point.getLatitude()))
                .maxLatitude(getHigherCoordinate(point.getLatitude()))
                .minLongitude(getLowerCoordinate(point.getLongitude()))
                .maxLongitude(getHigherCoordinate(point.getLongitude()))
                .build();
    }

    /**
     * gets the lower coordinate deciding which logic to apply depending on the positiveness of the coordinate
     */
    private double getLowerCoordinate(double coordinate) {
        if (coordinate >= 0) return getAbsoluteLowerCoordinate(coordinate);
        else return - getAbsoluteHigherCoordinate(abs(coordinate));
    }

    /**
     * gets the higher coordinate deciding which logic to apply depending on the positiveness of the coordinate
     */
    private double getHigherCoordinate(double coordinate) {
        if (coordinate >= 0) return getAbsoluteHigherCoordinate(coordinate);
        else return - getAbsoluteLowerCoordinate(abs(coordinate));
    }

    /**
     * Compute the zone absolute lower coordinate from the point coordinate
     */
    private double getAbsoluteLowerCoordinate(double coordinate) {
        double truncated = floor(coordinate);
        double modulo = coordinate % 1;
        if (modulo > 0.5) return truncated + 0.5;
        return truncated;
    }

    /**
     * Compute the zone higher coordinate from the point coordinate
     */
    private double getAbsoluteHigherCoordinate(double coordinate) {
        double truncated = floor(coordinate);
        double modulo = coordinate % 1;
        if (modulo > 0.5) return truncated + 1;
        return truncated + 0.5;
    }

    private int compareZoneDensity(Map.Entry<Zone, Integer> o1, Map.Entry<Zone, Integer> o2) {
        return o2.getValue() - o1.getValue();
    }
}
