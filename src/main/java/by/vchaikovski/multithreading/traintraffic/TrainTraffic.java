package by.vchaikovski.multithreading.traintraffic;

import by.vchaikovski.multithreading.entity.Train;
import by.vchaikovski.multithreading.entity.Tunnel;
import by.vchaikovski.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class TrainTraffic {
    static final Logger logger = LogManager.getLogger();
    private static final int WAIT_TIME = 50;
    private static TrainTraffic instance;
    private static ReentrantLock instanceLock = new ReentrantLock(true);
    private static AtomicBoolean isCreate = new AtomicBoolean(false);
    private static AtomicInteger trainCount = new AtomicInteger(0);
    private static Semaphore semaphore;
    private static List<Tunnel> tunnelsList;
    private static List<Train> waitingList;
    private int oneDirectionLimit;
//    private static ReentrantLock trafficLock;
//    private static Condition conditionLock;

    private TrainTraffic(int oneDirectionLimit) {
        this.oneDirectionLimit = oneDirectionLimit;
        tunnelsList = new ArrayList<>();
        waitingList = new ArrayList<>();
        }

    public static TrainTraffic getInstance(int oneDirectionLimit) {
        while (!isCreate.get()) {
            instanceLock.lock();
            try {
                if(instance == null) {
                    instance = new TrainTraffic(oneDirectionLimit);
                    isCreate.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public static void addTunnels(List<Tunnel> tunnels) {
        tunnelsList.addAll(tunnels);
    }

    public void moveIntoTunnel(Train train) throws MultithreadingException {
        semaphore = new Semaphore(instance.oneDirectionLimit, true);

        //trafficLock = new ReentrantLock(true);
        try {
            if (semaphore.tryAcquire(WAIT_TIME, TimeUnit.MILLISECONDS)) {
            for (Tunnel tunnel : tunnelsList) {
                Train sameDirectionTrain = waitingList.stream()
                        .filter(t -> t.getDirection() == tunnel.getLastTrainDirection())
                        .findAny()
                        .orElse(null);
                logger.info(() -> train.toString() + "has arrived at " + tunnel);
                if(tunnel.isFree()) {
                    if(tunnel.getLastTrainDirection() == null || (tunnel.getTrainsNumber() < tunnel.getCapacity() &&
                            tunnel.getLastTrainDirection() == train.getDirection() && trainCount.get() < instance.oneDirectionLimit)) {
                        logger.info(() -> train.toString() + "has moved into " + tunnel);
                        trainCount.incrementAndGet();
                        tunnel.addTrain(train);
                        tunnel.moveThroughTunnel();
                        if(trainCount.get() == instance.oneDirectionLimit) {
                            tunnel.setFree(false);
                        }
                    } else if((trainCount.get() == 0 || sameDirectionTrain == null) && tunnel.getLastTrainDirection() != train.getDirection()) {
                        logger.info(() -> train.toString() + "has moved into " + tunnel);
                        trainCount.incrementAndGet();
                        tunnel.addTrain(train);
                        tunnel.moveThroughTunnel();
                    }
                } else {
                    logger.info(() -> train.toString() + " has arrived at " + tunnel);
                    waitingList.add(train);
                }
            }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void leftTunnel(Train train, Tunnel tunnel) {
        waitingList.remove(train);
        if(trainCount.compareAndSet(instance.oneDirectionLimit, 0)) {
            tunnel.setFree(true);
        }
        semaphore.release();
    }
}