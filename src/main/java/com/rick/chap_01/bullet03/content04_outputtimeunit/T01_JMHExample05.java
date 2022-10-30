package com.rick.chap_01.bullet03.content04_outputtimeunit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 15:51
 */

@OutputTimeUnit(TimeUnit.MILLISECONDS) // 1ms = 1000µs = 1,000,000ns
@State(Scope.Thread)
@Measurement(iterations = 5)
@Warmup(iterations = 2)
public class T01_JMHExample05 {
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Benchmark
    public void test() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T01_JMHExample05.class.getSimpleName())
                .forks(1)
                .timeUnit(TimeUnit.NANOSECONDS)
                .measurementTime(TimeValue.seconds(1))
                .warmupTime(TimeValue.seconds(1))
                .build();
        new Runner(opts).run();
    }
}
