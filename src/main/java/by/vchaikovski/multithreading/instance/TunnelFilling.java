package by.vchaikovski.multithreading.instance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class TunnelFilling {
    private static final Logger logger = LogManager.getLogger();
    private static TunnelFilling instance;
    private static Semaphore semaphore = new Semaphore(1);
    private static AtomicBoolean create = new AtomicBoolean(false);
    private static AtomicInteger trainsNumber = new AtomicInteger(0);

    private TunnelFilling() {
    }

    public static TunnelFilling getInstance() {
        if(!create.get()) {
            try {
                semaphore.acquire();
                instance = new TunnelFilling();
                create.set(true);
            } catch (InterruptedException e) {
                logger.error("Exception when a singleton was created.", e);
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
        return instance;
    }

    public static void addTrain() {
        trainsNumber.incrementAndGet();
    }

    public static void removeTrain() {
        if(trainsNumber.get() >= 1) {
            trainsNumber.decrementAndGet();
        }

    }

    public static int getTrainsNumber() {
        return trainsNumber.get();
    }
}