package kr.co._29cm.homework.support;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import kr.co._29cm.homework.dto.request.ProductRequest;

public class ProductInitializer {

    private final CsvReader csvReader;

    public ProductInitializer() {
        this.csvReader = new CsvReader();
    }

    public List<ProductRequest> init() {
        final List<String[]> lines = csvReader.read("/items_homework.csv");
        lines.remove(0);
        return generateProducts(lines);
    }

    private List<ProductRequest> generateProducts(List<String[]> lines) {
        return lines.stream()
                .map(this::toProductRequest)
                .collect(Collectors.toList());
    }

    private ProductRequest toProductRequest(final String[] line) {
        final Long id = Long.parseLong(line[0]);
        final String name = String.join(",", Arrays.copyOfRange(line, 1, line.length - 2));
        final int price = Integer.parseInt(line[line.length - 2]);
        final int stock = Integer.parseInt(line[line.length - 1]);
        return new ProductRequest(id, name, price, stock);
    }
}
