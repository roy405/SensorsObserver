package com.example.sensorapplication2.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sensorapplication2.DataAccessObjects.AccelerometerDAO;
import com.example.sensorapplication2.DataAccessObjects.GyroscopeDAO;
import com.example.sensorapplication2.DataAccessObjects.LightSensorDAO;
import com.example.sensorapplication2.DataAccessObjects.ProximitySensorDAO;
import com.example.sensorapplication2.Models.Accelerometer;
import com.example.sensorapplication2.Models.Gyroscope;
import com.example.sensorapplication2.Models.LightSensor;
import com.example.sensorapplication2.Models.ProximitySensor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LightSensor.class, ProximitySensor.class, Accelerometer.class, Gyroscope.class}, version = 1, exportSchema = false)
abstract public class SensorDB extends RoomDatabase {

    public abstract LightSensorDAO lightSensorDao();
    public abstract ProximitySensorDAO proximitySensorDAO();
    public abstract AccelerometerDAO accelerometerDAO();
    public abstract GyroscopeDAO gyroscopeDAO();

    private static volatile SensorDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SensorDB getDatabase(final Context context){
        if(INSTANCE == null) {
            synchronized (SensorDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SensorDB.class, "sensor_database").fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
