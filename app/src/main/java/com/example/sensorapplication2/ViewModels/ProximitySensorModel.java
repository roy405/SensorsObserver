package com.example.sensorapplication2.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sensorapplication2.Repository.ProximitySensorRepository;
import com.example.sensorapplication2.Models.ProximitySensor;

import java.util.List;

public class ProximitySensorModel extends AndroidViewModel {

    private final ProximitySensorRepository proximitySensorRepository;
    private final LiveData<List<ProximitySensor>> proximitySensors;

    public ProximitySensorModel(@NonNull Application application){
        super(application);
        proximitySensorRepository = new ProximitySensorRepository(application);
        proximitySensors = proximitySensorRepository.getProximitySensors();
    }

    public LiveData<List<ProximitySensor>> getProximitySensors () {return proximitySensors;}

    public void addProximitySensor (ProximitySensor proximitySensor){proximitySensorRepository.insert(proximitySensor);}

}
