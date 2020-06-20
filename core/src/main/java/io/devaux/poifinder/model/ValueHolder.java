package io.devaux.poifinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Very simple bean aiming for JSON serialization
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueHolder {
    private long value;
}
