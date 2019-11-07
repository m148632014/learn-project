package org.mfm.learn;

/**
 * -- 模拟老年代GC
 * -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520
 * -XX:MaxHeapSize=20971520
 * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
 * -XX:PretenureSizeThreshold=3145728
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
 */
public class Demo3 {

    public static void main(String[] args) {

    }
}
