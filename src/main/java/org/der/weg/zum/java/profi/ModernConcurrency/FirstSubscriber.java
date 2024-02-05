package org.der.weg.zum.java.profi.ModernConcurrency;

import java.time.LocalDateTime;
import java.util.concurrent.Flow;
import java.util.function.Consumer;

public class FirstSubscriber implements Flow.Subscriber<String>{

    private final Consumer<String> consumer;

    private Flow.Subscription subscription;

    private int count = 1;

    FirstSubscriber(final Consumer<String> consumer){
        this.consumer = consumer;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("First 5 Subscription: " + subscription);

        this.subscription = subscription;
        this.subscription.request(10);
    }

    @Override
    public void onNext(final String s) {
            System.err.println("First 5 " + count + "x onNext" + s);

            consumer.accept(s);
            count++;
            if(count > 5) subscription.cancel();
    }

    @Override
    public void onError(Throwable throwable) {
            throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete()");
    }
}
