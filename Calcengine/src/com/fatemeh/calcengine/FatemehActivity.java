package com.fatemeh.calcengine;

import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;





public class FatemehActivity extends Activity implements OnItemSelectedListener {

    EditText investment;
    
    TextView futurevalue;
    Spinner years;
    EditText intrestrate;
    Object yrs;
    Integer[] num = new Integer[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatemeh);

        investment = (EditText) findViewById(R.id.editText1);
        intrestrate = (EditText) findViewById(R.id.editText2);
        futurevalue = (TextView) findViewById(R.id.textView4);

        investment.addTextChangedListener(
                new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                futurevalue.setText(" ");
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
        });
        intrestrate.addTextChangedListener(
                new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                futurevalue.setText(" ");
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
        });

        for (int i = 0; i < 20; i++) {
            num[i] = i + 1;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, num);
        years = (Spinner) findViewById(R.id.spinner1);
        years.setAdapter(adapter);
        years.setOnItemSelectedListener(this);

    }

    public void calculate(View view) {
        double init;
        double rate;
        Editable initial = investment.getText();
        Editable rt = intrestrate.getText();

        if (initial.length() != 0 && rt.length() != 0) {

            init = Double.parseDouble(initial.toString());
            init = Math.round(init);
            rate = Double.parseDouble(rt.toString());
            rate = Math.round(rate);

            
            double result = (double) (init * Math.pow((1 + rate / 100), (Integer)yrs));
            result = Math.round(result);

            
            futurevalue.setText("Dollar amount" + ((Double) result).toString());

          
            System.out.println("Total: " + result);
        }
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        int position = years.getSelectedItemPosition();
        yrs = years.getItemAtPosition(position);
        futurevalue.setText(" "); 
    }

    public void onNothingSelected(AdapterView<?> arg0) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.fatemeh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
    	
    	
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

