package org.der.weg.zum.java.profi.ModernConcurrency;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestFlow {
    public static void main(String[] args) throws InterruptedException {
        final WordPublisher finder = new WordPublisher("private",getInputFiles());

        finder.subscribe(new WordSubscriber());
        finder.subscribe(new FirstSubscriber(System.err::println));
        finder.performSearch();

        TimeUnit.SECONDS.sleep(2);
        finder.terminate();
    }

    private static List<Path> getInputFiles() {
        final String srcPath = "src/main/resources/";

        return List.of(Paths.get(srcPath + "reactiveStreams/WordPublisher.java"),Paths.get(srcPath + "reactiveStreams/WordSubscriber.java"));

    }


}
