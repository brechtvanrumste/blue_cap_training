package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity(name = "CAR")
public class Car {


    @Id
    @GeneratedValue
    private Long id;
    private float change_time;
    private String numberplate;
    private int start_hour;
    private int start_minute;
    private int start_second;
    private int start_milisecond;
    private Integer total_start_time;
    private int end_hour;
    private int end_minute;
    private int end_second;
    private int end_milisecond;
    private Integer end_start_time;

    public Car(float change_time, String numberplate, int start_hour, int start_minute, int start_second, int start_milisecond, Integer total_start_time, int end_hour, int end_minute, int end_second, int end_milisecond, Integer end_start_time) {
        this.change_time = change_time;
        this.numberplate = numberplate;
        this.start_hour = start_hour;
        this.start_minute = start_minute;
        this.start_second = start_second;
        this.start_milisecond = start_milisecond;
        this.total_start_time = total_start_time;
        this.end_hour = end_hour;
        this.end_minute = end_minute;
        this.end_second = end_second;
        this.end_milisecond = end_milisecond;
        this.end_start_time = end_start_time;
    }
    public int getSpeed(){
        return Math.round(10/change_time);
    }

    public float getChange_time() {
        return change_time;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public int getStart_hour() {
        return start_hour;
    }

    public int getStart_minute() {
        return start_minute;
    }

    public int getStart_second() {
        return start_second;
    }

    public int getStart_milisecond() {
        return start_milisecond;
    }

    public Integer getTotal_start_time() {
        return total_start_time;
    }

    public int getEnd_hour() {
        return end_hour;
    }

    public int getEnd_minute() {
        return end_minute;
    }

    public int getEnd_second() {
        return end_second;
    }

    public int getEnd_milisecond() {
        return end_milisecond;
    }

    public Integer getEnd_start_time() {
        return end_start_time;
    }

    public Car() {

    }


// standard getters and setters
}
