package by.vchaikovski.multithreading.entity;

import by.vchaikovski.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Tunnel {
    private static final Logger logger = LogManager.getLogger();
    private static final int INSTANCES_NUMBER = 2;
    private static final int MIN_TUNNEL_CAPACITY = 0;
    private static Tunnel instance;
    private static List<Tunnel> tunnels = new ArrayList<>();
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
        lock = new ReentrantLock(true);
        condition = lock.newCondition();
        Random random = new Random();
        speedInTunnel = random.nextInt(10);
        tunnelCapacity = random.nextInt(5);
        previousTrainDirection = null;
    }

    public static Tunnel getInstance() throws MultithreadingException {
        if(!create.get() || tunnels.size() < INSTANCES_NUMBER) {
            try {
                semaphore.acquire();
                instance = new Tunnel(tunnels.size() + 1);
                tunnels.add(instance);
                create.set(true);
            } catch (InterruptedException e) {
                logger.error("Exception when a singleton was created.", e);
                throw new MultithreadingException("Exception when a singleton was created.", e);
            } finally {
                semaphore.release();
            }
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
        logger.info(() -> String.format("The %s train number %d of %s direction  has approached to tunnel number %d",
                train.getType().name(), train.getTrainId(), train.getDirection().name(), train.getTunnel().getTunnelId()));
        try {
            lock.lock();
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
        }

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
}