package de.tsvlengfeld.reports.beitragproabteilung;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class BeitragProAbteilungMain implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello " + args[0]);
        BeitragProAbteilungReportGenerator generator = new BeitragProAbteilungReportGenerator();
        generator.generate("src/test/resources/rechnungen.csv", "src/test/resources/rechnungen-pro-abteilung.csv");
       return 0;
    }
    
}
