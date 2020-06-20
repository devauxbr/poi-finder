package io.devaux.poifinder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakawait.spring.boot.picocli.autoconfigure.ExitStatus;
import com.kakawait.spring.boot.picocli.autoconfigure.HelpAwarePicocliCommand;
import io.devaux.poifinder.model.Number;
import io.devaux.poifinder.model.Zone;
import io.devaux.poifinder.service.DensestZoneFinder;
import io.devaux.poifinder.service.ZonePoiFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static com.google.common.base.Strings.isNullOrEmpty;

@SpringBootApplication
public class PoiFinderCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoiFinderCliApplication.class, args);
    }

    @Component
    @Command
    @RequiredArgsConstructor
    public static class MainCommand extends HelpAwarePicocliCommand {
        @Option(names = {"--nbpoi"}, description = "Calculer le nombre de POIs d'une zone")
        String nbpoi;

        @Option(names = {"--densest"}, description = "Trouver les n zones les plus denses")
        String densest;

        private final ObjectMapper mapper = new ObjectMapper();

        private final ZonePoiFinder zonePoiFinder;
        private final DensestZoneFinder densestZoneFinder;

        @Override
        public ExitStatus call() {
            try {
                Object result;
                if (!isNullOrEmpty(nbpoi)) {
                    result = zonePoiFinder.findPoiIn(mapper.readValue(nbpoi, Zone.class));
                } else if (!isNullOrEmpty(densest)) {
                    result = densestZoneFinder.findDensestZones(mapper.readValue(densest, Number.class));
                } else {
                    System.err.println("Nothing to do !");
                    return ExitStatus.TERMINATION;
                }

                System.out.println(mapper.writeValueAsString(result));
                return ExitStatus.OK;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ExitStatus.TERMINATION;
        }
    }
}
