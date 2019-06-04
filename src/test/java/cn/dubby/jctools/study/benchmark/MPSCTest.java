package cn.dubby.jctools.study.benchmark;

import org.jctools.queues.MpscLinkedQueue;
import org.jctools.queues.MpscLinkedQueue8;
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

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author dubby
 * @date 2019/6/2 20:38
 */
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.All})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class MPSCTest {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(MPSCTest.class.getName()).forks(1).build();
        new Runner(options).run();
    }

    private MpscLinkedQueue<Long> mpscLinkedQueue;

    private LinkedTransferQueue<Long> linkedTransferQueue;

    @Setup
    public void setup() {
        mpscLinkedQueue = MpscLinkedQueue.newMpscLinkedQueue();
        linkedTransferQueue = new LinkedTransferQueue<Long>();
    }

    @Benchmark
    @Threads(10)
    public void mpscLinkedQueue() {
        mpscLinkedQueue.add(System.currentTimeMillis());
    }

    @Benchmark
    @Threads(1)
    public void pollMpscLinkedQueue() {
        mpscLinkedQueue.poll();
    }

    @Benchmark
    @Threads(10)
    public void linkedTransferQueue() {
        linkedTransferQueue.add(System.currentTimeMillis());
    }

    @Benchmark
    @Threads(1)
    public void pollLinkedTransferQueue() {
        linkedTransferQueue.poll();
    }

}
