package io.devaux.poifinder;

import com.kakawait.spring.boot.picocli.autoconfigure.ExitStatus;
import com.kakawait.spring.boot.picocli.autoconfigure.HelpAwarePicocliCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@SpringBootApplication
public class PoiFinderCliApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoiFinderCliApplication.class, args);
    }

    @Component
    @Command
    static class MainCommand extends HelpAwarePicocliCommand {
        @Option(names = {"--nbpoi"}, description = "Calculer le nombre de POIs d'une zone")
        String nbpoi;

        @Option(names = {"--densest"}, description = "Calculer le nombre de POIs d'une zone")
        String densest;

        @Override
        public ExitStatus call() {
            if (false) {
                System.out.println("0.1.0");
                return ExitStatus.TERMINATION;
            }
            return ExitStatus.OK;
        }
    }
}
