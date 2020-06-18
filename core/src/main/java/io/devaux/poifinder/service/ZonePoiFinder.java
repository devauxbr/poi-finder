package io.devaux.poifinder.service;

import io.devaux.poifinder.datasource.FileDataSource;
import io.devaux.poifinder.model.ValueHolder;
import io.devaux.poifinder.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that finds the number of Point Of Interests in a certain zone
 */
@Service
public class ZonePoiFinder {

    private final FileDataSource fileDataSource;

    @Autowired
    public ZonePoiFinder(
            FileDataSource fileDataSource
    ) {
        this.fileDataSource = fileDataSource;
    }

    public ValueHolder findPoiIn(Zone zone) {
        return null;
    }
}
