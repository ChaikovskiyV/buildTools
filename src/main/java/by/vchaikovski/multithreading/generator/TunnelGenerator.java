package by.vchaikovski.multithreading.generator;

import by.vchaikovski.multithreading.entity.Tunnel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TunnelGenerator {
    static final Logger logger = LogManager.getLogger();
    static final int MIN_CAPACITY = 1;

    public List<Tunnel> generate(int tunnelsNumber, int maxCapacity) {
        List<Tunnel> tunnels = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <= tunnelsNumber; i++) {
            tunnels.add(new Tunnel(i+1, random.nextInt(MIN_CAPACITY, maxCapacity)));
        }
        return tunnels;
    }
}