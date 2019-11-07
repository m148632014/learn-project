package org.mfm.learn;

/**
 * 模拟对象进入老年代 动态年龄进入老年代，survivor放不下对象，部分对象进入老年代，年龄超过15岁进入老年代，大对象进入老年代
 * -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 
 * -XX:SurvivorRatio=8  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760 
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC 
 * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
 */
public class Demo2 {

    public static void main(String[] args) {
        byte[] array1 = new byte[2*1024*1024]; //2MB
        array1 = new byte[2*1024*1024]; //2MB
        array1 = new byte[2*1024*1024]; //2MB
        array1 = null;

        byte[] array2 = new byte[128*1024]; //128KB
        
        byte[] array3 = new byte[2*1024*1024]; //2M
        array3 = new byte[2*1024*1024]; //2M
        array3 = new byte[2*1024*1024]; //2M
        array3 = new byte[128*1024]; //128KB
        array3 = null;

        byte[] array4 = new byte[2*1024*1024]; //2M
    }
}
