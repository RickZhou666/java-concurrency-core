package com.rick.chap_01.bullet04.content01_corrent_jmhcase;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 21:35
 */
@BenchmarkMode(Mode.AverageTime)
// 将Fork设置为0
// @Fork(0)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class T05_JMHExample17 {

    // Inc1 和Inc2的实现完全一样
    interface Inc {
        int inc();
    }

    public static class Inc1 implements Inc {

        private int i = 0;

        @Override
        public int inc() {
            return ++i;
        }
    }

    public static class Inc2 implements Inc {

        private int i = 0;

        @Override
        public int inc() {
            return ++i;
        }
    }

    private Inc inc1 = new Inc1();
    private Inc inc2 = new Inc2();

    private int measure(Inc inc) {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += inc.inc();
        }
        return result;
    }

    @Benchmark
    public int measure_inc_1() {
        return this.measure(inc1);
    }

    @Benchmark
    public int measure_inc_2() {
        return this.measure(inc2);
    }

    @Benchmark
    public int measure_inc_3() {
        return this.measure(inc1);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T05_JMHExample17.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }
}
