package com.nishan.motionsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView txtNumber;
    SensorManager sm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber=findViewById(R.id.txt_number);
        //initialize sensor object
        sm=(SensorManager)this.getSystemService(SENSOR_SERVICE);

        sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
          float value[]=event.values;
          float x=value[0];
          float y=value[1];
          float z=value[2];

            Toast.makeText(this,"x="+x+" y=" +y+"z="+z , Toast.LENGTH_SHORT).show();
          float asr=(x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);
          if(asr>=2){
              Random r=new Random();
              int i=r.nextInt(2000);
              txtNumber.setText(""+i);
          }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
