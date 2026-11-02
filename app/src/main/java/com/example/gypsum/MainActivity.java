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
    private Button clearButton;
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
        clearButton = findViewById(R.id.clearButton);
        calculatorButton = findViewById(R.id.calculatorButton);
        gypsumOutput = findViewById(R.id.gypsumOutput);
        waterOutput = findViewById(R.id.waterOutput);
        svvOutput = findViewById(R.id.svvOutput);

        // обработчик события calculatorButton
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(volumeInput.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.volumeInputError, Toast.LENGTH_SHORT).show();
                } else {
                    double gypsumIndex = 0.62;
                    double waterIndex = 1.88;
                    double svvIndex = 124;

                    double gypsumValue = Integer.parseInt(volumeInput.getText().toString()) / gypsumIndex;
                    double gypsumValueRound = Math.round(gypsumValue * 10) / 10.0;

                    double waterValue = Integer.parseInt(volumeInput.getText().toString()) / waterIndex;
                    double waterValueRound = Math.round(waterValue * 10) / 10.0;

                    double svvValue = Integer.parseInt(volumeInput.getText().toString()) / svvIndex;
                    double svvValueRound = Math.round(svvValue * 100) / 100.0;

                    gypsumOutput.setText(String.valueOf(gypsumValueRound));
                    waterOutput.setText(String.valueOf(waterValueRound));
                    svvOutput.setText(String.valueOf(svvValueRound));
                }
            }
        });

        // обработчик события clearButton
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volumeInput.setText("");
                gypsumOutput.setText("");
                waterOutput.setText("");
                svvOutput.setText("");
            }
        });
    }
}