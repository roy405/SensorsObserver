package com.example.sensorapplication2.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "proximity_table")
public class ProximitySensor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "proximity_sensor_value")
    private String proximity_sensor_value;
    @ColumnInfo(name = "date_time")
    private String date_time;

    public ProximitySensor() {
    }

    public ProximitySensor(int id, String proximity_sensor_value, String date_time) {
        this.id = id;
        this.proximity_sensor_value = proximity_sensor_value;
        this.date_time = date_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProximity_sensor_value() {
        return proximity_sensor_value;
    }

    public void setProximity_sensor_value(String proximity_sensor_value) {
        this.proximity_sensor_value = proximity_sensor_value;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
