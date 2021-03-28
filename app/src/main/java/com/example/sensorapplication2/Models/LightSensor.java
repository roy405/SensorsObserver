package com.example.sensorapplication2.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "light_table")
public class LightSensor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "light_sensor_value")
    private String light_sensor_value;
    @ColumnInfo(name = "date_time")
    private String date_time;


    public LightSensor() {
    }

    public LightSensor(int id, String light_sensor_value, String date_time) {
        this.id = id;
        this.light_sensor_value = light_sensor_value;
        this.date_time = date_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLight_sensor_value() {
        return light_sensor_value;
    }

    public void setLight_sensor_value(String light_sensor_value) {
        this.light_sensor_value = light_sensor_value;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
