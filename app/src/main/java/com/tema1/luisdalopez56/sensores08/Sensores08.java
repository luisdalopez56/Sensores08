package com.tema1.luisdalopez56.sensores08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Sensores08 extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor acelerometro;

    LinearLayout mainLayout;
    ShapeDrawable mDibujo = new ShapeDrawable();

    public static  int x, y ,centrox = 250, centroy = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores03);

        mainLayout = (LinearLayout) findViewById(R.id.lay);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        MiVistaPersonal miVista = new MiVistaPersonal(this);
        mainLayout.addView(miVista);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = centrox+(int)Math.pow(event.values[1],2);
        y = centroy+(int)Math.pow(event.values[2],2);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    class  MiVistaPersonal extends View{

        static final int ancho = 100, alto = 100;

        public MiVistaPersonal(Context context) {
            super(context);
            mDibujo = new ShapeDrawable(new OvalShape());
            mDibujo.getPaint().setColor(0xff74AC23);
            mDibujo.setBounds(x, y, x + ancho,y + alto);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            RectF oval = new RectF(Sensores08.x,Sensores08.y,Sensores08.x+ancho,Sensores08.y+alto);
            Paint pincel = new Paint();
            pincel.setColor(Color.BLUE);
            canvas.drawOval(oval,pincel);
            invalidate();
        }
    }





}




