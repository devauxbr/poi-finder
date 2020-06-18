package io.devaux.poifinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private String id;
    private Double latitude;
    private Double longitude;
}
