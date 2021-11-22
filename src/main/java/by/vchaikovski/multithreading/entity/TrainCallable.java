package by.vchaikovski.multithreading.entity;

import java.util.Random;
import java.util.concurrent.Callable;

public class TrainCallable implements Callable<Boolean> {
    private Boolean direction;
    private Integer number;

    public TrainCallable(int number) {
        this.number = number;
        direction = new Random().nextBoolean();
    }

    public int getNumber() {
        return number;
    }

    @Override
    public Boolean call() {
        return direction;
    }
}