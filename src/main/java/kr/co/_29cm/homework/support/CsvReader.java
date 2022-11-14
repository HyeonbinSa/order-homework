package kr.co._29cm.homework.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final String CSV_DIVIDER = ",";

    public List<String[]> read(String fileName) {
        try (final InputStream inputStream = getClass().getResourceAsStream(fileName);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return generateFileContents(reader);
        } catch (IOException | NullPointerException e) {
            throw new IllegalArgumentException("올바르지 않은 파일명입니다.");
        }
    }

    private static List<String[]> generateFileContents(BufferedReader reader) throws IOException {
        final List<String[]> fileContents = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            final String[] lineItems = line.split(CSV_DIVIDER);
            fileContents.add(lineItems);
        }

        return fileContents;
    }
}
