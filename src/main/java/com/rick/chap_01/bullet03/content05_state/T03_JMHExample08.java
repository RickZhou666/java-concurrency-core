package com.rick.chap_01.bullet03.content05_state;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 16:18
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class T03_JMHExample08 {

    // 将Test设置为线程组共享的
    @State(Scope.Group)
    public static class Test {
        public Test() {
            System.out.println("create instance");
        }

        public void write() {
            System.out.println("write");
        }

        public void read() {
            System.out.println("read");
        }
    }

    // 在线程组"test"中，有三个线程将不断地对Test实例的read方法进行调用
    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testWrite(Test test) {
        // 调用write方法
        test.write();
    }

    // 在线程组"test"中，有三个线程将不断地对Test实例的read方法进行调用
    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testRead(Test test) {
        // 调用read方法
        test.read();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T03_JMHExample08.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }

}
