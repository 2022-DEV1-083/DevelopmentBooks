package com.example.bnpp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class BookController {
    final double PRICES = 50.0;
    private final CalculService calculService;

    public BookController(CalculService calculService) {
        this.calculService = calculService;
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal addBooks(@RequestBody BooksRequest request) {

    return this.calculService.computeFinalPrice(request.getBooks());

    }

}
