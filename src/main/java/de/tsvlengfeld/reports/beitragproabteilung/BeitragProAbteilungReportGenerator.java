package de.tsvlengfeld.reports.beitragproabteilung;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import de.tsvlengfeld.reports.beitragproabteilung.entity.RechnungenCsvFile;

public final class BeitragProAbteilungReportGenerator {
    
    public void generate(String sourceFilePath, String targetFilePath) {

        RechnungenCsvFile rechnungenFile = new RechnungenCsvFile(sourceFilePath);
        try {
            Map<String, String[]> data = rechnungenFile.readContent();
            writeCsv(data, targetFilePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeCsv(Map<String, String[]> dataMap, String outputPath) throws IOException {
        int counter = 0;
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(outputPath), CSVFormat.DEFAULT.withHeader("profileid", "abteilung", "betrag"))) {
            for (Map.Entry<String, String[]> entry : dataMap.entrySet()) {
                String profileId = entry.getKey();
                String[] rowData1 = entry.getValue();
                csvPrinter.printRecord(rowData1[0], rowData1[1], rowData1[2]);
                counter++;
             }
        }
        System.out.println("Counter= " + counter);
    }
}
