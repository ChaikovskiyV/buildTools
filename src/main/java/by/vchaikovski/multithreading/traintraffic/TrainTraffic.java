package by.vchaikovski.multithreading.traintraffic;

import by.vchaikovski.multithreading.entity.Train;
import by.vchaikovski.multithreading.entity.Tunnel;
import by.vchaikovski.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class TrainTraffic {
    static final Logger logger = LogManager.getLogger();
    private static final int WAIT_TIME = 50;
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static TrainTraffic instance;
    private static AtomicBoolean isCreate = new AtomicBoolean(false);
    private static List<Tunnel> tunnelsList = new ArrayList<>();
    private static List<Train> waitingList = new ArrayList<>();

    private TrainTraffic() {
    }

    public static TrainTraffic getInstance() {
        while (!isCreate.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new TrainTraffic();
                    isCreate.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void addTunnels(List<Tunnel> tunnels) {
        tunnelsList.addAll(tunnels);
    }

    public void moveIntoTunnel(Train train) throws MultithreadingException {
        lock.lock();
        Tunnel currentTunnel;
        try {
            while ((currentTunnel = findAllowedTunnel(train, tunnelsList)) == null) {
                if (!waitingList.contains(train)) {
                    logger.info(() -> String.format("%s is waiting...%n", train));
                    waitingList.add(train);
                }
                TimeUnit.MILLISECONDS.sleep(WAIT_TIME);
            }
        } catch (InterruptedException e) {
            logger.error(() -> "The current thread " + Thread.currentThread().getName() + " has already interrupted.", e);
            throw new MultithreadingException("The current thread " + Thread.currentThread().getName() + " has already interrupted.", e);
        } finally {
            lock.unlock();
        }
        waitingList.remove(train);
        currentTunnel.addTrain(train);
        currentTunnel.moveThroughTunnel();
        currentTunnel.removeTrain(train);
    }

    public boolean isContainsAnotherDirection(Train.TrainDirection direction) {
        return waitingList.stream()
                .filter(t -> t.getDirection() != direction)
                .findAny()
                .orElse(null) == null;
    }

    private Tunnel findAllowedTunnel(Train train, List<Tunnel> tunnels) {
        return tunnels.stream()
                .filter(t -> t.isFree(train))
                .findFirst()
                .orElse(null);
    }
}