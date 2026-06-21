package com.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookScraper {
    private static final String BASE_URL = "https://books.toscrape.com/catalogue/";
    private static final Map<String, Integer> RATINGS = Map.of(
            "One", 1, "Two", 2, "Three", 3, "Four", 4, "Five", 5);

    public List<Book> scrapePage(int pageNumber) throws IOException {
        String url = BASE_URL + "page-" + pageNumber + ".html";
        Document doc = fetchPage(url);
        return parseBooks(doc);
    }

    public List<Book> scrapeAll() throws IOException {
        List<Book> allBooks = new ArrayList<>();
        String url = "https://books.toscrape.com/catalogue/page-1.html";
        int page = 1;

        while(url != null) {
            Document doc = fetchPage(url);
            allBooks.addAll(parseBooks(doc));
            System.out.println("Page " + page + " — " + allBooks.size() + " books so far");

            Element next = doc.selectFirst("li.next > a");
            url = next == null ? null : next.attr("abs:href");

            if (url != null) {
                sleep(200);
                page++;
            }
        }

        return allBooks;
    }

    private Document fetchPage(String url) throws IOException {
        return Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10_000).get();
    }

    private List<Book> parseBooks(Document doc) {
        Elements cards = doc.select("article.product_pod");
        List<Book> books = new ArrayList<>();
        for (Element card : cards) {
            books.add(parseBook(card));
        }
        return books;
    }

    private Book parseBook(Element card) {
        String title = card.selectFirst("h3 > a").attr("title");
        String price = card.selectFirst(".price_color").text();
        int rating = parseRating(card);
        String availability = card.selectFirst(".instock.availability").text().trim();
        String url = card.selectFirst("h3 > a").attr("abs:href");
        return new Book(title, price, rating, availability, url);
    }

    private int parseRating(Element card) {
        String[] classes = card.selectFirst("p.star-rating").className().split(" ");
        return RATINGS.getOrDefault(classes[1], 0);
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
