package kr.co._29cm.homework.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CsvReaderTest {

    private CsvReader csvReader = new CsvReader();

    @DisplayName("CSV 파일을 읽어 List 형태로 반환한다.")
    @Test
    void read() {
        // when
        List<String[]> read = csvReader.read("/items.csv");

        // then
        assertEquals(read.size(), 6);
    }

    @DisplayName("파일명이 존재하지 않을 경우 예외를 반환한다.")
    @Test
    void read_WhenNonExistFile() {
        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> csvReader.read("/item.csv"));
    }
}
