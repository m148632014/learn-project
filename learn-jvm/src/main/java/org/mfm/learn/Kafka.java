package org.mfm.learn;

/**
 * @author MengFanmao
 * @since 2019年09月12日
 */
public class Kafka {

    public static void main(String[] args) throws Exception {
        while (true) {
            Kafka.loadReplicaFromDisk();
            Thread.sleep(1000);
        }
    }

    private static void loadReplicaFromDisk() {
        ReplicaManager replicaManager = new ReplicaManager();
        replicaManager.load();
    }

}
