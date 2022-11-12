package kr.co._29cm.homework.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final String CSV_DIVIDER = ",";

    public static List<String[]> read(String fileName) {
        final File file = findFilePath(fileName);
        try (final FileReader fileReader = new FileReader(file);
             final BufferedReader reader = new BufferedReader(fileReader)) {
            return generateFileContents(reader);
        } catch (IOException exception) {
            throw new IllegalArgumentException("올바르지 않은 파일명입니다.");
        }
    }

    private static File findFilePath(String fileName) {
        final URL resource = CsvReader.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("올바르지 않은 파일명입니다.");
        }

        return new File(resource.getFile());
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
