package com.nabil.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView countText = findViewById(R.id.countText);

//        By Default
        countText.setText(getCountValue().toString());
        initClickListeners();


    }

    public void initClickListeners() {

        final TextView countText = findViewById(R.id.countText);
        Button minusButton = findViewById(R.id.minusButton);
        Button plusButton = findViewById(R.id.plusButton);
        Button resetButton = findViewById(R.id.resetButton);

        //        Click Listeners
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePlusMinus("+");
                countText.setText(getCountValue().toString());
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePlusMinus("-");

                countText.setText(getCountValue().toString());
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resetData()) {
                    countText.setText(getCountValue().toString());
                } else {
                    countText.setText("Failed!");
                }
            }
        });
    }

    public void handlePlusMinus(String operation) {

        System.out.println(getCountValue());

        if (operation.equals("+")) {
            setData(getCountValue() + 1);
        } else {
            setData(getCountValue() - 1);
        }
    }

    public Integer getCountValue() {
        SharedPreferences sp = this.getSharedPreferences("com.nabil.javaapp", MODE_PRIVATE);
        Integer countValue = sp.getInt("countValue", 0);
        return countValue;
    }

    public void setData(Integer newData) {
        SharedPreferences sp = this.getSharedPreferences("com.nabil.javaapp", MODE_PRIVATE);

        sp.edit().putInt("countValue", newData).apply();
    }

    public Boolean resetData() {
        SharedPreferences sp = this.getSharedPreferences("com.nabil.javaapp", MODE_PRIVATE);

        sp.edit().clear().apply();
        return true;
    }
}
