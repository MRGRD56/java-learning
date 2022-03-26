package com.mrgrd56.common;

import java.time.Duration;
import java.util.function.Consumer;

public class Stopwatch {
    private Long startTime = null;
    private Long endTime = null;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void stopAndPrintElapsed(String label) {
        stop();
        printElapsed(label);
    }

    public void printElapsed() {
        printElapsed(null);
    }

    public void printElapsed(String label) {
        System.out.println((label == null ? "" : label + " ") + elapsedSeconds() + "s");
    }

    public void reset() {
        startTime = null;
        endTime = null;
    }

    public void restart() {
        reset();
        start();
    }

    public long elapsedNanos() {
        return endTime - startTime;
    }

    public long elapsedMillis() {
        return elapsed().toMillis();
    }

    public double elapsedSeconds() {
        return elapsedMillis() / 1000D;
    }

    public Duration elapsed() {
        return Duration.ofNanos(elapsedNanos());
    }

    public static Stopwatch startNew() {
        var stopwatch = new Stopwatch();
        stopwatch.start();
        return stopwatch;
    }
}
