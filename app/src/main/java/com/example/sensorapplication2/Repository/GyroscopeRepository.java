package com.example.sensorapplication2.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sensorapplication2.DB.SensorDB;
import com.example.sensorapplication2.DataAccessObjects.GyroscopeDAO;
import com.example.sensorapplication2.Models.Gyroscope;

import java.util.List;

public class GyroscopeRepository {

    private GyroscopeDAO gyroscopeDAO;
    private LiveData<List<Gyroscope>> gyroscopes;

    public GyroscopeRepository(Application application){
        SensorDB sensorDB = SensorDB.getDatabase(application);
        gyroscopeDAO = sensorDB.gyroscopeDAO();
        gyroscopes = gyroscopeDAO.gyroscopeList();
    }

    public LiveData<List<Gyroscope>> getGyroscopes () {return gyroscopes;}

    public void insert(Gyroscope gyroscope){
        SensorDB.databaseWriteExecutor.execute(() ->{
            gyroscopeDAO.addGyroscope(gyroscope);
        });
    }

}
