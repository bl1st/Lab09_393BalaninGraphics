package com.example.lab09_393balanin_graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import math.arr;
import math.interp;

public class MySurface extends SurfaceView
{
    float xmin; //aabb
    float xmax;
    float ymin;
    float ymax;

    float[] x; //data points
    float[] y;

    int n; //number of points

    Paint p;

    public void update() { //compute min and max (aabb -axis aligned bounding box)

        xmin = arr.min(x, n);
        xmax = arr.max(x,n);
        ymin = arr.min(y,n);
        ymax = arr.max(y,n);
    }

    public MySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setColor(Color.RED);
        setWillNotDraw(false);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        //Paint axis_ordinate = new Paint();
        //axis_ordinate.setColor(Color.GREEN); //Wanted to draw axis and ordinate line, but gave up
        //axis_ordinate.setStrokeWidth(10);
        //canvas.drawLine(,0.0f, xmax, ymax, axis_ordinate);

        int w = canvas.getWidth(); //image dimensions
        int h = canvas.getHeight();

        float x0 = 0.0f, y0 = 0.0f;

        for (int i = 0; i < n; i++) {
            //transform xi, yi (in some units) from world space to screen space (in pixels)
            float x1 = interp.map(x[i], xmin, xmax, 0, w-1);
            float y1 = interp.map(y[i], ymin, ymax, h-1, 0);

            if (i>0) canvas.drawLine(x0,y0,x1,y1,p); //can only draw after 1 iteration

            x0 = x1; //remember last points
            y0 = y1;
        }
        //super.onDraw(canvas);
    }
}
