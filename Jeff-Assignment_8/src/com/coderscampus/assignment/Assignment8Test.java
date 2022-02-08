package com.coderscampus.assignment;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;



public class Assignment8Test {
    @Test
	public void AssignmentTest() {
        Assignment8 assignment = new Assignment8();

        List<Integer> allNumerics = Collections.synchronizedList(new ArrayList<>(1000));

        ExecutorService run = Executors.newCachedThreadPool();

        List<CompletableFuture<Void>> jobs = new ArrayList<>(1000);

        for (int i = 0; i < 1000; i++) {
            CompletableFuture<Void> job = CompletableFuture.supplyAsync(() -> assignment.getNumbers(), run)
                    .thenAccept(numeric -> allNumerics.addAll(numeric));
            jobs.add(job);
        }

        while (jobs.stream().filter(CompletableFuture::isDone).count() < 1000)

        {
        }

        System.out.println("Done!");
        System.out.println(allNumerics.size() + " = 1,000,000");


       
        Map<Integer, Integer> display = allNumerics.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.summingInt(p -> 1)));
        System.out.println(display);

    }
}