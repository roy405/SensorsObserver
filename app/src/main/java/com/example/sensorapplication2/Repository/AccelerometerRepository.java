package com.example.sensorapplication2.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sensorapplication2.DB.SensorDB;
import com.example.sensorapplication2.DataAccessObjects.AccelerometerDAO;
import com.example.sensorapplication2.Models.Accelerometer;

import java.util.List;

public class AccelerometerRepository {

    private AccelerometerDAO accelerometerDAO;
    private LiveData<List<Accelerometer>> accelerometers;


    public AccelerometerRepository(Application application){
        SensorDB sensorDB = SensorDB.getDatabase(application);
        accelerometerDAO = sensorDB.accelerometerDAO();
        accelerometers = accelerometerDAO.AccelerometerList();
    }

    public LiveData<List<Accelerometer>> getAccelerometers (){return accelerometers;}

    public void insert(Accelerometer accelerometer){
        SensorDB.databaseWriteExecutor.execute(() ->{
            accelerometerDAO.addAccelerometer(accelerometer);
        });
    }

}
