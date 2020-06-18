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
        // Instead we check that the datasource returns at least one point containing both coordinates
        assertThat(points).isNotEmpty();
        Point firstPoint = points.get(0);
        assertThat(firstPoint.getId()).isNotBlank();
        assertThat(firstPoint.getLongitude()).isNotNull();
        assertThat(firstPoint.getLatitude()).isNotNull();
    }
}
