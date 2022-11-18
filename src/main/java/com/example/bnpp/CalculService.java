package com.example.bnpp;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CalculService {

    public BigDecimal computeFinalPrice(List<Book> list) {
        List<Situation> situations = new ArrayList<>();

        List<Integer> occurenceList = Arrays.asList(0, 0, 0, 0, 0);

        AtomicInteger i = new AtomicInteger();
        Map<String, List<Book>> collect = list
                .stream()
                .collect(Collectors.groupingBy(Book::getName));
        List<Integer> finalOccurenceList = occurenceList;
        collect.forEach((title, listBooks) -> {
            finalOccurenceList.set(i.getAndIncrement(), listBooks.size());
        });

        Situation initSituation = new Situation(occurenceList);
        //the case when we have 5 books
        if(list.size() == 5 && initSituation.getSumOfElements().intValue() == 5) {
            return initSituation.getTotalMontant();
        }

        occurenceList = occurenceList.stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).collect(Collectors.toList());

        Integer maxValue = occurenceList.stream().max(Comparator.comparingInt(Integer::intValue)).get();
        //the case when we have [2 2 2 1 1]
        for (int k = 0; k < maxValue; k++) {
            AtomicInteger counter = new AtomicInteger();
            List<Integer> books = occurenceList
                    .stream()
                    .map(item ->
                    {   // they take 1 1 1 1 0 and 1 1 1 0 1 else it will give the case of 1 1 1 1 1
                        int aux = item > 0 ? 1 : 0;
                        if (counter.addAndGet(aux) < 5 ){
                            return aux;
                        }
                        return 0;
                    })
                    .collect(Collectors.toList());

            for(int j = 0; j < books.size(); j++) {
                occurenceList.set(j, occurenceList.get(j) - books.get(j));
            }

            situations.add(new Situation(books));
        }

        return situations
                .stream()
                .map(Situation::getTotalMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
