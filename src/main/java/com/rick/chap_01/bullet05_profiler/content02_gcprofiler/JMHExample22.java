package com.rick.chap_01.bullet05_profiler.content02_gcprofiler;

import org.openjdk.jmh.annotations.*;
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
 * @Date: 2022/10/30 23:17
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample22 {
    private byte[] alexBytes;
    private AlexClassLoader classLoader;

    @Setup
    public void init() throws IOException {
        this.alexBytes = Files.readAllBytes(
                // Paths.get("/Users/runzhou/git/java-concurrency-core/target/classes/com/rick/chap_01/bullet05_profiler/content02_gcprofiler/Alex.class"));
                Paths.get("/Users/runzhou/git/java-concurrency-core/target/classes/Alex.class"));
        this.classLoader = new AlexClassLoader(alexBytes);
    }

    @Benchmark
    public Object testLoadClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> alexClass = Class.forName("Alex", true, classLoader);
        return alexClass.newInstance();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder()
                .include(JMHExample22.class.getSimpleName())
                // add GcProfiler输出基准方法执行过程中的GC信息
                .addProfiler(GCProfiler.class)
                // 将最大堆内存设置为128MB，会有多次的GC发生
                .jvmArgsAppend("-Xmx128M")
                .build();
        new Runner(opts).run();
    }
}
