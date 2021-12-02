package by.vchaikovski.multithreading.generator;

import by.vchaikovski.multithreading.entity.Train;
import by.vchaikovski.multithreading.traintraffic.TrainTraffic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TrainGenerator {
    static final Logger logger = LogManager.getLogger();

    public List<Train> generate(int trainsNumber, TrainTraffic traffic) {
        List<Train> trains = new ArrayList<>();
        for (int i = 0; i < trainsNumber; i++) {
            Train.TrainType type = i % 2 == 0 ? Train.TrainType.CARGO : Train.TrainType.PASSENGER;
            Train.TrainDirection direction = i % 2 == 0 ? Train.TrainDirection.FORWARD : Train.TrainDirection.REVERSE;
            trains.add(new Train(i + 1, type, direction, traffic));
        }
        return trains;
    }
}