package com.example.sensorapplication2.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sensorapplication2.Repository.AccelerometerRepository;
import com.example.sensorapplication2.Models.Accelerometer;

import java.util.List;

public class AccelerometerModel extends AndroidViewModel {

    private final AccelerometerRepository accelerometerRepository;
    private LiveData<List<Accelerometer>> accelerometers;

    public AccelerometerModel(@NonNull Application application) {
        super(application);
        accelerometerRepository = new AccelerometerRepository(application);
        accelerometers = accelerometerRepository.getAccelerometers();
    }

    public LiveData<List<Accelerometer>> getAccelerometers(){return accelerometers;}

    public void addAccelerometer(Accelerometer accelerometer) {accelerometerRepository.insert(accelerometer);}
}
