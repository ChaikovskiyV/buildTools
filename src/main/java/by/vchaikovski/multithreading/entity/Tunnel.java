package by.vchaikovski.multithreading.entity;

import by.vchaikovski.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Tunnel {
    static final Logger logger = LogManager.getLogger();
    private AtomicBoolean isFree;
    private List<Train> insideTrains;
    private int tunnelId;
    private int capacity;
    private int speedIntoTunnel;
    private Train.TrainDirection lastTrainDirection;

    public Tunnel(int tunnelId, int capacity) {
        this.tunnelId = tunnelId;
        this.capacity = capacity;
        insideTrains = new ArrayList<>();
        isFree = new AtomicBoolean(true);
        speedIntoTunnel = new Random().nextInt(5);
    }

    public boolean isFree() {
        return isFree.get();
    }

    public void setFree(boolean condition) {
        isFree.set(condition);
    }

    public int getTunnelId() {
        return tunnelId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addTrain(Train train) {
        lastTrainDirection = train.getDirection();
        insideTrains.add(train);
    }

    public void removeTrain(Train train) {
        insideTrains.remove(train);
    }

    public int getTrainsNumber() {
        return insideTrains.size();
    }

    public Train.TrainDirection getLastTrainDirection() {
        return lastTrainDirection;
    }

    public int getSpeedIntoTunnel() {
        return speedIntoTunnel;
    }

    public void moveThroughTunnel() throws MultithreadingException {
        try {
            TimeUnit.MILLISECONDS.sleep(speedIntoTunnel);
        } catch (InterruptedException e) {
            logger.error(() -> "The current thread " + Thread.currentThread().getName() + "has already interrupted.", e);
            throw new MultithreadingException("The current thread " + Thread.currentThread().getName() + "has already interrupted.", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tunnel tunnel = (Tunnel) o;
        return isFree == tunnel.isFree && tunnelId == tunnel.tunnelId && capacity == tunnel.capacity && insideTrains.equals(tunnel.insideTrains);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first * isFree.hashCode();
        result = result * first + (insideTrains != null ? insideTrains.hashCode() : 0);
        result = result * first * tunnelId;
        result = result * first + capacity;

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Tunnel{")
                .append("isFree=")
                .append(isFree)
                .append(", insideTrains=")
                .append(insideTrains)
                .append(", tunnelId=")
                .append(tunnelId)
                .append(", capacity=")
                .append(capacity)
                .append('}')
                .toString();
    }
}
    /*private static final Logger logger = LogManager.getLogger();
    private static final int INSTANCES_NUMBER = 2;
    private static final int MIN_TUNNEL_CAPACITY = 0;
    private static Tunnel instance;
    private static List<Tunnel> tunnels = new ArrayList<>();
    private static ReentrantLock lock = new ReentrantLock();
    private static Semaphore semaphore = new Semaphore(1);
    private static AtomicBoolean create = new AtomicBoolean(false);
    private AtomicInteger insideTrainsNumber;
    private int tunnelCapacity;
    private Train.TrainDirection previousTrainDirection;
    private ReentrantLock lock;
    private Condition condition;
    private int tunnelId;
    private long speedInTunnel;

    private Tunnel(int tunnelId) {
        this.tunnelId = tunnelId;
        insideTrainsNumber = new AtomicInteger(0);
        condition = lock.newCondition();
        Random random = new Random();
        speedInTunnel = random.nextInt(10);
        tunnelCapacity = random.nextInt(5);
        previousTrainDirection = null;
    }

    public static Tunnel getInstance() throws MultithreadingException {
        while (!create.get()) {
                lock.lock();
                instance = new Tunnel(tunnels.size() + 1);
                tunnels.add(instance);
                if(tunnels.size() < INSTANCES_NUMBER) {
                    create.set(true);
                }
                lock.unlock();
        }
        Random random = new Random();

        return tunnels.get(random.nextInt(tunnels.size()));
    }

    public int getTunnelId() {
        return tunnelId;
    }

    public int addTrain() {
        return insideTrainsNumber.incrementAndGet();
    }

    public void removeTrains() {
       insideTrainsNumber = new AtomicInteger(0);
    }

    public int getInsideTrainsNumber() {
        return insideTrainsNumber.get();
    }

    public void moveThroughTunnel(Train train) throws MultithreadingException {

        lock.lock();
        try {
            if(previousTrainDirection == null || (train.getDirection() == previousTrainDirection && getInsideTrainsNumber() < tunnelCapacity)) {
            moveIntoTunnel(train);
            } else if(previousTrainDirection == train.getDirection() && getInsideTrainsNumber() == tunnelCapacity) {
                removeTrains();
                condition.signalAll();
                logger.info(() -> String.format("The %s train number %d of %s direction  is waiting",
                        train.getType().name(), train.getTrainId(), train.getDirection().name()));
                condition.await();
            } else if(getInsideTrainsNumber() == MIN_TUNNEL_CAPACITY && previousTrainDirection != train.getDirection()) {
                moveIntoTunnel(train);
            } else if(previousTrainDirection != previousTrainDirection && getInsideTrainsNumber() < tunnelCapacity) {
                logger.info(() -> String.format("The %s train number %d of %s direction  is waiting",
                        train.getType().name(), train.getTrainId(), train.getDirection().name()));
                condition.await();
            }
            } catch (InterruptedException e) {
            logger.error(() -> "The current thread " + Thread.currentThread().getName() + "has already interrupted.", e);
            throw new MultithreadingException("The current thread " + Thread.currentThread().getName() + "has already interrupted.", e);
            } finally {
            lock.unlock();
            }
        }

        private void moveIntoTunnel(Train train) throws MultithreadingException {
        try {
            logger.info(() -> String.format("The %s train number %d of %s direction  has moved into the tunnel number %d",
                    train.getType().name(), train.getTrainId(), train.getDirection().name(), train.getTunnel().getTunnelId()));
            addTrain();
            previousTrainDirection = train.getDirection();
            if(!Thread.currentThread().isInterrupted()) {
                TimeUnit.MILLISECONDS.sleep(speedInTunnel);
            }
            logger.info(() -> String.format("The %s train number %d of %s direction  has left the tunnel number %d",
                    train.getType().name(), train.getTrainId(), train.getDirection().name(), train.getTunnel().getTunnelId()));
        } catch (InterruptedException e) {
            logger.error(() -> "The current thread " + Thread.currentThread().getName() + "has already interrupted.", e);
            throw new MultithreadingException("The current thread " + Thread.currentThread().getName() + "has already interrupted.", e);
        }
        }*/

    /*public void moveIntoTunnel(Train train) throws MultithreadingException {
        if(lock.tryLock()) {
            logger.info(String.format("The %s entity number %d of %s direction  has moved into the tunnel number %d",
                    train.getType().name(), train.getTrainId(), train.getDirection().name(), train.getTunnel().getTunnelId()));
            try {
                condition.await(speedInTunnel, TimeUnit.MILLISECONDS);
                logger.info(String.format("The %s entity number %d of %s direction  has left the tunnel number %d",
                        train.getType().name(), train.getTrainId(), train.getDirection().name(), train.getTunnel().getTunnelId()));
                condition.signalAll();
            } catch (InterruptedException e) {
                logger.error("This thread " + Thread.currentThread().getName() + " can't wait.", e);
                throw new MultithreadingException("This thread " + Thread.currentThread().getName() + " can't wait.", e);
            } finally {
                lock.unlock();
            }
        } else {
            logger.info(String.format("The %s entity number %d of %s direction  is waiting",
                    train.getType().name(), train.getTrainId(), train.getDirection().name()));
            try {
                TimeUnit.MILLISECONDS.sleep(speedInTunnel);
                moveIntoTunnel(train);
            } catch (InterruptedException e) {
                logger.error("This thread " + Thread.currentThread().getName() + " can't wait.", e);
                throw new MultithreadingException("This thread " + Thread.currentThread().getName() + " can't wait.", e);
            }
        }
    }*/
//}