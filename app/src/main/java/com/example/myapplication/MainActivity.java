package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText edTxt;
    Spinner sp1, sp2;
    TextView res;
    Button btn;

    private static final String API_URL = "https://api.exchangeratesapi.io/latest?base=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTxt = findViewById(R.id.amtAddress);
        sp1 = findViewById(R.id.spFrom);
        sp2 = findViewById(R.id.spTo);
        res = findViewById(R.id.result);
        btn = findViewById(R.id.button);

        String[] from = {"USD", "INR", "EURO", "BDT"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,from);
        sp1.setAdapter(ad1);

        String[] to = {"USD", "INR", "EURO", "BDT"};
        ArrayAdapter ad2 = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,to);
        sp2.setAdapter(ad2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double tot = 0.0;
                Double amt = Double.parseDouble(edTxt.getText().toString());


                if(edTxt.getText().toString() == null) res.setText("Please Enter The Amount");
                try{
                    String fromCurr = sp1.getSelectedItem().toString();
                    String toCurr = sp2.getSelectedItem().toString();

                    if(fromCurr == "USD" && toCurr == "USD" || fromCurr == "INR" && toCurr == "INR"
                            || fromCurr == "EURO" && toCurr == "EURO" || fromCurr == "BDT" && toCurr == "BDT")
                        tot = amt;

                    if(fromCurr == "USD" && toCurr == "INR") tot = amt * 82.02;
                    if(fromCurr == "USD" && toCurr == "EURO") tot = amt * 0.92;
                    if(fromCurr == "USD" && toCurr == "BDT") tot = amt * 108.05;

                    if(fromCurr == "INR" && toCurr == "USD") tot = amt * 0.012;
                    if(fromCurr == "INR" && toCurr == "EURO") tot = amt * 0.011;
                    if(fromCurr == "INR" && toCurr == "BDT") tot = amt * 1.32;

                    if(fromCurr == "EURO" && toCurr == "USD") tot = amt * 1.09;
                    if(fromCurr == "EURO" && toCurr == "INR") tot = amt * 89.36;
                    if(fromCurr == "EURO" && toCurr == "BDT") tot = amt * 117.74;

                    if(fromCurr == "BDT" && toCurr == "USD") tot = amt * 0.0093;
                    if(fromCurr == "BDT" && toCurr == "EURO") tot = amt * 0.0093;
                    if(fromCurr == "BDT" && toCurr == "INR") tot = amt * 0.76;

                    res.setText(amt.toString() + " " + fromCurr + " = " + tot.toString() + " " + toCurr);
//                    edTxt.setText("");
                }
                catch(Error e){
                    res.setText("Error Occured");
                }
            }
        });
    }
}