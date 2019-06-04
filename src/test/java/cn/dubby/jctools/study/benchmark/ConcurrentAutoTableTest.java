package cn.dubby.jctools.study.benchmark;

import org.jctools.maps.ConcurrentAutoTable;
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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author dubby
 * @date 2019/6/2 20:06
 */

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode({Mode.All})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class ConcurrentAutoTableTest {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(ConcurrentAutoTableTest.class.getName()).forks(1).build();
        new Runner(options).run();
    }

    private ConcurrentAutoTable concurrentAutoTable;

    private LongAdder longAdder;

    @Setup
    public void setup() {
        concurrentAutoTable = new ConcurrentAutoTable();
        longAdder = new LongAdder();
    }

    @Benchmark
    @Threads(10)
    public void incrLongAdder() {
        concurrentAutoTable.increment();
    }

    @Benchmark
    @Threads(10)
    public void incrConcurrentAutoTable() {
        longAdder.increment();
    }

}
