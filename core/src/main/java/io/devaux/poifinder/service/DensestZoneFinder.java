package io.devaux.poifinder.service;

import io.devaux.poifinder.datasource.FileDataSource;
import io.devaux.poifinder.model.Zone;
import io.devaux.poifinder.model.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }
}
