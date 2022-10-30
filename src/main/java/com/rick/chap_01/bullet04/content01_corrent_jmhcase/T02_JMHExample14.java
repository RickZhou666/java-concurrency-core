package com.rick.chap_01.bullet04.content01_corrent_jmhcase;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 17:38
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// 单线程
@State(Scope.Thread)
public class T02_JMHExample14 {

    double x1 = Math.PI;
    double x2 = Math.PI * 2;

    @Benchmark
    public double baseline() {
        // 不是Dead Code，因为对结果进行了返回
        return Math.pow(x1, 2);
    }

    @Benchmark
    public double powButReturnOne() {
        // Dead Code 会被擦除
        Math.pow(x1, 2);

        // 不会被擦除，因为对结果进行了返回
        return Math.pow(x2, 2);
    }

    @Benchmark
    public double putThenAdd() {
        // 通过加法运算对两个结果进行了合并，因此两次的计算都会生效
        return Math.pow(x1, 2) + Math.pow(x2, 2);
    }

    @Benchmark
    public void useBlackhole(Blackhole hole) {
        // 将结果存放至black hole中，因此两次pow操作都会生效
        hole.consume(Math.pow(x1, 2));
        hole.consume(Math.pow(x2, 2));
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T02_JMHExample14.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }
}
