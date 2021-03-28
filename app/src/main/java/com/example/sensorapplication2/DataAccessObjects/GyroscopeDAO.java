package com.example.sensorapplication2.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sensorapplication2.Models.Gyroscope;


import java.util.List;

@Dao
public interface GyroscopeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addGyroscope(Gyroscope gyroscope);

    //Get All from Database
    @Query("SELECT * FROM gyroscope_table ORDER BY id ASC")
    LiveData<List<Gyroscope>> gyroscopeList();

    //Get a single record from Database
    @Query("SELECT * FROM gyroscope_table WHERE id = :sID")
    Gyroscope getGyroscope(int sID);

    //Update a record in the database
    @Query("UPDATE gyroscope_table SET gyroscope_valueX = :sGyroscope_ValueX and gyroscope_valueY = :sGyroscope_ValueY and gyroscope_valueZ = :sGyroscope_ValueZ  and date_time = :sDate_Time WHERE ID = :sID ")
    void updateGyroscope(int sID, String sGyroscope_ValueX, String sGyroscope_ValueY, String sGyroscope_ValueZ, String sDate_Time);

    //Delete record from table
    @Delete
    void delete(Gyroscope gyroscope);

    //Delete all from database
    @Delete
    void reset(List<Gyroscope> gyroscopes);
}
