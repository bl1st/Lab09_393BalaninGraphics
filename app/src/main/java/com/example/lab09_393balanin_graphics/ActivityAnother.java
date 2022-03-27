package com.example.lab09_393balanin_graphics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityAnother extends AppCompatActivity {

    ArrayAdapter<Functions> adapter;

    TextView text_n;
    TextView text_xmin;
    TextView text_xmax;
    Intent i;
    AlertDialog alert;
    ArrayAdapter<String> adp;
    AlertDialog.Builder builder;

    AlertDialog dlg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        text_n = findViewById(R.id.txt_n);
        text_xmin = findViewById(R.id.txt_xmin);
        text_xmax = findViewById(R.id.txt_xmax);
        i = getIntent();
        String[] s = {"y=cos(x)","y = tan(x)", "y = |x|","y =log e (x)","y = Math.Pow(x,2)","y = Math.Sqrt(1-Math.Pow(x,2) (CIRCLE)"};
        ArrayAdapter<String> adp = new ArrayAdapter<String>(ActivityAnother.this,
                android.R.layout.simple_spinner_item, s);

        Spinner sp = new Spinner(ActivityAnother.this);
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adp);

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAnother.this);

        builder.setView(sp);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            alert.cancel();
            }
        });
        alert = builder.create();

        alert.show();
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int a = position+1;
                i.putExtra("function_id", a); //put chosen function to intent

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                i.putExtra("function_id", 1); //Default fucntion if nothing was chosen

            }
        });




    }

    public void onButtonCompute_Click(View v)
    {
        int _n = Integer.parseInt(text_n.getText().toString());
        int _xmin = Integer.parseInt(text_xmin.getText().toString());
        int _xmax=  Integer.parseInt(text_xmax.getText().toString());
        i.putExtra("n", _n);
        i.putExtra("xmin", _xmin);
        i.putExtra("xmax", _xmax);

        setResult(RESULT_OK, i);
        finish();
    }


}