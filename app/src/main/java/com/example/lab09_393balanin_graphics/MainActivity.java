package com.example.lab09_393balanin_graphics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import math.interp;

public class MainActivity extends AppCompatActivity {

    Intent i;
    MySurface s;
    int function_id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i = new Intent(this, ActivityAnother.class);
        startActivityForResult(i, 555); //Start acitivty and send empty intent
        s =findViewById(R.id.mySurface);
    }

    @Override //Get intent with n, xmin, xmax
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 555) {
            if (data != null) {
                s.n =data.getIntExtra("n", 100); //default -> 100
                s.xmin = (float)data.getIntExtra("xmin", -20); //default -> -20
                s.xmax = (float)data.getIntExtra("xmax", 20); //deafult -> 20
                function_id = data.getIntExtra("function_id", -1);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        startDraw();
    }

    public void startDraw()
    {
        s.x= new float[s.n]; //allocate samples
        s.y = new float[s.n];

        switch (function_id)
        {
            case 1:   //y = cos(x) function
                for (int i = 0 ; i < s.n; i ++) //generate samples
                {
                    //while i ranges from 0 to s.n -1 ==> x ranges from 0 to 4 * pi
                    s.x[i] = interp.map(i,s.xmin,s.n-1,0.0f,(float) s.xmax);
                    s.y[i] = (float) Math.cos(s.x[i]);
                }
                break;
            case 2:  //y=tang(x)
                for (int i = 0 ; i < s.n; i ++) //generate samples
                {
                    //while i ranges from 0 to s.n -1 ==> x ranges from 0 to 4 * pi
                    s.x[i] = interp.map(i,s.xmin,s.n-1,0.0f,(float) s.xmax);
                    s.y[i] = (float) Math.tan(s.x[i]);
                }
                break;
            case 3:  //y= |x|
                for (int i = 0 ; i < s.n; i ++) //generate samples
                {
                    //while i ranges from 0 to s.n -1 ==> x ranges from 0 to 4 * pi
                    s.x[i] = interp.map(i,s.xmin,s.n-1,0.0f,(float) s.xmax);
                    s.y[i] = (float) Math.abs(s.x[i]);
                }
                break;
            case 4:  //y=ln(x) y = log(e) x
                for (int i = 0 ; i < s.n; i ++) //generate samples
                {
                    //while i ranges from 0 to s.n -1 ==> x ranges from 0 to 4 * pi
                    s.x[i] = interp.map(i,s.xmin,s.n-1,0.0f,(float) s.xmax);
                    s.y[i] = (float) Math.log(s.x[i]);
                }
                break;
            case 5: // y = Math.Pow(x,2)
                for (int i = 0 ; i < s.n; i ++) //generate samples
                {
                    //while i ranges from 0 to s.n -1 ==> x ranges from 0 to 4 * pi
                    s.x[i] = interp.map(i,s.xmin,s.n-1,0.0f,(float) s.xmax);
                    s.y[i] = (float) Math.pow(s.x[i],2);
                }
                break;
            case 6: //y = Math.Sqrt(1-Math.Pow(x,2) CIRCLE maybe this one wont work
                for (int i = 0 ; i < s.n; i ++) //generate samples
                {
                    //while i ranges from 0 to s.n -1 ==> x ranges from 0 to 4 * pi
                    s.x[i] = interp.map(i,s.xmin,s.n-1,0.0f,(float) s.xmax);
                    s.y[i] = (float) Math.sqrt(1-Math.pow(s.x[i],2));
                }
                break;
            default: return;
        }

        s.update(); //compute min, max values
        s.invalidate(); //invoke onDraw()
    }

    public void onButtonChangeField_Click(View v) {
        i = new Intent(this, ActivityAnother.class);
        startActivityForResult(i, 555); //Start acitivty and send empty intent
    }

}