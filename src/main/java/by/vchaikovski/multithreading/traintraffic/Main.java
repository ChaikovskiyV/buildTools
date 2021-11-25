package by.vchaikovski.multithreading.traintraffic;

import by.vchaikovski.multithreading.entity.Train;
import by.vchaikovski.multithreading.exception.MultithreadingException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService service;
    public static void main(String[] args) throws MultithreadingException {
        service = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            Train.TrainType trainType = (i%2 == 0) ? Train.TrainType.PASSENGER : Train.TrainType.CARGO;
            Train.TrainDirection trainDirection = (i%2 == 0) ? Train.TrainDirection.FORWARD : Train.TrainDirection.REVERSE;
            service.submit(new Train(i, trainType, trainDirection));
        }
        service.shutdown();
    }
}