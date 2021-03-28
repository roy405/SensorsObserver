package com.example.sensorapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;

import com.example.sensorapplication2.ViewModels.LightSensorModel;
import com.example.sensorapplication2.ViewModels.ProximitySensorModel;
import com.example.sensorapplication2.ViewModels.AccelerometerModel;
import com.example.sensorapplication2.ViewModels.GyroscopeModel;
import com.example.sensorapplication2.databinding.ActivityMainBinding;
import com.example.sensorapplication2.Models.Accelerometer;
import com.example.sensorapplication2.Models.Gyroscope;
import com.example.sensorapplication2.Models.LightSensor;
import com.example.sensorapplication2.Models.ProximitySensor;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private ActivityMainBinding sensorBinding;

    private SensorManager newSensorManager;

    private Sensor proximitySensor;
    private Sensor lightSensor;
    private Sensor accelerometer;
    private Sensor gyroscope;

    private LightSensorModel lightSensorModel;
    private ProximitySensorModel proximitySensorModel;
    private AccelerometerModel accelerometerModel;
    private GyroscopeModel gyroscopeModel;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(sensorBinding.getRoot());

        /*Initializing Sensor Manager*/
        newSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        /*Acquiring Specific Sensors and assigning them to specific variables (Sensor-wise)*/
        proximitySensor = newSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        lightSensor = newSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometer = newSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = newSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorBinding.lightSensor.setText(String.valueOf(0));
        sensorBinding.proximitySensor.setText(String.valueOf(0));


        lightSensorModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LightSensorModel.class);

        proximitySensorModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ProximitySensorModel.class);

        accelerometerModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(AccelerometerModel.class);

        gyroscopeModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(GyroscopeModel.class);

        databaseTransaction();
//        Button button = sensorBinding.Button1;
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                String light_sensor_value = sensorBinding.lightSensor.getText().toString();
//                Log.v("SENSE", light_sensor_value);
//            }
//        });

        sensorBinding.lightSensorCardView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, LightSensorActivity.class);
                startActivity(intent);
            }
        });

        sensorBinding.proximitySensorCardView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ProximitySensorActivity.class);
                startActivity(intent);
            }
        });

        sensorBinding.accelerometerCardView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AccelerometerActivity.class);
                startActivity(intent);
            }
        });

        sensorBinding.gyroscopeCardView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, GyroscopeActivity.class);
                startActivity(intent);
            }
        });

    }

    public void databaseTransaction(){
        count++;
        insertLightSensorData();
        insertProximitySensorData();
        insertAccelerometerData();
        insertGyroscopeData();

        refresh(100000);
    }

    private void refresh(int milliseconds){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable(){
            @Override
            public void run(){
                databaseTransaction();
            }
        };
        handler.postDelayed(runnable, milliseconds);
    }

    private void insertLightSensorData(){
        String light_sensor_value = sensorBinding.lightSensor.getText().toString();
        Date currentTime = Calendar.getInstance().getTime();
        String date_time = String.valueOf(currentTime);

        LightSensor lightSensor = new LightSensor(0,light_sensor_value, date_time);
        lightSensorModel.addLightSensor(lightSensor);
    }

    private void insertProximitySensorData(){
        String proximity_sensor_value = sensorBinding.proximitySensor.getText().toString();
        Date currentTime = Calendar.getInstance().getTime();
        String date_time = String.valueOf(currentTime);

        ProximitySensor proximitySensor = new ProximitySensor(0,proximity_sensor_value, date_time);
        proximitySensorModel.addProximitySensor(proximitySensor);
    }

    private void insertAccelerometerData(){
        String accelerometer_X_value = sensorBinding.accelerometer1.getText().toString();
        String accelerometer_Y_value = sensorBinding.accelerometer2.getText().toString();
        String accelerometer_Z_value = sensorBinding.accelerometer3.getText().toString();

        Date currentTime = Calendar.getInstance().getTime();
        String date_time = String.valueOf(currentTime);

        Accelerometer accelerometer = new Accelerometer(0,accelerometer_X_value,accelerometer_Y_value,accelerometer_Z_value, date_time);
        accelerometerModel.addAccelerometer(accelerometer);
    }

    private void insertGyroscopeData(){
        String gyroscope_X_value = sensorBinding.gyroscope1.getText().toString();
        String gyroscope_Y_value = sensorBinding.gyroscope2.getText().toString();
        String gyroscope_Z_value = sensorBinding.gyroscope3.getText().toString();

        Date currentTime = Calendar.getInstance().getTime();
        String date_time = String.valueOf(currentTime);

        Gyroscope gyroscope = new Gyroscope(0,gyroscope_X_value,gyroscope_Y_value,gyroscope_Z_value, date_time);
        gyroscopeModel.addGyroscope(gyroscope);

    }



    @Override
    protected void onStart() {
        super.onStart();

        if(lightSensor != null){
            newSensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(proximitySensor != null) {
            newSensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(accelerometer != null){
            newSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(gyroscope != null){
            newSensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        newSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();

        switch(sensorType){
            case Sensor.TYPE_LIGHT:
                /* Light Sensor Values */
                float lightValue = event.values[0];
                sensorBinding.lightSensor.setText(String.valueOf(lightValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                /*Proximity Sensor Values*/
                float proximityValue = event.values[0];
                sensorBinding.proximitySensor.setText(String.valueOf(proximityValue));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                /*Accelerometer Sensor Values*/
                float accelerometerX = event.values[0];
                float accelerometerY = event.values[1];
                float accelerometerZ = event.values[2];
                sensorBinding.accelerometer1.setText("X-Axis" + String.valueOf(accelerometerX));
                sensorBinding.accelerometer2.setText("Y-Axis" + String.valueOf(accelerometerY));
                sensorBinding.accelerometer3.setText("Z-Axis" + String.valueOf(accelerometerZ));
                break;
            case Sensor.TYPE_GYROSCOPE:
                /*Gyroscope Sensor Values*/
                float gyroscopeX = event.values[0];
                float gyroscopeY = event.values[1];
                float gyroscopeZ = event.values[2];
                sensorBinding.gyroscope1.setText("X-Axis" + String.valueOf(gyroscopeX));
                sensorBinding.gyroscope2.setText("Y-Axis" + String.valueOf(gyroscopeY));
                sensorBinding.gyroscope3.setText("Z-Axis" + String.valueOf(gyroscopeZ));
                break;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}