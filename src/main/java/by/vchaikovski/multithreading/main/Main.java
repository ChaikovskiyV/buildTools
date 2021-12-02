package by.vchaikovski.multithreading.main;

import by.vchaikovski.multithreading.entity.Train;
import by.vchaikovski.multithreading.entity.Tunnel;
import by.vchaikovski.multithreading.exception.MultithreadingException;
import by.vchaikovski.multithreading.generator.TrainGenerator;
import by.vchaikovski.multithreading.generator.TunnelGenerator;
import by.vchaikovski.multithreading.reader.ReaderFromFile;
import by.vchaikovski.multithreading.traintraffic.TrainTraffic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static final Logger logger = LogManager.getLogger();
    static final String FILE_NAME = "sources/sourcedata.txt";


    public static void main(String[] args) throws MultithreadingException {
        ReaderFromFile reader = ReaderFromFile.getInstance();
        List<Integer> data = reader.readeData(FILE_NAME);
        int trainsNumber = data.get(0);
        int tunnelsNumber = data.get(1);
        int maxTunnelCapacity = data.get(2);
        int oneDirectionTrainLimit = data.get(3);

        ExecutorService service = Executors.newFixedThreadPool(trainsNumber);
        TrainTraffic traffic = TrainTraffic.getInstance();
        List<Tunnel> tunnels = new TunnelGenerator().generate(tunnelsNumber, maxTunnelCapacity, oneDirectionTrainLimit);
        traffic.addTunnels(tunnels);
        List<Train> trains = new TrainGenerator().generate(trainsNumber, traffic);
        trains.forEach(service :: submit);
        service.shutdown();
    }
}