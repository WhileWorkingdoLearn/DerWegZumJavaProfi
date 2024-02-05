package org.der.weg.zum.java.profi.AdvancedJavaTopics.HttpApi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestHttp {

    private static void readOraclePageJdk11() throws URISyntaxException, IOException,InterruptedException {
        final URI uri = new URI("https://www.oracle.com/index.html");
        final HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        final HttpResponse.BodyHandler<String> asString = HttpResponse.BodyHandlers.ofString();

        final HttpClient httpClient = HttpClient.newHttpClient();

        final HttpResponse<String> response = httpClient.send(request,asString);

        printResponseInfo(response);
    }

    private static void printResponseInfo(final HttpResponse<String> response) {
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println("Header: " + response.headers().map());
    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
       // readOraclePageJdk11();
        downloadASync();

    }

    private static void downloadSync() throws URISyntaxException, IOException, InterruptedException {

        var uri = new URI("https://www.oracle.com/index.html");
        var request = HttpRequest.newBuilder(uri).GET().build();

        var downloadPath = Paths.get("oracle-index.html");

        var asFile = HttpResponse.BodyHandlers.ofFile(downloadPath);
        var httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(request,asFile);

        if(response.statusCode() == 200){
            System.out.println("Content written to file: " + downloadPath.toAbsolutePath());
        }
    }

    private static void downloadASync() throws URISyntaxException, ExecutionException, InterruptedException {
        var uri = new URI("https://www.oracle.com/index.html");
        var request = HttpRequest.newBuilder(uri).GET().build();
        var asString = HttpResponse.BodyHandlers.ofString();
        var httpClient = HttpClient.newHttpClient();

        final CompletableFuture<HttpResponse<String>> asyncResponse = httpClient.sendAsync(request,asString);

        waitForAsyncResponse(asyncResponse);

        if(asyncResponse.isDone()){
            final HttpResponse<String> response = asyncResponse.get();
            printResponseInfo(response);
        } else {
            asyncResponse.cancel(true);

            System.err.println("timeout");
        }
        /*
+
        asyncResponse.thenAccept(TestHttp::printResponseInfo)
                .orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(ex -> {
                    asyncResponse.cancel(true);
                    System.err.println("timeout");
                    return null;
                });
          */
    }

    private static void waitForAsyncResponse(CompletableFuture<HttpResponse<String>> asyncResponse) throws InterruptedException {
        int i = 0;

        while (!asyncResponse.isDone()){
            System.out.println("Step + 1");
            i++;
            Thread.sleep(200);
        }

    }
}
