package com.example.sensorapplication2.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sensorapplication2.Models.Accelerometer;


import java.util.List;
@Dao
public interface AccelerometerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addAccelerometer(Accelerometer accelerometer);

    //Get All from Database
    @Query("SELECT * FROM accelerometer_table ORDER BY id ASC")
    LiveData<List<Accelerometer>> AccelerometerList();

    //Get a single record from Database
    @Query("SELECT * FROM accelerometer_table WHERE id = :sID")
    Accelerometer getAccelerometer(int sID);

    //Update a record in the database
    @Query("UPDATE accelerometer_table SET accelerometer_valueX = :sAccelerometer_ValueX and accelerometer_valueY = :sAccelerometer_ValueY and " +
            "accelerometer_valueZ = :sAccelerometer_ValueZ and date_time = :sDate_Time WHERE ID = :sID ")
    void updateAccelerometer(int sID, String sAccelerometer_ValueX, String sAccelerometer_ValueY, String sAccelerometer_ValueZ, String sDate_Time);

    //Delete record from table
    @Delete
    void delete(Accelerometer accelerometer);

    //Delete all from database
    @Delete
    void reset(List<Accelerometer> Accelerometers);
}
