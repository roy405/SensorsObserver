package com.example.sensorapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sensorapplication2.Models.Accelerometer;
import com.example.sensorapplication2.databinding.AccelerometerAndGyroscopeSensorViewBinding;

import java.util.List;

public class AccelerometerRecyclerAdapter extends RecyclerView.Adapter<AccelerometerRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Accelerometer> mAccelerometers;

    public AccelerometerRecyclerAdapter(Context context) {this.context = context;}

    @NonNull
    @Override
    public AccelerometerRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AccelerometerAndGyroscopeSensorViewBinding accelerometerAndGyroscopeSensorViewBinding = AccelerometerAndGyroscopeSensorViewBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(accelerometerAndGyroscopeSensorViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AccelerometerRecyclerAdapter.ViewHolder holder, int position) {
        if(mAccelerometers != null){
            Accelerometer accelerometer = mAccelerometers.get(position);
            holder.accelerometerAndGyroscopeSensorViewBinding.dateTime.setText(accelerometer.getDate_time());
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue1.setText(accelerometer.getAccelerometer_valueX());
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue2.setText(accelerometer.getAccelerometer_valueY());
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue3.setText(accelerometer.getAccelerometer_valueZ());
        }
        else{
            holder.accelerometerAndGyroscopeSensorViewBinding.dateTime.setText("xx/xx/xxxx");
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue1.setText("xx");
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue2.setText("xx");
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue3.setText("xx");

        }
    }

    public void setAccelerometers(List<Accelerometer> accelerometers){
        this.mAccelerometers = accelerometers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mAccelerometers != null){ return mAccelerometers.size(); }
        else{ return 0; }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AccelerometerAndGyroscopeSensorViewBinding accelerometerAndGyroscopeSensorViewBinding;
        public ViewHolder(@NonNull AccelerometerAndGyroscopeSensorViewBinding accelerometerAndGyroscopeSensorViewBinding) {
            super(accelerometerAndGyroscopeSensorViewBinding.getRoot());
            this.accelerometerAndGyroscopeSensorViewBinding = accelerometerAndGyroscopeSensorViewBinding;
        }
    }
}
