package org.example.ModernConcurrency;

import java.time.LocalDateTime;
import java.util.concurrent.Flow;

public class WordSubscriber implements Flow.Subscriber<String> {
    @Override
    public void onSubscribe(final Flow.Subscription subscription) {
        System.out.println(LocalDateTime.now() + " onSubscribe()");

        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(final String s) {
        System.out.println(LocalDateTime.now() + " onNext(): " + s);

    }

    @Override
    public void onError(final Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println(LocalDateTime.now() + " onComplete()");
    }
}
