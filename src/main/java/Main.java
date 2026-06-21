import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookScraper scraper = new BookScraper();
        CsvExporter exporter = new CsvExporter();

        try {
            List<Book> books = scraper.scrapeAll();
            exporter.export(books, "books.csv");
            System.out.println("\nScraped " + books.size() + " books.");
        } catch (IOException e) {
            System.out.println("Failed to scrape " + e.getMessage());
        }
    }
}
