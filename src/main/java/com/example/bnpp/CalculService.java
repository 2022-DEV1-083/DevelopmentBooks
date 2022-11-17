package com.example.bnpp;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CalculService {
    private List<Integer> books = Arrays.asList(0,0,0,0,0);

    public BigDecimal computeFinalPrice(List<Book> list) {

        List<Integer> occurenceList = Arrays.asList(0,0,0,0,0);

        AtomicInteger i = new AtomicInteger();
        Map<String, List<Book>> collect = list
                .stream()
                .collect(Collectors.groupingBy(Book::getName));
        List<Integer> finalOccurenceList = occurenceList;
        collect.forEach((title, listBooks) -> {
            finalOccurenceList.set(i.getAndIncrement(), listBooks.size());
        });

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
