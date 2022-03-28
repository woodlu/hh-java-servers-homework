package service;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterService {

    private final AtomicInteger counter = new AtomicInteger(0);

    public int getNumber() {
        return counter.get();
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public void subtract(int number) {
        counter.addAndGet(-number);
    }

    public void clear() {
        counter.set(0);
    }
}
