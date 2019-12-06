package com.vn.rbk.util;

import com.vn.rbk.domain.chotKQ;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CVSUtil {

    private static final char DEFAULT_SEPARATOR = ',';

    static void writeCSV() {
        try {
            String csvFile = "/Users/mkyong/csv/developer.csv";
            FileWriter writer = new FileWriter(csvFile);

            ArrayList<chotKQ> chotKQList = new ArrayList<chotKQ>();

            //for header
            writeLine(writer, Arrays.asList("Name", "Salary", "Age"));

            for (chotKQ d : chotKQList) {

                List<String> list = new ArrayList<String>();

                writeLine(writer, list);
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }
}
