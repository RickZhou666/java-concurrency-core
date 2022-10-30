package com.rick.chap_01.bullet04.content01_corrent_jmhcase;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.PI;

/**
 * @Author: Rick
 * @Date: 2022/10/30 17:32
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
// 单线程
@State(Scope.Thread)
public class T01_JMHExample13 {
    @Benchmark
    public void baseline() {
        // 空的方法
    }

    @Benchmark
    public void measureLog1() {
        // 进行数学运算，但是在局部方法的
        Math.log(PI);
    }


    @Benchmark
    public void measureLog2() {
        // result是通过数学运算所得并且在下一行代码中得到了应用
        double result = Math.log(PI);
        // 对result进行数学运算，但是结果既不保存也不返回，更不会进行二次运算
        Math.log(result);
    }

    @Benchmark
    public double measureLog3() {
        // 返回数学运算结果
        return Math.log(PI);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T01_JMHExample13.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }


}
