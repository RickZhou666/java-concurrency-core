package com.rick.chap_01.bullet03.content02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 13:16
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Measurement(iterations = 5)
@Warmup(iterations = 3)
public class T02_JMHExample03 {

    @Benchmark
    public void test() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }
}
