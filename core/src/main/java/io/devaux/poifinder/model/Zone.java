package io.devaux.poifinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zone {
    @JsonProperty("min_lat")
    private Double minLatitude;

    @JsonProperty("min_lon")
    private Double minLongitude;

    @JsonProperty("max_lat")
    private Double maxLatitude;

    @JsonProperty("max_lon")
    private Double maxLongitude;
}
