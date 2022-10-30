package com.rick.chap_01.bullet04.content02_highrankusage;

import com.rick.chap_01.bullet04.content01_corrent_jmhcase.T05_JMHExample17;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Rick
 * @Date: 2022/10/30 21:52
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Group)
public class T01_JMHExample18 {
    private AtomicInteger counter;

    @Setup
    public void init() {
        this.counter = new AtomicInteger();
    }

    @GroupThreads(5)
    @Group("q")
    @Benchmark
    public void inc() {
        this.counter.incrementAndGet();
    }


    @GroupThreads(5)
    @Group("q")
    @Benchmark
    public int get() {
        return this.counter.get();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T01_JMHExample18.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

}
