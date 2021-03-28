package com.example.sensorapplication2.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sensorapplication2.Repository.LightSensorRepository;
import com.example.sensorapplication2.Models.LightSensor;

import java.util.List;

public class LightSensorModel extends AndroidViewModel {

    private final LightSensorRepository lightSensorRepository;

    private final LiveData<List<LightSensor>> lightSensors;

    public LightSensorModel(@NonNull Application application) {
        super(application);
        lightSensorRepository = new LightSensorRepository(application);
        lightSensors = lightSensorRepository.getLightSensors();
    }

    public LiveData<List<LightSensor>> getLightSensors(){return lightSensors;}

    public void addLightSensor(LightSensor lightSensor) {lightSensorRepository.insert(lightSensor);}
}
