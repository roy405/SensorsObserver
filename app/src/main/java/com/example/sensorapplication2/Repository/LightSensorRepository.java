package com.example.sensorapplication2.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sensorapplication2.DB.SensorDB;
import com.example.sensorapplication2.DataAccessObjects.LightSensorDAO;
import com.example.sensorapplication2.Models.LightSensor;

import java.util.List;

public class LightSensorRepository  {

    private LightSensorDAO lightSensorDAO;
    private LiveData<List<LightSensor>> lightSensors;

    public LightSensorRepository(Application application){
        SensorDB sensorDB = SensorDB.getDatabase(application);
        lightSensorDAO = sensorDB.lightSensorDao();
        lightSensors = lightSensorDAO.lightSensorList();
    }

    public LiveData<List<LightSensor>> getLightSensors(){
        return lightSensors;
    }

    public void insert(LightSensor lightSensor){
        SensorDB.databaseWriteExecutor.execute(() ->{
            lightSensorDAO.addLightSensor(lightSensor);
        });
    }

//    private class asyncMethod extends AsyncTask<LightSensor, Void, Void> {
//        private LightSensorDAO newLightSensorDao;
//        public asyncMethod(LightSensorDAO lightSensorDAO) {
//            newLightSensorDao = lightSensorDAO;
//        }
//
//        @Override
//        protected Void doInBackground(LightSensor... lightSensors) {
//            newLightSensorDao.addLightSensor(lightSensors[0]);
//            return null;
//        }
//    }
}
