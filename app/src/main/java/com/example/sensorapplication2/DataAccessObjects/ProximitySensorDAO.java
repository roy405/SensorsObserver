package com.example.sensorapplication2.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sensorapplication2.Models.ProximitySensor;

import java.util.List;

@Dao
public interface ProximitySensorDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addProximitySensor(ProximitySensor proximitySensor);

    //Get All from Database
    @Query("SELECT * FROM proximity_table ORDER BY id ASC")
    LiveData<List<ProximitySensor>> proximitySensorList();

    //Get a single record from Database
    @Query("SELECT * FROM proximity_table WHERE id = :sID")
    ProximitySensor getProximitySensor(int sID);

    //Update a record in the database
    @Query("UPDATE proximity_table SET proximity_sensor_value = :sProximity_Sensor_Value and date_time = :sDate_Time WHERE ID = :sID ")
    void updateProximitySensor(int sID, String sProximity_Sensor_Value, String sDate_Time);

    //Delete record from table
    @Delete
    void delete(ProximitySensor proximitySensor);

    //Delete all from database
    @Delete
    void reset(List<ProximitySensor> proximitySensors);
}
