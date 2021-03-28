package com.example.sensorapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sensorapplication2.Adapter.ProximitySensorRecyclerAdapter;
import com.example.sensorapplication2.Models.ProximitySensor;
import com.example.sensorapplication2.ViewModels.ProximitySensorModel;
import com.example.sensorapplication2.databinding.ActivityProximitySensorBinding;

import java.util.List;

public class ProximitySensorActivity extends AppCompatActivity {

    private ProximitySensorRecyclerAdapter proximitySensorRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProximitySensorBinding proximitySensorBinding = ActivityProximitySensorBinding.inflate(getLayoutInflater());
        setContentView(proximitySensorBinding.getRoot());

        proximitySensorRecyclerAdapter = new ProximitySensorRecyclerAdapter(this);

        RecyclerView recyclerView = proximitySensorBinding.ProximityRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(proximitySensorRecyclerAdapter);
        ProximitySensorModel proximitySensorModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ProximitySensorModel.class);

        proximitySensorModel.getProximitySensors().observe(this, new Observer<List<ProximitySensor>>() {
            @Override
            public void onChanged(List<ProximitySensor> proximitySensors) {
                if(proximitySensors != null){
                    proximitySensorRecyclerAdapter.setProximitySensors(proximitySensors);
                    proximitySensorRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}