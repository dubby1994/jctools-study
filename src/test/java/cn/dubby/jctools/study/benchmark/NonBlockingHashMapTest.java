package cn.dubby.jctools.study.benchmark;

import org.jctools.maps.NonBlockingHashMap;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author dubby
 * @date 2019/6/2 20:20
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode({Mode.All})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class NonBlockingHashMapTest {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(NonBlockingHashMapTest.class.getName()).forks(1).build();
        new Runner(options).run();
    }

    private NonBlockingHashMap nonBlockingHashMap;
    private ConcurrentHashMap concurrentHashMap;

    @Setup
    public void setup() {
        nonBlockingHashMap = new NonBlockingHashMap();
        concurrentHashMap = new ConcurrentHashMap();
    }

    @Benchmark
    @Threads(10)
    public void putNonBlockingHashMap() {
        nonBlockingHashMap.put(System.currentTimeMillis()/10240, System.currentTimeMillis());
    }

    @Benchmark
    @Threads(10)
    public void putConcurrentHashMap() {
        concurrentHashMap.put(System.currentTimeMillis()/10240, System.currentTimeMillis());
    }

}
