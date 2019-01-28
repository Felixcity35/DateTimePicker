package com.example.felixcity.datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements  DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener
{
    private Button btnDate, btnTime;
    private EditText edtDate, edtTime;

    private int day, mnt, year,hour,minute;
    private int day_x,mnt_x,year_x,hour_x,minute_x;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DBHelper(this);

        edtDate = findViewById(R.id.editTextDate);
        edtTime = findViewById(R.id.editTextTime);

        btnDate = findViewById(R.id.buttonDate);
        btnTime = findViewById(R.id.buttonTime);

        
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicked();

               DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,MainActivity.this,year,mnt,day);
                datePickerDialog.show();
            }
        });



        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,MainActivity.this,hour,minute,true);
                timePickerDialog.show();
            }
        });
    }





    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

          year_x = year;
          mnt_x = month+1 ;  // remove 1 and test the output
          day_x = day;
         Calendar c = Calendar.getInstance();

        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);

    //    TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,MainActivity.this,hour,minute,true);
     //   timePickerDialog.show();


        edtDate.setText(day_x + "/"+(mnt_x)+"/"+year_x);
        String Date = edtDate.getText().toString();

        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.Column_2,Date);
         long id = db.insert(DBHelper.table_name,null,contentValues);
        Toast.makeText(MainActivity.this,"Date Successfully Save in Db",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        hour_x = i;
        minute_x = i1;

        edtTime.setText(hour_x + " : " + minute_x );
         String Time = edtTime.getText().toString();

        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.Column_3,Time);
        long id = db.insert(DBHelper.table_name,null,contentValues);
        Toast.makeText(MainActivity.this,"Time Successfully Save in Db",Toast.LENGTH_LONG).show();
        db.close();
    }

   public  void onClicked(){
       Calendar c = Calendar.getInstance();
       year = c.get(Calendar.YEAR);
       mnt = c.get(Calendar.MONTH);
       day = c.get(Calendar.DAY_OF_MONTH);

   }

        }

