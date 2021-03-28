package com.example.sensorapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sensorapplication2.Adapter.AccelerometerRecyclerAdapter;
import com.example.sensorapplication2.Models.Accelerometer;
import com.example.sensorapplication2.ViewModels.AccelerometerModel;
import com.example.sensorapplication2.databinding.ActivityAccelerometerBinding;

import java.util.List;

public class AccelerometerActivity extends AppCompatActivity {

    private AccelerometerRecyclerAdapter accelerometerRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAccelerometerBinding activityAccelerometerBinding = ActivityAccelerometerBinding.inflate(getLayoutInflater());
        setContentView(activityAccelerometerBinding.getRoot());

        accelerometerRecyclerAdapter = new AccelerometerRecyclerAdapter(this);

        RecyclerView recyclerView = activityAccelerometerBinding.AccelerometerRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(accelerometerRecyclerAdapter);
        AccelerometerModel accelerometerModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(AccelerometerModel.class);

        accelerometerModel.getAccelerometers().observe(this, new Observer<List<Accelerometer>>() {
            @Override
            public void onChanged(List<Accelerometer> accelerometers) {
                if(accelerometers != null){
                    accelerometerRecyclerAdapter.setAccelerometers(accelerometers);
                    accelerometerRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}