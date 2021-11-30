package by.vchaikovski.multithreading.main;

import by.vchaikovski.multithreading.entity.Train;
import by.vchaikovski.multithreading.entity.Tunnel;
import by.vchaikovski.multithreading.exception.MultithreadingException;
import by.vchaikovski.multithreading.generator.TrainGenerator;
import by.vchaikovski.multithreading.generator.TunnelGenerator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService service;
    public static void main(String[] args) throws MultithreadingException {
        service = Executors.newFixedThreadPool(10);
        List<Train> trains = new TrainGenerator().generate(5);
        List<Tunnel> tunnels = new TunnelGenerator().generate(2, 5);
        for (int i = 1; i <= 10; i++) {
            Train.TrainType trainType = (i%2 == 0) ? Train.TrainType.PASSENGER : Train.TrainType.CARGO;
            Train.TrainDirection trainDirection = (i%2 == 0) ? Train.TrainDirection.FORWARD : Train.TrainDirection.REVERSE;
            service.submit(new Train(i, trainType, trainDirection));
        }
        service.shutdown();
    }
}