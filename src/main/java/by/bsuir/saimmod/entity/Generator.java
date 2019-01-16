package by.bsuir.saimmod.entity;

import java.util.Random;

public class Generator {
    private static Random random = new Random();

    public static double getExponentialTime(double parameter)
    {
        double randomValue = random.nextDouble();
        return (-1 / parameter) * Math.log(randomValue);
    }
}
