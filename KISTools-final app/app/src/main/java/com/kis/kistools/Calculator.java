package com.kis.kistools;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity {

    String oldNumber="";
    String op = "+";
    boolean isNewOp = true;
    EditText editText;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b0;
    Button btnHome;
    Button bdot;
    Button bequal;
    Button bAC;
    Button bPlusMinus;
    Button bDivide;
    Button bMultiply;
    Button bPercent;
    Button bAdd;
    Button bSubtract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        btnHome = findViewById(R.id.btnHome);
        editText = findViewById(R.id.editText);
        bdot = findViewById(R.id.calcDecimal);
        bSubtract = findViewById(R.id.calcMinus);
        bAC = findViewById(R.id.calcAC);
        bPlusMinus = findViewById(R.id.calcPlusMinus);
        bDivide = findViewById(R.id.calcDivide);
        bMultiply = findViewById(R.id.calcMultiply);
        bPercent = findViewById(R.id.calcPercent);
        bAdd = findViewById(R.id.calcAdd);
        bequal = findViewById(R.id.calcEqual);
        b1 = findViewById(R.id.calc1);
        b2 = findViewById(R.id.calc2);
        b3 = findViewById(R.id.calc3);
        b4 = findViewById(R.id.calc4);
        b5 = findViewById(R.id.calc5);
        b6 = findViewById(R.id.calc6);
        b7 = findViewById(R.id.calc7);
        b8 = findViewById(R.id.calc8);
        b9 = findViewById(R.id.calc9);
        b0 = findViewById(R.id.calc0);

        Typeface ProductSansLight = Typeface.createFromAsset(getAssets(), "fonts/Product Sans Regular.ttf");
        Typeface ProductSansMedium = Typeface.createFromAsset(getAssets(), "fonts/Product Sans Bold.ttf");

        editText.setTypeface(ProductSansMedium);
        b1.setTypeface(ProductSansLight);
        b2.setTypeface(ProductSansLight);
        b3.setTypeface(ProductSansLight);
        b4.setTypeface(ProductSansLight);
        b5.setTypeface(ProductSansLight);
        b6.setTypeface(ProductSansLight);
        b7.setTypeface(ProductSansLight);
        b8.setTypeface(ProductSansLight);
        b9.setTypeface(ProductSansLight);
        bAdd.setTypeface(ProductSansLight);
        bSubtract.setTypeface(ProductSansLight);
        bMultiply.setTypeface(ProductSansLight);
        bDivide.setTypeface(ProductSansLight);
        bAC.setTypeface(ProductSansLight);
        bequal.setTypeface(ProductSansLight);
        bPercent.setTypeface(ProductSansLight);
        bPlusMinus.setTypeface(ProductSansLight);
        bdot.setTypeface(ProductSansLight);
        btnHome.setTypeface(ProductSansMedium);
    }
    public void numberEvent(View view) {

        if (isNewOp)
            editText.setText("");
        isNewOp = false;

        String number = editText.getText().toString();
        switch (view.getId()){
            case R.id.calc1:
                number += "1";
                break;
            case R.id.calc2:
                number += "2";
                break;
            case R.id.calc3:
                number += "3";
                break;
            case R.id.calc4:
                number += "4";
                break;
            case R.id.calc5:
                number += "5";
                break;
            case R.id.calc6:
                number += "6";
                break;
            case R.id.calc7:
                number += "7";
                break;
            case R.id.calc8
                    :
                number += "8";
                break;
            case R.id.calc9:
            number += "9";
                break;
            case R.id.calc0:
            number += "0";
                break;
            case R.id.calcDecimal:
            number += ".";
                break;
            case R.id.calcPlusMinus:
            number = "-"+number;
                break;
        }
        editText.setText((number));
    }

    public void operatorEvent(View view) {

        isNewOp = true;
        oldNumber = editText.getText().toString();
        switch (view.getId()){
            case R.id.calcAdd: op = "+"; break;
            case R.id.calcMinus: op = "-"; break;
            case R.id.calcMultiply: op = "*"; break;
            case R.id.calcDivide: op = "/"; break;
        }
    }

    public void equalEvent (View view){
        String newNumber = editText.getText().toString();
        double result = 0.0;
        switch (op) {
            case "+":
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case "*":
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case "/":
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
        }
        editText.setText(result+"");

    }

    public void clearEvent(View view) {
        editText.setText(("0"));
        isNewOp = true;
    }

    public void percentEvent(View view) {
        double no;
        no = Double.parseDouble(editText.getText().toString())/100;
        editText.setText((no+""));
        isNewOp = true;
    }


    public void exit(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}