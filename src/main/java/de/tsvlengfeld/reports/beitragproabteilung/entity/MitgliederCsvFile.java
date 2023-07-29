package de.tsvlengfeld.reports.beitragproabteilung.entity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public final class MitgliederCsvFile {

    private String filePath;

    public MitgliederCsvFile(String filePath) {
        this.filePath = filePath;
        checkFileExistence();
    }

    private void checkFileExistence() {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("File does not exist or is a directory: " + filePath);
        }
    }

    public Map<String, String[]> readContent() throws IOException {
        Map<String, String[]> dataMap = new HashMap<>();

        try (CSVParser parser = CSVParser.parse(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : parser) {
                String profileId = record.get("Profil-ID");
                String abteilungen = record.get("Abteilungen");
                String teams = record.get("Teams");
                String beitraege = record.get("Beiträge");
                String[] rowData = new String[]{
                        abteilungen,
                        teams,
                        beitraege
                };

                dataMap.put(profileId, rowData);
            }
        }

        return dataMap;
    }
    
}
