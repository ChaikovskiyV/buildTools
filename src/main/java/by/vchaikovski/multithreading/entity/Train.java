package by.vchaikovski.multithreading.entity;

import by.vchaikovski.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

import static java.lang.String.format;

public class Train implements /*Runnable*/Callable<Train.TrainDirection> {
    static final Logger logger = LogManager.getLogger();
    private TrainDirection direction;
    private int trainId;
    private TrainType type;
    private Tunnel tunnel;

    public enum TrainType {
        PASSENGER, CARGO
    }

    public enum TrainDirection {
        FORWARD, REVERSE
    }

    public Train(int trainId, TrainType type, TrainDirection direction) throws MultithreadingException {
        this.trainId = trainId;
        this.type = type;
        this.direction = direction;
        tunnel = Tunnel.getInstance();
    }

    public int getTrainId() {
        return trainId;
    }

    public TrainType getType() {
        return type;
    }

    public TrainDirection getDirection() {
        return direction;
    }

    @Override
    public TrainDirection call() throws MultithreadingException {
        tunnel.moveThroughTunnel(this);
        return direction;
    }

    public Tunnel getTunnel() {
        return tunnel;
    }
}