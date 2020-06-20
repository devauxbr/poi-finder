package io.devaux.poifinder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class PoiFinderCliApplicationTest {

    // Setup inspired from https://stackoverflow.com/questions/1119385
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /*
    These tests validate the answers to both questions from the exercise
    They are HIGHLY dependent on JSON serialization configuration (line break, tabs, etc.) and will easily break !
     */

    @Test
    void shouldGetPoiNumber() {
        PoiFinderCliApplication.main(new String[]{"--nbpoi", "{\"min_lat\": 6.5, \"min_lon\": -7}"});
        String result = outContent.toString();

        assertThat(errContent.toString()).isEmpty();
        assertThat(result).isEqualTo("{\"value\":2}\n");
    }

    @Test
    void shouldGetDensestZones() {
        PoiFinderCliApplication.main(new String[]{"--densest", "{\"n\": 2}"});
        String result = outContent.toString();

        assertThat(errContent.toString()).isEmpty();
        assertThat(result).isEqualTo("[{\"min_lat\":-2.5,\"min_lon\":38.0,\"max_lat\":-2.0,\"max_lon\":38.5},{\"min_lat\":6.5,\"min_lon\":-7.0,\"max_lat\":7.0,\"max_lon\":-6.5}]\n");
    }
}
