package com.rick.chap_01.bullet05_profiler.content03_classloaderprofiler;

import com.rick.chap_01.bullet05_profiler.content02_gcprofiler.AlexClassLoader;
import com.rick.chap_01.bullet05_profiler.content02_gcprofiler.JMHExample22;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.ClassloaderProfiler;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/30 23:36
 */

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
// 将热身批次设置为0
@Warmup(iterations = 0)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample23 {
    private byte[] alexBytes;
    private AlexClassLoader classLoader;

    @Setup
    public void init() throws IOException {
        this.alexBytes = Files.readAllBytes(
                Paths.get("/Users/runzhou/git/java-concurrency-core/src/main/java/com/rick/chap_01/bullet05_profiler/content02_gcprofiler/Alex.class"));
        // Paths.get("/Users/runzhou/git/java-concurrency-core/target/classes/com/rick/chap_01/bullet05_profiler/content02_gcprofiler/Alex.class"));
        this.classLoader = new AlexClassLoader(alexBytes);
    }

    @Benchmark
    public Object testLoadClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> alexClass = Class.forName("Alex", true, classLoader);
        return alexClass.newInstance();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(JMHExample23.class.getSimpleName())
                // 增加CL Profiler, 输出类的加载、卸载信息
                .addProfiler(ClassloaderProfiler.class)
                .build();
        new Runner(opts).run();
    }
}
