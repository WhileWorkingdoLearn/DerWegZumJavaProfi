package org.der.weg.zum.java.profi.ModernConcurrency;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class WordPublisher implements Flow.Publisher<String> {

    private final String word;
    private final List<Path> paths;

    private final SubmissionPublisher<String> publisher;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public WordPublisher(final String word, List<Path> inputFiles) {
        this.word =  word;
        this.paths = Collections.unmodifiableList(inputFiles);
        this.publisher = new SubmissionPublisher<>();
    }

    public void performSearch() {
        for(final Path path: paths){
            executorService.execute(()-> {
                final Stream<String> occurences = findWord(word,path);
                occurences.forEach(line -> publisher.submit("files: " + path + " : " + line));
            });
        }
    }

    private Stream<String> findWord(final String wordsToSearch,final Path path)  {
        try{
            final Charset utf8 = StandardCharsets.UTF_8;
            final List<String> lines = Files.readAllLines(path,utf8);
            return lines.stream().filter(line -> line.contains(wordsToSearch));
        } catch (final IOException ie){
            return Stream.of();
        }
    }

    public void terminate() {
        publisher.close();
        executorService.shutdown();
    }

    @Override
    public void subscribe(Flow.Subscriber<? super String> subscriber) {
        publisher.subscribe(subscriber);
    }
}
