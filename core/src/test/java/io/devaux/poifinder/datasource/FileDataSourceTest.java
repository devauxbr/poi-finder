package io.devaux.poifinder.datasource;

import io.devaux.poifinder.model.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileDataSourceTest {
    private final FileDataSource underTest = new FileDataSource();

    @Test
    void shouldGetPointsFromDatasource() {
        List<Point> points = underTest.getPoints();

        // We could test for the exact data values, but this would prevent from any data change...
        // Instead we test the plumbing by checking that the datasource returns at least one point, and that each point
        // contains and ID and both coordinates :
        assertThat(points).isNotEmpty();
        for (Point point : points) {
            assertThat(point.getId()).isNotBlank();
            assertThat(point.getLongitude()).isNotNull();
            assertThat(point.getLatitude()).isNotNull();
        }
    }
}
