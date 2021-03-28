package com.example.sensorapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sensorapplication2.Models.Gyroscope;
import com.example.sensorapplication2.databinding.AccelerometerAndGyroscopeSensorViewBinding;

import java.util.List;

public class GyroscopeRecyclerAdapter extends RecyclerView.Adapter<GyroscopeRecyclerAdapter.ViewHolder>{
    private Context context;
    private List<Gyroscope> mGyroscopes;

    public GyroscopeRecyclerAdapter(Context context){this.context = context;}

    @NonNull
    @Override
    public GyroscopeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AccelerometerAndGyroscopeSensorViewBinding accelerometerAndGyroscopeSensorViewBinding = AccelerometerAndGyroscopeSensorViewBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(accelerometerAndGyroscopeSensorViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GyroscopeRecyclerAdapter.ViewHolder holder, int position) {
        if(mGyroscopes != null){
            Gyroscope gyroscope = mGyroscopes.get(position);
            holder.accelerometerAndGyroscopeSensorViewBinding.dateTime.setText(gyroscope.getDate_time());
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue1.setText(gyroscope.getGyroscope_valueX());
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue2.setText(gyroscope.getGyroscope_valueY());
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue3.setText(gyroscope.getGyroscope_valueZ());
        }
        else{
            holder.accelerometerAndGyroscopeSensorViewBinding.dateTime.setText("xx/xx/xxxx");
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue1.setText("xx");
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue2.setText("xx");
            holder.accelerometerAndGyroscopeSensorViewBinding.gyroAcceleroValue3.setText("xx");

        }
    }

    public void setGyroscope(List<Gyroscope> gyroscopes){
        this.mGyroscopes = gyroscopes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mGyroscopes != null){ return mGyroscopes.size(); }
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
