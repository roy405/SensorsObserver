package com.example.sensorapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sensorapplication2.Models.ProximitySensor;
import com.example.sensorapplication2.databinding.LightAndProximitySensorViewBinding;

import java.util.List;

public class ProximitySensorRecyclerAdapter extends RecyclerView.Adapter<ProximitySensorRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<ProximitySensor> mProximitySensors;

    public ProximitySensorRecyclerAdapter(Context context){this.context = context;}

    @NonNull
    @Override
    public ProximitySensorRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LightAndProximitySensorViewBinding lightAndProximitySensorViewBinding = LightAndProximitySensorViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(lightAndProximitySensorViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProximitySensorRecyclerAdapter.ViewHolder holder, int position) {
        if(mProximitySensors != null){
            ProximitySensor proximitySensor = mProximitySensors.get(position);
            holder.lightAndProximitySensorViewBinding.dateTime.setText(proximitySensor.getDate_time());
            holder.lightAndProximitySensorViewBinding.lightProximityValue.setText(proximitySensor.getProximity_sensor_value());
        }
        else{
            holder.lightAndProximitySensorViewBinding.dateTime.setText("xx/xx/xxxx");
            holder.lightAndProximitySensorViewBinding.lightProximityValue.setText("xx");
        }
    }

    public void setProximitySensors(List<ProximitySensor> proximitySensors){
        this.mProximitySensors = proximitySensors;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mProximitySensors != null){
            return mProximitySensors.size();
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
