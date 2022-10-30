package com.rick.chap_01.bullet03.content05_state;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 16:12
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(3)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// 设置5个线程运行基准测试方法
@Threads(5)
public class T02_JMHExample07 {

    // Test的实例将会被多个线程共享，也就是说只有一份Test的实例
    @State(Scope.Benchmark)
    public static class Test {
        public Test() {
            System.out.println("create instance");
        }

        public void method() {
        }
    }

    // 通过基准测试将State引用传入
    @Benchmark
    public void test(Test test) {
        test.method();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T02_JMHExample07.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }
}
