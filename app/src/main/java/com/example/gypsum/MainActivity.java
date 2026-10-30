package com.example.gypsum;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText volumeInput;
    private Button calculatorButton;
    private TextView gypsumOutput;
    private TextView waterOutput;
    private TextView svvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // для возможности изменять/работать графический объект из кода
        volumeInput = findViewById(R.id.volumeInput);
        calculatorButton = findViewById(R.id.calculatorButton);
        gypsumOutput = findViewById(R.id.gypsumOutput);
        waterOutput = findViewById(R.id.waterOutput);
        svvOutput = findViewById(R.id.svvOutput);

        // обработчик события
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(volumeInput.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, R.string.volumeInputError, Toast.LENGTH_SHORT).show();
                } else {
                    int gypsumValue = Integer.parseInt(volumeInput.getText().toString()) / 10;
                    String gypsumValueStr = Integer.toString(gypsumValue);

                    int waterValue = Integer.parseInt(volumeInput.getText().toString()) / 5;
                    String waterValueStr = Integer.toString(waterValue);

                    int svvValue = Integer.parseInt(volumeInput.getText().toString()) / 2;
                    String svvValueStr = Integer.toString(svvValue);


                    gypsumOutput.setText(gypsumValueStr);
                    waterOutput.setText(waterValueStr);
                    svvOutput.setText(svvValueStr);
                }
            }
        });
    }
}