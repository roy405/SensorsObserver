package com.example.sensorapplication2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sensorapplication2.Models.LightSensor;
import com.example.sensorapplication2.databinding.LightAndProximitySensorViewBinding;

import java.util.List;

public class LightSensorRecyclerAdapter extends RecyclerView.Adapter<LightSensorRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<LightSensor> mLightSensors;

    public LightSensorRecyclerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public LightSensorRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LightAndProximitySensorViewBinding lightAndProximitySensorViewBinding = LightAndProximitySensorViewBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(lightAndProximitySensorViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LightSensorRecyclerAdapter.ViewHolder holder, int position) {
        if (mLightSensors != null) {
            LightSensor lightSensor = mLightSensors.get(position);
            holder.lightAndProximitySensorViewBinding.dateTime.setText(lightSensor.getDate_time());
            holder.lightAndProximitySensorViewBinding.lightProximityValue.setText(lightSensor.getLight_sensor_value());
        }
        else{
            holder.lightAndProximitySensorViewBinding.dateTime.setText("xx/xx/xxxx");
            holder.lightAndProximitySensorViewBinding.lightProximityValue.setText("xx");
        }

    }

    public void setLightSensors(List<LightSensor> lightSensors){
        this.mLightSensors = lightSensors;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mLightSensors != null) {
            return mLightSensors.size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LightAndProximitySensorViewBinding lightAndProximitySensorViewBinding;
        public ViewHolder(@NonNull LightAndProximitySensorViewBinding lightAndProximitySensorViewBinding) {
            super(lightAndProximitySensorViewBinding.getRoot());
            this.lightAndProximitySensorViewBinding = lightAndProximitySensorViewBinding;
        }
    }

}
