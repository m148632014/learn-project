package org.mfm.learn;

/**
 * @author MengFanmao
 * @since 2019年09月12日
 */
public class Kafka3 {

    private static  ReplicaFetcher fetcher = new ReplicaFetcher();

    public static void main(String[] args) throws Exception {
        loadReplicaFromDisk();
        while (true){
            fetchReplicaFromRemote();
            Thread.sleep(1000);
        }
    }

    private static void loadReplicaFromDisk(){
        ReplicaManager replicaManager = new ReplicaManager();
        replicaManager.load();
    }

    private static void fetchReplicaFromRemote(){
        fetcher.fetch();
    }
}
