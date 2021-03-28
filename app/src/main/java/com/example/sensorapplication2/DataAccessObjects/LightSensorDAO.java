package com.example.sensorapplication2.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sensorapplication2.Models.LightSensor;

import java.util.List;

@Dao
public interface LightSensorDAO {

    //Insert to database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     void addLightSensor(LightSensor lightSensor);

    //Get All from Database
    @Query("SELECT * FROM light_table ORDER BY id ASC")
    LiveData<List<LightSensor>> lightSensorList();

    //Get a single record from Database
    @Query("SELECT * FROM light_table WHERE id = :sID")
    LightSensor getLightSensor(int sID);

    //Update a record in the database
    @Query("UPDATE light_table SET light_sensor_value = :sLight_Sensor_Value and date_time = :sDate_Time WHERE ID = :sID ")
    void updateLightSensor(int sID, String sLight_Sensor_Value, String sDate_Time);

    //Delete record from table
    @Delete
    void delete(LightSensor lightSensor);

    //Delete all from database
    @Delete
    void reset(List<LightSensor> lightSensors);

}
