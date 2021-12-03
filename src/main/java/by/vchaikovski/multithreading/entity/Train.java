package by.vchaikovski.multithreading.entity;

import by.vchaikovski.multithreading.exception.MultithreadingException;
import by.vchaikovski.multithreading.traintraffic.TrainTraffic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Train implements Runnable {
    static final Logger logger = LogManager.getLogger();
    private int trainId;
    private TrainDirection direction;
    private TrainType type;
    private TrainTraffic traffic;

    public enum TrainType {
        PASSENGER, CARGO
    }

    public enum TrainDirection {
        FORWARD, REVERSE
    }

    public Train(int trainId, TrainType type, TrainDirection direction, TrainTraffic traffic) {
        this.trainId = trainId;
        this.type = type;
        this.direction = direction;
        this.traffic = traffic;
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

    public TrainTraffic getTraffic() {
        return traffic;
    }

    @Override
    public void run() {
        try {
            traffic.moveIntoTunnel(this);
        } catch (MultithreadingException e) {
            logger.error(() -> "The thread " + this.getClass().getName() + " wasn't run.", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return trainId == train.trainId && (train.direction == null ? direction == null : direction == train.direction) &&
                (train.type == null ? type == null : type == train.type) &&
                (train.traffic == null ? traffic == null : traffic.equals(train.traffic));
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first * trainId;
        result = result * first + (direction == null ? 0 : direction.hashCode());
        result = result * first + (type == null ? 0 : type.hashCode());
        result = result * first + (traffic == null ? 0 : traffic.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("The ")
                .append(type)
                .append(" train number ")
                .append(trainId)
                .append(" of ")
                .append(direction)
                .append(" direction")
                .toString();
    }
}