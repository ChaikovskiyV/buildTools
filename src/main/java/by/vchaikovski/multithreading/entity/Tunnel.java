package by.vchaikovski.multithreading.entity;

import by.vchaikovski.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class Tunnel {
    static final Logger logger = LogManager.getLogger();
    static final int MAX_SPEED = 10;
    static final int MIN_SPEED = 1;
    private static ReentrantLock lock = new ReentrantLock(true);
    private AtomicBoolean isFree;
    private AtomicInteger oneSideTrainCount;
    private AtomicInteger insideTrainsNumber;
    private int tunnelId;
    private int capacity;
    private int oneSideLimit;
    private int speedIntoTunnel;
    private Train.TrainDirection lastTrainDirection;

    public Tunnel(int tunnelId, int capacity, int oneSideLimit) {
        this.tunnelId = tunnelId;
        this.capacity = capacity;
        this.oneSideLimit = oneSideLimit;
        isFree = new AtomicBoolean(true);
        oneSideTrainCount = new AtomicInteger(0);
        insideTrainsNumber = new AtomicInteger(0);
        speedIntoTunnel = new Random().nextInt(MIN_SPEED, MAX_SPEED);
    }

    public boolean isFree(Train train) {
        try {
            lock.lock();
            if (insideTrainsNumber.get() == 0) { // a tunnel is empty
                receivePermitIfTunnelEmpty(train);
            } else { // a tunnel is not empty
                receivePermitIfTunnelNotEmpty(train);
            }
        } finally {
            lock.unlock();
        }
        return isFree.get();
    }

    public void setLastTrainDirection(Train.TrainDirection lastTrainDirection) {
        lock.lock();
        this.lastTrainDirection = lastTrainDirection;
        lock.unlock();
    }

    public int getTunnelId() {
        return tunnelId;
    }

    public int getOneSideLimit() {
        return oneSideLimit;
    }

    public int getOneSideTrainCount() {
        return oneSideTrainCount.get();
    }

    public void resetCount() {
        oneSideTrainCount.set(0);
    }

    public void addTrain(Train train) {
        try {
            lock.lock();
            setLastTrainDirection(train.getDirection());
            insideTrainsNumber.incrementAndGet();
            oneSideTrainCount.incrementAndGet();
            logger.info(() -> String.format("%s has moved into %s.%n", train, this));
        } finally {
            lock.unlock();
        }
    }

    public void removeTrain(Train train) {
        lock.lock();
        insideTrainsNumber.decrementAndGet();
        logger.info(() -> String.format("%s has left the %s.%n", train.toString(), this));
        if (!Thread.currentThread().isInterrupted()) {
            Thread.currentThread().interrupt();
        }
        lock.unlock();
    }

    public Train.TrainDirection getLastTrainDirection() {
        return lastTrainDirection;
    }

    public int getSpeedIntoTunnel() {
        return speedIntoTunnel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void moveThroughTunnel() throws MultithreadingException {
        try {
            TimeUnit.MILLISECONDS.sleep(speedIntoTunnel);
        } catch (InterruptedException e) {
            logger.error(() -> "The current thread " + Thread.currentThread().getName() + "has already interrupted.", e);
            throw new MultithreadingException("The current thread " + Thread.currentThread().getName() + "has already interrupted.", e);
        }
    }

    private boolean isContainsOtherDirection(Train train) {
        return train.getTraffic().isContainsAnotherDirection(train.getDirection());
    }

    private void receivePermitIfTunnelEmpty(Train train) {
        if (lastTrainDirection == train.getDirection()) { // the last direction is the same with current
            if (oneSideTrainCount.get() >= oneSideLimit) { // the limit of the same direction is exceeded
                if (isContainsOtherDirection(train)) { // there are other directions
                    isFree.set(false);
                } else { // there aren't other directions
                    isFree.set(true);
                }
            } else { // the limit of the same direction isn't exceeded
                isFree.set(true);
            }
        } else { // the last direction is different with current
            isFree.set(true);
            resetCount();
        }
    }

    private void receivePermitIfTunnelNotEmpty(Train train) {
        if (insideTrainsNumber.get() < capacity) { // a tunnel is not empty but not filled
            if (lastTrainDirection != train.getDirection()) { // a current direction is different with the last
                isFree.set(false);
            } else { // a current direction is the same with the last
                if (oneSideTrainCount.get() < oneSideLimit) { // the limit of the same direction isn't exceeded
                    isFree.set(true);
                } else {  // the limit of the same direction is exceeded
                    if (isContainsOtherDirection(train)) { // there are other directions
                        isFree.set(false);
                    } else { // there aren't other directions
                        isFree.set(true);
                    }
                }
            }
        } else { // a tunnel is filled
            isFree.set(false);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tunnel tunnel = (Tunnel) o;
        return tunnelId == tunnel.tunnelId && capacity == tunnel.capacity && oneSideLimit == tunnel.oneSideLimit &&
                speedIntoTunnel == tunnel.speedIntoTunnel && isFree.get() == tunnel.isFree.get() &&
                oneSideTrainCount.get() == tunnel.oneSideTrainCount.get() && insideTrainsNumber.get() == tunnel.insideTrainsNumber.get() &&
                (tunnel.lastTrainDirection == null ? lastTrainDirection == null : lastTrainDirection == tunnel.lastTrainDirection);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first * tunnelId;
        result = result * first * capacity;
        result = result * first * oneSideLimit;
        result = result * first * speedIntoTunnel;
        result = result * first + isFree.hashCode();
        result = result * first + insideTrainsNumber.hashCode();
        result = result * first + oneSideTrainCount.hashCode();
        result = result * first + (lastTrainDirection == null ? 0 : lastTrainDirection.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Tunnel{ number ")
                .append(tunnelId)
                .append(", capacity=")
                .append(capacity)
                .append(", oneSideLimit=")
                .append(oneSideLimit)
                .append(", speedIntoTunnel=")
                .append(speedIntoTunnel)
                .append(", insideTrainsNumber=")
                .append(insideTrainsNumber.get())
                .append(", lastTrainDirection=")
                .append(lastTrainDirection)
                .append(", oneSideTrainCount=")
                .append(oneSideTrainCount)
                .append(", isFree=")
                .append(isFree)
                .append('}')
                .toString();
    }
}