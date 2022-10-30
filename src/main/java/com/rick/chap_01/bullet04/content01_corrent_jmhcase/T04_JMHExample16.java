package com.rick.chap_01.bullet04.content01_corrent_jmhcase;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 21:25
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// 单线程
@State(Scope.Thread)
public class T04_JMHExample16 {

    private int x = 1;
    private int y = 2;

    @Benchmark
    public int measure() {
        return (x + y);
    }

    private int loopCompute(int times) {
        int result = 0;
        for (int i = 0; i < times; i++) {
            result += (x + y);
        }
        return result;
    }

    @OperationsPerInvocation
    @Benchmark
    public int measureLoop_1() {
        return loopCompute(1);
    }

    @OperationsPerInvocation(10)
    @Benchmark
    public int measureLoop_10() {
        return loopCompute(10);
    }

    @OperationsPerInvocation(100)
    @Benchmark
    public int measureLoop_100() {
        return loopCompute(100);
    }

    @OperationsPerInvocation(1000)
    @Benchmark
    public int measureLoop_1000() {
        return loopCompute(1000);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T04_JMHExample16.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }
}
