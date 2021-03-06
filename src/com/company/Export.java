package com.company;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by st on 01.03.2016.
 */
class Export {
    public static int MAX = 1000;
    public static String SEPARATOR = ";";
    public static String LINE_SEPARATOR = "\n";
    public static String EXCEL_FILE = "phones.csv";

    public static void exportRecords(String[] names, String[] numbers, boolean[] ids, int[] years, int[] months, int[] days) throws IOException {
        FileUtils.writeStringToFile(new File(Export.EXCEL_FILE), Export.createOneLine(ids, names, numbers, years, months, days));
    }

    public static String createTheHead() {
        return "sep=;" + LINE_SEPARATOR + "ID" + SEPARATOR + "Имя абонента" +  SEPARATOR + "Телефон" + SEPARATOR +
                "Дата рождения" + LINE_SEPARATOR;
    }

    public static String createOneLine(boolean[] ids, String[] names, String[] numbers, int[] years, int[] months, int[] days) {
        String line = createTheHead();
        for (int i = 1; i < ids.length; i++) {
            if (ids[i]) {
                line += i + SEPARATOR + names[i] + SEPARATOR + " + 375 " + numbers[i] + SEPARATOR + years[i] + "-";
                if(months[i] < 10) {
                    line += "0";
                }
                line += months[i] + "-";
                if(days[i] < 10) {
                    line += "0";
                }
                line += days[i] + LINE_SEPARATOR;
            }
        }
        return line;
    }
}
