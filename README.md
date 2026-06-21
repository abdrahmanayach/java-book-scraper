# Book Scraper

A Java web scraper built with [Jsoup](https://jsoup.org/) that extracts book data from [books.toscrape.com](https://books.toscrape.com/) and exports it to CSV.

## Features

* Scrapes all ~1000 books across every catalogue page
* Extracts title, price, rating, availability, and URL
* Exports results to a clean CSV file

## Tech Stack

Java 21 · Jsoup · Maven

## Getting Started

Clone the repository and move into it:

```bash
git clone https://github.com/abdrahmanayach/java-book-scraper.git
cd java-book-scraper
```

Run with Maven:

```bash
mvn compile exec:java
```

Results are written to `books.csv`.

## Structure

```
src/main/java/com/scraper
├── Main.java            # Entry point
├── Book.java            # Data model
├── BookScraper.java     # Fetching + parsing logic
└── CsvExporter.java     # CSV export
```
