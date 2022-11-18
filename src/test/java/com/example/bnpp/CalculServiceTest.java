package com.example.bnpp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class CalculServiceTest {

    private CalculService calculService;

    public static Stream<Arguments> getDataSet() {
        return Stream.of(
                Arguments.of("OneCopy", BigDecimal.valueOf(50), Collections.singletonList(new Book(1, "book1"))),
                Arguments.of("TwoDifferentBooks", BigDecimal.valueOf(95.0), Arrays.asList(new Book(1, "book1"), new Book(2, "book2"))),
                Arguments.of("TwoTheSameBook", BigDecimal.valueOf(100), Arrays.asList(new Book(1, "book1"), new Book(1, "book1"))),
                Arguments.of("3DifferentBooks", BigDecimal.valueOf(135.0), Arrays.asList(new Book(1, "book1"), new Book(2, "book2"), new Book(3, "book3"))),
                Arguments.of("4DifferentBooks", BigDecimal.valueOf(160.0), Arrays.asList(new Book(1, "book1"), new Book(2, "book2"), new Book(3, "book3"), new Book(4, "book4"))),
                Arguments.of("5DifferentBooks", BigDecimal.valueOf(187.5), Arrays.asList(new Book(1, "book1"), new Book(2, "book2"), new Book(3, "book3"), new Book(4, "book4"), new Book(5, "book5"))),
                Arguments.of("4DifferentBooksAnd4DifferentBooks", BigDecimal.valueOf(320.0), Arrays.asList(new Book(1, "book1"), new Book(2, "book2"), new Book(3, "book3"),new Book(1, "book1"), new Book(2, "book2"), new Book(3, "book3"), new Book(4, "book4"), new Book(5, "book5"))),
                Arguments.of("2DifferentBooksAndOneSameCopy", BigDecimal.valueOf(145.0), Arrays.asList(new Book(1, "book1"), new Book(1, "book1"), new Book(2, "book2"))),
                Arguments.of("2TheSameBookAndTwoDifferent", BigDecimal.valueOf(185.0), Arrays.asList(new Book(1, "book1"), new Book(1, "book1"), new Book(2, "book2"), new Book(3, "book3")))

        );
    }

    @BeforeEach
    void init() {
        this.calculService = new CalculService();

    }

    @ParameterizedTest
    @MethodSource("getDataSet")
    void test1(String description, BigDecimal expected, List<Book> input) {
        assertEquals(expected, this.calculService.computeFinalPrice(input));
    }

}