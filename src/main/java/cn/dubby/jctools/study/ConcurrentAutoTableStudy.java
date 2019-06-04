package cn.dubby.jctools.study;

import org.jctools.maps.ConcurrentAutoTable;

/**
 * @author dubby
 * @date 2019/6/2 16:49
 */
public class ConcurrentAutoTableStudy {

    public static void main(String[] args) {
        ConcurrentAutoTable concurrentAutoTable = new ConcurrentAutoTable();
        System.out.println(concurrentAutoTable.get());
    }

}
