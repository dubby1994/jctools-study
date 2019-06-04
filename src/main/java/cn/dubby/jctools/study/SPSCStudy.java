package cn.dubby.jctools.study;

import org.jctools.queues.SpscArrayQueue;

/**
 * @author dubby
 * @date 2019/6/2 20:35
 */
public class SPSCStudy {

    public static void main(String[] args) {
        SpscArrayQueue<Long> spscArrayQueue = new SpscArrayQueue<>(1024);
    }

}
