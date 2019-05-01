package com.comlu.navinsandroidtutorial.srm_billinappsqlitedatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText v1;
    EditText v2;
    TextView t1;
    TextView t2;
    int discount=20;
    ItemPrice itemPrice;
    MySQLiteHelper dbHelper;
    //     createNecessaryObjects();

    //prepareObjectsForDisplay();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1=(EditText)findViewById(R.id.editText);
        v2=(EditText)findViewById(R.id.editText1);
        dbHelper =new MySQLiteHelper(this);

        itemPrice=dbHelper.getItemPrice();
        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton) {
                    discount = 10;
                    Toast.makeText(getBaseContext(), "10 % discount selected",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radioButton2) {
                    discount = 20;
                    Toast.makeText(getBaseContext(), "20 % discount selected",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
        Button submit=(Button)findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int val1 = Integer.parseInt(v1.getText().toString());
                    int val2 = Integer.parseInt(v2.getText().toString());
                    double result = val1*itemPrice.getItemPrice1() + val2*itemPrice.getItemPrice2();
                    result = result - result * ((double) discount / 100);

                    Intent i = new Intent(MainActivity.this, ResultActivity.class);
                    i.putExtra("result", result);
                    startActivity(i);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "One of the field entry is not entered or Wrongly entered",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
        Button modify=(Button)findViewById(R.id.button1);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(i);


            }
        });

    }
//objectdisplay
    @Override
    protected void onResume() {
        super.onResume();
        t1.setText(" x " + Integer.toString(itemPrice.getItemPrice1()) + " Rs");
        t2.setText(" x " + Integer.toString(itemPrice.getItemPrice2()) + " Rs");
    }
    // prepareObjectsForDisplay();
    @Override
    protected void onRestart() {
        super.onRestart();
        itemPrice=dbHelper.getItemPrice();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
