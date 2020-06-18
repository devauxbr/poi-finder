package io.devaux.poifinder.datasource;

import io.devaux.poifinder.model.Point;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Very simple datasource that reads its data from a resource file
 *
 * This is required in the exercise, but this should definitely migrate to a real database IMHO
 */
@Component
public class FileDataSource {
    public FileDataSource() {
        // We assume data won't change for application lifetime, so we read it once on startup and keep it in memory :
        this.getClass().getResourceAsStream("data");
    }

    public List<Point> getPoints() {
        return null;
    }
}
