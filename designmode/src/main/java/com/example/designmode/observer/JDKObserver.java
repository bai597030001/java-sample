package com.example.designmode.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @program: java-sample
 * @description: jdk观察者
 * @author: baijd-a
 * @create: 2020-08-12 12:42
 **/
public class JDKObserver {
    public static void main(String[] args) {
        JDKWeatherData weatherData = new JDKWeatherData();
        Observer currentDisplay = new CurrentDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}

class JDKWeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public JDKWeatherData() {
    }

    public void measurementsChanged() {
        setChanged(); // 标记此 Observable 对象为已改变的对象
        notifyObservers();
    }

    public void setMeasurements(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

// 假设温度、湿度、压强只有大于1时才显示
class CurrentDisplay implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;
    Observable observable;

    public CurrentDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof JDKWeatherData) {
            JDKWeatherData weatherData = (JDKWeatherData) obs;
            // 此处实现自己的拉取逻辑 如：温度、湿度、压强只有大于1时才显示
            float curTemprature = weatherData.getTemperature();
            float curHumidity = weatherData.getHumidity();
            float curPressure = weatherData.getPressure();
            if (curTemprature - 1 > this.temperature
                    || curHumidity - 1 > this.humidity
                    || curPressure - 1 > this.pressure) {
                this.temperature = curTemprature;
                this.humidity = curHumidity;
                this.pressure = curPressure;
                display();
            }
        }
    }

    public void display() {
        System.out.println("current contions: temperature is " + temperature
                + ", humidity is " + humidity + ", pressure is " + pressure);
    }
}
