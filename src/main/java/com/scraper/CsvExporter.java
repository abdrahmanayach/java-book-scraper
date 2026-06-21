package com.scraper;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvExporter {

    public void export(List<Book> books, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Path.of(filename)))) {
            writer.println("title,price,rating,availability,url");
            for (Book book : books) {
                writer.println(toCsvRow(book));
            }
        }
    }

    private String toCsvRow(Book book) {
        return String.join(",",
                escape(book.title()),
                escape(book.price()),
                String.valueOf(book.rating()),
                escape(book.availability()),
                escape(book.url()));
    }

    private String escape(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
