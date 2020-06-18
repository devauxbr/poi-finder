package io.devaux.poifinder.service;

import io.devaux.poifinder.datasource.FileDataSource;
import io.devaux.poifinder.model.ValueHolder;
import io.devaux.poifinder.model.Zone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ZonePoiFinderTest {

    private final ZonePoiFinder underTest = new ZonePoiFinder(new FileDataSource());

    @Test
    void shouldFindTwo() {
        // This test follows the first exercise question. It depends on the data, and WILL break if these data change
        Zone zone = Zone.builder()
                .minLatitude(6.5)
                .minLongitude(-7d)
                .build();

        ValueHolder poiNumber = underTest.findPoiIn(zone);

        assertThat(poiNumber).isEqualTo(2);
    }
}