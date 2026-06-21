package com.scraper;

public record Book(
        String title,
        String price,
        int rating,
        String availability,
        String url
) {}
