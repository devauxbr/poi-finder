package io.devaux.poifinder.service;

import io.devaux.poifinder.datasource.FileDataSource;
import io.devaux.poifinder.model.Point;
import io.devaux.poifinder.model.ValueHolder;
import io.devaux.poifinder.model.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service that finds the number of Point Of Interests in a certain zone
 */
@Service
@RequiredArgsConstructor
public class ZonePoiFinder {

    private final FileDataSource fileDataSource;

    public ValueHolder findPoiIn(Zone zone) {
        // Note that iterating over the data like this is highly inefficient, ideally this should be pre-indexed in a
        // geolocation search engine (ElasticSearch would be a good opensource candidate with its geoshape capabilites)
        long result = fileDataSource.getPoints().stream()
                .filter(point -> isPointInZone(point, zone))
                .count();
        return new ValueHolder(result);
    }

    private boolean isPointInZone(Point point, Zone zone) {
        return isPointUnderMaxLatitude(point, zone) &&
                isPointUnderMaxLongitude(point, zone) &&
                isPointOverMinLatitude(point, zone) &&
                isPointOverMinLongitude(point, zone);
    }

    private boolean isPointUnderMaxLatitude(Point point, Zone zone) {
        return zone.getMaxLatitude() == null || zone.getMaxLatitude() >= point.getLatitude();
    }

    private boolean isPointUnderMaxLongitude(Point point, Zone zone) {
        return zone.getMaxLongitude() == null || zone.getMaxLongitude() >= point.getLongitude();
    }

    private boolean isPointOverMinLatitude(Point point, Zone zone) {
        return zone.getMinLatitude() == null || zone.getMinLatitude() <= point.getLatitude();
    }

    private boolean isPointOverMinLongitude(Point point, Zone zone) {
        return zone.getMinLongitude() == null || zone.getMinLongitude() <= point.getLongitude();
    }
}
