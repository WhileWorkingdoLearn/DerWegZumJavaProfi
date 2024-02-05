package org.der.weg.zum.java.profi.ModernConcurrency;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelData {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final Path exampleFIle = Paths.get("src/main/resources/Test/Example.txt");

        final CompletableFuture<List<String>> contents = CompletableFuture.supplyAsync(extractWordsFromFile(exampleFIle));

        contents.thenAccept(text -> System.out.println("Initial: " +text));

        final CompletableFuture<List<String>> filtered1 = contents.thenApplyAsync(removeIgnorableWords());

        final CompletableFuture<List<String>> filtered2 = contents.thenApplyAsync(removeShortWords());
        
        final CompletableFuture<List<String>> result = filtered1.thenCombine(filtered2,calcIntersection() );

        System.out.println("result: " + result.get());

    }

    private static Function<List<String>,List<String>> removeShortWords() {
        final Predicate<String> isShortWord = word -> word.length() <= 3;

        return input -> input.stream().filter(isShortWord.negate()).collect(Collectors.toList());
    }

    private static Function<List<String>,List<String>> removeIgnorableWords() {
        final var wordsToIgnore = Arrays.asList("this","This","text");
        final Predicate<String> isIgnorable = wordsToIgnore::contains;

        return input -> input.stream().filter(isIgnorable.negate()).collect(Collectors.toList());
    }

    private static BiFunction<? super List<String>,? super List<String>,? extends List<String>> calcIntersection(){
        return (list1,list2) -> {list1.retainAll(list2); return list1;};
    }

    public static Supplier<List<String>> extractWordsFromFile(final Path inputfile){
            return ()-> {
                try{
                    final List<String> lines = Files.readAllLines(inputfile);
                    final Stream<String> words = lines.stream().flatMap(line -> Stream.of(line.split(" ")));

                    final Stream<String> mapped = words.map(removePunctationMarks());
                    final Stream<String> sorted = mapped.sorted(String.CASE_INSENSITIVE_ORDER);

                    return sorted.collect(Collectors.toList());

                }catch (final Exception e){
                    return Collections.emptyList();
                }
            };
    }

    private static Function<String,String> removePunctationMarks() {
       return s -> {
            if(s.endsWith(".") || s.endsWith(":") || s.endsWith("!")){
                return s.substring(0,s.length()-1);
            }
            return s;
        };
    }
}
