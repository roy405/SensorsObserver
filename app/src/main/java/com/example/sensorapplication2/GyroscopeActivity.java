package com.example.sensorapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sensorapplication2.Adapter.GyroscopeRecyclerAdapter;
import com.example.sensorapplication2.Models.Gyroscope;
import com.example.sensorapplication2.ViewModels.GyroscopeModel;
import com.example.sensorapplication2.databinding.ActivityGyroscopeBinding;

import java.util.List;

public class GyroscopeActivity extends AppCompatActivity {

    private GyroscopeRecyclerAdapter gyroscopeRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGyroscopeBinding activityGyroscopeBinding = ActivityGyroscopeBinding.inflate(getLayoutInflater());
        setContentView(activityGyroscopeBinding.getRoot());

        gyroscopeRecyclerAdapter = new GyroscopeRecyclerAdapter(this);

        RecyclerView recyclerView = activityGyroscopeBinding.GyroscopeRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(gyroscopeRecyclerAdapter);
        GyroscopeModel gyroscopeModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(GyroscopeModel.class);

        gyroscopeModel.getGyroscopes().observe(this, new Observer<List<Gyroscope>>() {
            @Override
            public void onChanged(List<Gyroscope> gyroscopes) {
                if(gyroscopes!=null){
                    gyroscopeRecyclerAdapter.setGyroscope(gyroscopes);
                    gyroscopeRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}