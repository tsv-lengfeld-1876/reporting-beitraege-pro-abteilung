package de.tsvlengfeld.reports.beitragproabteilung.entity;

public enum BeitragsArt {
    
    BEITRAG,
    EINMALIGE_GEBUEHR,
    UNDEFINED;

    public static BeitragsArt findBeitragsArt(String value) {
        if ("Beitrag".equalsIgnoreCase(value)) {
            return BeitragsArt.BEITRAG;
        }
        else if ("Einmalige Gebühr".equalsIgnoreCase(value)) {
            return BeitragsArt.EINMALIGE_GEBUEHR;
        }
        else {
            return BeitragsArt.UNDEFINED;
        }
       }
}
