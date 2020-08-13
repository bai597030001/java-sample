package com.example.designmode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: java-sample
 * @description: 自定义实现观察者模式
 * @author: baijd-a
 * @create: 2020-08-11 19:42
 **/
public class ObserverPush {
    public static void main(String[] args) {
        // 主题
        WeatherData weatherData = new WeatherData();
        // 观察者: 当前状态布告板
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        // 观察者: 统计布告版
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        // 观察者: 预测布告板
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}

/**
 * @Author baijd-a
 * @Description 主题
 * @Date 19:43 2020/8/11
 **/
interface Subject {
    //注册观察者
    void registerObserver(Observer observer);

    //删除观察者
    void deleteObserver(Observer observer);

    //当主题发生数据变化时，通知所有观察
    void notifyObservers();
}

/**
 * @Author baijd-a
 * @Description 主题的实现：WeatherData
 * @Date 10:52 2020/8/12
 **/
class WeatherData implements Subject {
    List<Observer> observers = new ArrayList<>();
    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        observers.forEach(o -> o.update(temperature, humidity, pressure));
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

interface Observer {
    /**
     * update:当气象站的观测数据发生改变时，这个方法会被调用
     *
     * @param temp     温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    void update(float temp, float humidity, float pressure);

    /**
     * @Author baijd-a
     * @Description 当布告板需要展示时，调用此方法时
     **/
    void display();
}

/**
 * @Author baijd-a
 * @Description 当前状态布告板
 * @Date 10:54 2020/8/12
 **/
class CurrentConditionsDisplay implements Observer {
    private Subject weatherData;
    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    //显示方法
    @Override
    public void display() {
        System.out.println("温度：" + temperature + "\n湿度:" + humidity + "\n气压:" + pressure);
    }
}

/**
 * @Author baijd-a
 * @Description 统计布告板
 * @Date 11:14 2020/8/12
 **/
class StatisticsDisplay implements Observer {
    private Subject weatherData;
    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("温度：" + temperature + "\n湿度:" + humidity + "\n气压:" + pressure);
    }
}

/**
 * @Author baijd-a
 * @Description 预测布告板
 * @Date 11:15 2020/8/12
 **/
class ForecastDisplay implements Observer {
    private Subject weatherData;
    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    //显示方法
    @Override
    public void display() {
        System.out.println("温度：" + temperature + "\n湿度:" + humidity + "\n气压:" + pressure);
    }
}
