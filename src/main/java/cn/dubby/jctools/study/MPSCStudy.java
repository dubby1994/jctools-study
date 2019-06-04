package cn.dubby.jctools.study;

import org.jctools.queues.MpscLinkedQueue;
import org.jctools.queues.MpscLinkedQueue8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dubby
 * @date 2019/6/2 20:56
 */
public class MPSCStudy {

    public static void main(String[] args) {
        int threadNum = 10, loopNum = 100;
        MpscLinkedQueue<String> mpscLinkedQueue = new MpscLinkedQueue8<String>();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; ++i) {
            executorService.submit(() -> {
                for (int j = 0; j < loopNum; ++j) {
                    mpscLinkedQueue.offer(Thread.currentThread().getId() + "-" + System.currentTimeMillis());
                }
            });
        }

        while (true) {
            String s = mpscLinkedQueue.poll();
            if (s == null) {
                continue;
            }
            System.out.println(s);
        }
    }

}
