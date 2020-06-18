package io.devaux.poifinder.datasource;

import io.devaux.poifinder.model.Point;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Very simple datasource that reads its data points from a resource file
 * <p>
 * This is required in the exercise, but this should definitely migrate to a real database ;)
 */
@Component
public class FileDataSource {
    private static final String DATA_FILENAME = "/data";

    private final List<Point> dataPoints;

    public FileDataSource() {
        // We assume data won't change for the application lifetime, so we read it once on startup and keep it in memory
        dataPoints = this.readPointsFromDataFile();
    }

    public List<Point> getPoints() {
        return dataPoints;
    }

    private List<Point> readPointsFromDataFile() {
        InputStream is = FileDataSource.class.getResourceAsStream(DATA_FILENAME);
        if (is == null) {
            throw new IllegalStateException("Could not find data file");
        }
        try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            return reader.lines()
                    .skip(1) // Skip header line
                    .map(this::mapLineToPoint)
                    .collect(toList());
        } catch (IOException e) {
            throw new IllegalStateException("Could not read data file", e);
        }
    }

    private Point mapLineToPoint(String line) {
        String[] data = line.split(" ");
        return new Point(
                data[0],
                Double.valueOf(data[1]),
                Double.valueOf(data[2])
        );
    }
}
