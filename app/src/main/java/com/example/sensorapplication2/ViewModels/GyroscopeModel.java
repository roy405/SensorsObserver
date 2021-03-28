package com.example.sensorapplication2.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sensorapplication2.Repository.GyroscopeRepository;
import com.example.sensorapplication2.Models.Gyroscope;

import java.util.List;

public class GyroscopeModel extends AndroidViewModel {

    private final GyroscopeRepository gyroscopeRepository;
    private final LiveData<List<Gyroscope>> gyroscopes;

    public GyroscopeModel(@NonNull Application application) {
        super(application);
        gyroscopeRepository = new GyroscopeRepository(application);
        gyroscopes = gyroscopeRepository.getGyroscopes();
    }

    public LiveData<List<Gyroscope>> getGyroscopes(){return gyroscopes;}

    public void addGyroscope (Gyroscope gyroscope) {gyroscopeRepository.insert(gyroscope);}
}
