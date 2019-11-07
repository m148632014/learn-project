package org.mfm.learn;

/**
 * @author MengFanmao
 * @since 2019年09月12日
 */
public class Kafka2 {

    private static ReplicaManager replicaManager = new ReplicaManager();

    public static void main(String[] args) throws Exception {
        while (true) {
            Kafka2.loadReplicaFromDisk();
            Thread.sleep(1000);
        }
    }

    private static void loadReplicaFromDisk() {
        Kafka2.replicaManager.load();
    }

}