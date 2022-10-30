package com.rick.chap_01.bullet03.content02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 13:19
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Measurement(iterations = 5, time = 1)
@Warmup(iterations = 2, time = 1)
public class T03_JMHExample03 {

    @Benchmark
    public void test() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Measurement(iterations = 10)
    @Warmup(iterations = 5)
    @Benchmark
    public void test2() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T03_JMHExample03.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opts).run();
    }
}
