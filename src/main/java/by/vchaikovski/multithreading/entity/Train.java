package by.vchaikovski.multithreading.entity;

import by.vchaikovski.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

public class Train implements Callable<Train.TrainDirection> {
    static final Logger logger = LogManager.getLogger();
    private TrainDirection direction;
    private int trainId;
    private TrainType type;

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

        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return trainId == train.trainId && (train.direction != null ? direction == train.direction : direction == null) &&
                (train.type != null ? type == train.type : type == null);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first * trainId;
        result = result * first + (direction != null ? direction.hashCode() : 0);
        result = result * first + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Train{")
                .append("trainId=")
                .append(trainId)
                .append(", type=")
                .append(type)
                .append(", direction=")
                .append(direction)
                .append('}')
                .toString();
    }
}