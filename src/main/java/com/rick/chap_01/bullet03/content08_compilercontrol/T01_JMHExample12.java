package com.rick.chap_01.bullet03.content08_compilercontrol;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.PI;
import static java.lang.Math.log;

/**
 * @Author: Rick
 * @Date: 2022/10/30 17:17
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
// 单线程
@State(Scope.Thread)
public class T01_JMHExample12 {

    // 禁止优化
    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    @Benchmark
    public void test1() {

    }

    // 禁止优化
    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    @Benchmark
    public void test2() {
        log(PI);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T01_JMHExample12.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }
}
