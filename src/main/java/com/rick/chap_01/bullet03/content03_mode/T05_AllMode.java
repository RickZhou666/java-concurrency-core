package com.rick.chap_01.bullet03.content03_mode;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 15:47
 */
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 1ms = 1000µs = 1,000,000ns
@State(Scope.Thread)
public class T05_AllMode {
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Benchmark
    public void testAverageTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @BenchmarkMode(Mode.All)
    @Benchmark
    public void testAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(T05_AllMode.class.getSimpleName())
                .forks(1)
                .measurementIterations(10)
                .measurementTime(TimeValue.seconds(1))
                .warmupIterations(10)
                .warmupTime(TimeValue.seconds(1))
                .build();
        new Runner(opts).run();
    }
}
