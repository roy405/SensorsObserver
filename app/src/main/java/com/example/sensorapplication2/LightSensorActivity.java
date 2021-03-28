package com.example.sensorapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.sensorapplication2.Adapter.LightSensorRecyclerAdapter;
import com.example.sensorapplication2.Models.LightSensor;
import com.example.sensorapplication2.ViewModels.LightSensorModel;
import com.example.sensorapplication2.databinding.ActivityLightSensorBinding;
import com.example.sensorapplication2.databinding.ActivityMainBinding;

import java.util.List;

public class LightSensorActivity extends AppCompatActivity {

    private LightSensorRecyclerAdapter lightSensorRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLightSensorBinding lightSensorBinding = ActivityLightSensorBinding.inflate(getLayoutInflater());
        setContentView(lightSensorBinding.getRoot());

        lightSensorRecyclerAdapter = new LightSensorRecyclerAdapter(this);

        RecyclerView recyclerView = lightSensorBinding.LightRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lightSensorRecyclerAdapter);
        LightSensorModel lightSensorModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LightSensorModel.class);

        lightSensorModel.getLightSensors().observe(this, new Observer<List<LightSensor>>() {
            @Override
            public void onChanged(List<LightSensor> lightSensors) {
                if(lightSensors != null) {
                    lightSensorRecyclerAdapter.setLightSensors(lightSensors);
                    lightSensorRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}