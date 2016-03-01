package com.company;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created by st on 01.03.2016.
 */
public class Export {
    public static int MAX = 1000;
    public static String SEPARATOR = ";";
    public static String LINE_SEPARATOR = "/r/n";
    public static String EXCEL_FILE = "ex.csv";

    public static void exportRecords(String[] names, String[] numbers, boolean[] ids, int[] years, int[] months, int[] days) {
        FileUtils.writeStringToFile(new File(Export.EXCEL_FILE), Export.createOneLine(names, numbers, ids, years, months, days));
    }

    public static void createTheHead() {

    }

    public static void createOneLine() {
        String line = Main.NAMES + SEPARATOR + Main.NUMBERS + SEPARATOR + Main.YEARS + SEPARATOR + Main.MONTHS + SEPARATOR + Main.DAYS
    }

    public static void genericLines() {
        createOneLine();
    }
}
