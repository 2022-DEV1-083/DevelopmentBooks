package com.example.bnpp;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Situation {
    private List<Integer> books = Arrays.asList(0,0,0,0,0);

    public Situation(List<Integer> books) {
        this.books = books;
    }

    public BigDecimal getSumOfElements() {
        return books.stream().map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public BigDecimal getTotalMontant() {
        BigDecimal total = BigDecimal.ZERO;
        int sum = books.stream().reduce(0, Integer::sum);
        switch (sum) {
            case 1 : total = total.add(BigDecimal.valueOf(50));break;
            case 2 : total = total.add(BigDecimal.valueOf(2 * 50 * 0.95));break;
            case 3 : total = total.add(BigDecimal.valueOf(3 * 50 * 0.90));break;
            case 4 : total = total.add(BigDecimal.valueOf(4 * 50 * 0.80));break;
            case 5 : total = total.add(BigDecimal.valueOf(5 * 50 * 0.75));break;
            default:
                break;
        }
        return total;
    }
}