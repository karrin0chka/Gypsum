package com.example.gypsum;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private EditText gypsumInput;
    private EditText waterInput;
    private EditText svvInput;
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
        gypsumInput = findViewById(R.id.gypsumInput);
        waterInput = findViewById(R.id.waterInput);
        svvInput = findViewById(R.id.svvInput);
        clearButton = findViewById(R.id.clearButton);
        calculatorButton = findViewById(R.id.calculatorButton);
        gypsumOutput = findViewById(R.id.gypsumOutput);
        waterOutput = findViewById(R.id.waterOutput);
        svvOutput = findViewById(R.id.svvOutput);

        // обработчик события calculatorButton
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем системный сервис для работы с методом ввода
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // Скрываем клавиатуру, передавая токен окна текущего фокуса
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                if(volumeInput.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.volumeInputError, Toast.LENGTH_SHORT).show();
                } else {
                    double gypsumIndex,
                            waterIndex,
                            svvIndex;

                    String gypsumInputText = gypsumInput.getText().toString().trim();
                    String waterInputText = waterInput.getText().toString().trim();
                    String svvInputText = svvInput.getText().toString().trim();

                    if(gypsumInputText.isEmpty() &&
                        waterInputText.isEmpty() &&
                        svvInputText.isEmpty()) {
                        gypsumIndex = 0.7;
                        waterIndex = 2.12;
                        svvIndex = 140.0;
                    } else if(!gypsumInputText.isEmpty() &&
                            !waterInputText.isEmpty() &&
                            !svvInputText.isEmpty()) {
                        int gypsumInputValue = Integer.parseInt(gypsumInputText);
                        int waterInputValue = Integer.parseInt(waterInputText);
                        int svvInputValue = Integer.parseInt(svvInputText);

                        int mass = gypsumInputValue + waterInputValue + svvInputValue;
                        double density = 1.907;
                        double volume = mass / density;

                        gypsumIndex = volume / gypsumInputValue;
                        waterIndex = volume / waterInputValue;
                        svvIndex = volume / svvInputValue;
                    } else {
                        Toast.makeText(MainActivity.this, R.string.proportionsInputError, Toast.LENGTH_SHORT).show();
                        return;
                    }

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
                gypsumInput.setText("");
                waterInput.setText("");
                svvInput.setText("");
                gypsumOutput.setText("");
                waterOutput.setText("");
                svvOutput.setText("");
            }
        });
    }
}