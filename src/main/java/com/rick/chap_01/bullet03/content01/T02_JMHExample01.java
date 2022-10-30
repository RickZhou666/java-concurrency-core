package com.rick.chap_01.bullet03.content01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 12:03
 */
@BenchmarkMode(Mode.AverageTime) // keep OOM: Java Heap Space
// @BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class T02_JMHExample01 {
    private final static String DATA = "DUMMY DATA";

    private List<String> arrayList;
    private List<String> linkedList;

    @Setup(Level.Iteration)
    public void setUp() {
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
    }

    @Benchmark
    public List<String> arrayListAdd() {
        this.arrayList.add(DATA);
        return arrayList;
    }

    @Benchmark
    public List<String> linkedListAdd() {
        this.linkedList.add(DATA);
        return this.linkedList;
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(T02_JMHExample01.class.getSimpleName())
                .forks(1)
                .measurementIterations(10)
                .measurementTime(TimeValue.seconds(1))
                .warmupIterations(10)
                .warmupTime(TimeValue.seconds(1))
                .build();
        new Runner(opts).run();
    }
}
