package com.example.sensorapplication2.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sensorapplication2.DB.SensorDB;
import com.example.sensorapplication2.DataAccessObjects.ProximitySensorDAO;
import com.example.sensorapplication2.Models.ProximitySensor;

import java.util.List;

public class ProximitySensorRepository {

    private ProximitySensorDAO proximitySensorDAO;
    private LiveData<List<ProximitySensor>> proximitySensors;

    public ProximitySensorRepository(Application application){
        SensorDB sensorDB = SensorDB.getDatabase(application);
        proximitySensorDAO = sensorDB.proximitySensorDAO();
        proximitySensors = proximitySensorDAO.proximitySensorList();
    }

    public LiveData<List<ProximitySensor>> getProximitySensors () {return proximitySensors;}

    public void insert(ProximitySensor proximitySensor){
        SensorDB.databaseWriteExecutor.execute(() ->{
            proximitySensorDAO.addProximitySensor(proximitySensor);
        });
    }
}
