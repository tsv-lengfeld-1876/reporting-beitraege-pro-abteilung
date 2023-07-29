package de.tsvlengfeld.reports.beitragproabteilung.entity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public final class RechnungenCsvFile {
    
    private String filePath;

    public RechnungenCsvFile(String filePath) {
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

        int beitragCounter = 0;
        int nonBeitragCounter = 0;
        try (CSVParser parser = CSVParser.parse(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : parser) {
                String profileId = record.get("Profil-ID");
                String beschreibung = record.get("Beschreibung");
                BeitragsArt art = BeitragsArt.findBeitragsArt(record.get("Art"));
                String bruttobetrag = record.get("Bruttobetrag");

                if (art.equals(BeitragsArt.BEITRAG)) {
                    beitragCounter++;
                    String abteilung = extractAbteilung(beschreibung);
                    String[] rowData = new String[]{
                        profileId,
                        abteilung,
                        bruttobetrag
                     };

                    dataMap.put(String.valueOf(beitragCounter), rowData);
                }
                else {
                    nonBeitragCounter++;
                }
             }
        }
        System.out.println("Beitrage= " + beitragCounter + " Andere= " + nonBeitragCounter);

        return dataMap;
    }

    private String extractAbteilung(String value) {
        // Pattern for "Name: Breitensport - Beitragsfrei - aktiv"
        Pattern pattern = Pattern.compile("^(.*?):\\s*(.*?)(?:-|$)(.*?)(?:-|$)(.*?)(?:-|$)");
        Matcher matcher = pattern.matcher(value);

        if (matcher.matches()) {
            return matcher.group(2); // = Abteilung
        }
        else {
            return null;
        }
    }
}
