package com.rick.chap_01.bullet05_profiler.content01_stackprofiler;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 22:30
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Group)
public class T01_JMHExample21 {

    private BlockingQueue<Integer> queue;

    private final static int VALUE = Integer.MAX_VALUE;

    @Setup
    public void init() {
        this.queue = new ArrayBlockingQueue<>(10);
    }

    @GroupThreads(5)
    @Group("blockingQueue")
    @Benchmark
    public void put() throws InterruptedException {
        this.queue.put(VALUE);
    }

    @GroupThreads(5)
    @Group("blockingQueue")
    @Benchmark
    public int take() throws InterruptedException {
        return this.queue.take();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T01_JMHExample21.class.getSimpleName())
                .timeout(TimeValue.seconds(5))
                .addProfiler(StackProfiler.class) // 增加StackProfiler
                .build();
        new Runner(opts).run();
    }
}
