package com.example.designmode.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: java-sample
 * @description: 桥接模式
 * @author: baijd-a
 * @create: 2020-07-28 20:09
 **/
abstract class AbstractCar {
    protected Transmission gear;
    public abstract void run();
    public void setTransmission(Transmission gear) {
        this.gear = gear;
    }
}

class BMWCar extends AbstractCar{
    private static final Logger LOG = LoggerFactory.getLogger(BMWCar.class);
    @Override
    public void run() {
        gear.gear();
        LOG.info("BMW is running");
    };
}

class BenZCar extends AbstractCar{
    private static final Logger LOG = LoggerFactory.getLogger(BenZCar.class);
    @Override
    public void run() {
        gear.gear();
        LOG.info("BenZCar is running");
    };
}

class LandRoverCar extends AbstractCar{
    private static final Logger LOG = LoggerFactory.getLogger(LandRoverCar.class);
    @Override
    public void run() {
        gear.gear();
        LOG.info("LandRoverCar is running");
    }
}

abstract class Transmission{
    public abstract void gear();
}

class Manual extends Transmission {
    private static final Logger LOG = LoggerFactory.getLogger(Manual.class);
    @Override
    public void gear() {
        LOG.info("Manual transmission");
    }
}

class Auto extends Transmission {
    private static final Logger LOG = LoggerFactory.getLogger(Auto.class);
    @Override
    public void gear() {
        LOG.info("Auto transmission");
    }
}


public class BridgeTest {
    public static void main(String[] args) {
        Transmission auto = new Auto();
        AbstractCar bmw = new BMWCar();
        bmw.setTransmission(auto);
        bmw.run();

        Transmission manual = new Manual();
        AbstractCar benz = new BenZCar();
        benz.setTransmission(manual);
        benz.run();
    }
}
