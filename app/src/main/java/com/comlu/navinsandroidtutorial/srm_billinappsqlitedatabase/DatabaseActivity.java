package com.comlu.navinsandroidtutorial.srm_billinappsqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    private  EditText v1;
    private  EditText v2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        v1=(EditText)findViewById(R.id.editText3);
        v2=(EditText)findViewById(R.id.editText4);

        Button submit=(Button)findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int val1 = Integer.parseInt(v1.getText().toString());
                    int val2 = Integer.parseInt(v2.getText().toString());
                    MySQLiteHelper dbHelper=new MySQLiteHelper(DatabaseActivity.this);
                    ItemPrice itemPrice=new ItemPrice();

                    itemPrice.setItemPrice1(val1);
                    itemPrice.setItemPrice2(val2);
                    dbHelper.updatePrice(itemPrice);
                    finish();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "One of the field entry is not entered or Wrongly entered",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_database, menu);
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
