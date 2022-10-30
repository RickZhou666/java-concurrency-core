package com.rick.chap_01.bullet04.content02_highrankusage;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 22:13
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Group)
public class T04_JMHExample20 {

    @Param({"1", "2", "3", "4"})
    private int type;

    private Map<Integer, Integer> map;

    @Setup
    public void setUp() {
        switch (type) {
            case 1:
                this.map = new ConcurrentHashMap<>();
                break;
            case 2:
                this.map = new ConcurrentSkipListMap<>();
                break;
            case 3:
                this.map = new Hashtable<>();
                break;
            case 4:
                this.map = Collections.synchronizedMap(new HashMap<>());
                break;
            default:
                throw new IllegalArgumentException("Illegal map type.");
        }
    }

    @GroupThreads(5)
    @Group("q")
    @Benchmark
    public void putMap() {
        int random = randomIntValue();
        this.map.put(random, random);
    }

    @GroupThreads(5)
    @Group("q")
    @Benchmark
    public Integer getMap() {
        return this.map.get(randomIntValue());
    }

    private int randomIntValue() {
        return (int) Math.ceil(Math.random() * 600_000);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(T04_JMHExample20.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }
}
