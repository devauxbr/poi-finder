package io.devaux.poifinder.rest;

import io.devaux.poifinder.model.ValueHolder;
import io.devaux.poifinder.model.Zone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PoiFinderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*
    These tests validate the answers to both questions from the WEB exercise
    They are dependent on data and will break if data change
     */

    @Test
    void shouldGetPoiNumber() {
        Map<String, Double> params = newHashMap();
        params.put("min_lat", 6.5);
        params.put("min_lon", -7d);

        ValueHolder result = this.restTemplate.getForObject(
                "http://localhost:" + port + "/nbpoi?min_lat={min_lat}&min_lon={min_lon}",
                ValueHolder.class, params);

        assertThat(result).isNotNull();
        assertThat(result.getValue()).isEqualTo(2);
    }

    @Test
    void shouldGetDensestZones() {
        int number = 2;

        List<Zone> results = this.restTemplate
                .exchange("http://localhost:" + port + "/densest/" + number, GET, null,
                        new ParameterizedTypeReference<List<Zone>>() {})
                .getBody();

        // Validate expected results from exercise question 2. This WILL break if data changes :
        // FIXME : this is duplicated from core module. We should create a test-helper module and share that in both places :
        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(number);
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