package io.devaux.poifinder.service;

import io.devaux.poifinder.datasource.FileDataSource;
import io.devaux.poifinder.model.Zone;
import io.devaux.poifinder.model.Number;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DensestZoneFinderTest {

    private final DensestZoneFinder underTest = new DensestZoneFinder(new FileDataSource());

    @Test
    void shouldFindTwoZonesForDensityTwo() {
        Number number = new Number(2);

        List<Zone> results = underTest.findDensestZones(number);

        // Validate expected results from exercise question 2. This WILL break if data changes :
        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(number.getN());
        assertZoneEquals(results.get(0), Zone.builder()
                .minLatitude(-2.5)
                .maxLatitude(-2d)
                .minLongitude(38d)
                .maxLongitude(38.5)
                .build());
        assertZoneEquals(results.get(1), Zone.builder()
                .minLatitude(6.5)
                .maxLatitude(7d)
                .minLongitude(-7d)
                .maxLongitude(-6.5)
                .build());
    }

    private void assertZoneEquals(Zone test, Zone expected) {
        assertThat(test.getMinLatitude()).isEqualTo(expected.getMinLatitude());
        assertThat(test.getMinLongitude()).isEqualTo(expected.getMinLongitude());
        assertThat(test.getMaxLatitude()).isEqualTo(expected.getMaxLatitude());
        assertThat(test.getMaxLongitude()).isEqualTo(expected.getMaxLongitude());
    }
}