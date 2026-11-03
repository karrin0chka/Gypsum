package com.example.gypsum;

import android.content.Context;
import android.content.Intent;
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

public class costCalculator extends AppCompatActivity {
    private Button
            clearButton,
            calculatorButton,
            returnMainButton;

    private EditText
            gypsumPriceInput,
            gypsumMassInput,
            gypsumContentInput,
            svvPriceInput,
            svvMassInput,
            svvContentInput,
            waxPriceInput,
            waxMassInput,
            waxContentInput,
            fragrancePriceInput,
            fragranceMassInput,
            fragranceContentInput,
            wickPriceInput,
            wickMassInput,
            wickContentInput,
            packagingPriceInput,
            packagingMassInput,
            packagingContentInput;

    private TextView costProductOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_cost_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        clearButton = findViewById(R.id.clearButton);
        calculatorButton = findViewById(R.id.calculatorButton);
        returnMainButton = findViewById(R.id.returnMainButton);

        gypsumPriceInput = findViewById(R.id.gypsumPriceInput);
        gypsumMassInput = findViewById(R.id.gypsumMassInput);
        gypsumContentInput = findViewById(R.id.gypsumContentInput);
        svvPriceInput = findViewById(R.id.svvPriceInput);
        svvMassInput = findViewById(R.id.svvMassInput);
        svvContentInput = findViewById(R.id.svvContentInput);
        waxPriceInput = findViewById(R.id.waxPriceInput);
        waxMassInput = findViewById(R.id.waxMassInput);
        waxContentInput = findViewById(R.id.waxContentInput);
        fragrancePriceInput = findViewById(R.id.fragrancePriceInput);
        fragranceMassInput = findViewById(R.id.fragranceMassInput);
        fragranceContentInput = findViewById(R.id.fragranceContentInput);
        wickPriceInput = findViewById(R.id.wickPriceInput);
        wickMassInput = findViewById(R.id.wickMassInput);
        wickContentInput = findViewById(R.id.wickContentInput);
        packagingPriceInput = findViewById(R.id.packagingPriceInput);
        packagingMassInput = findViewById(R.id.packagingMassInput);
        packagingContentInput = findViewById(R.id.packagingContentInput);

        costProductOutput = findViewById(R.id.costProductOutput);

        // обработчик события calculatorButton
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем системный сервис для работы с методом ввода
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // Скрываем клавиатуру, передавая токен окна текущего фокуса
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                double gypsumPrice,
                        svvPrice,
                        waxPrice,
                        fragrancePrice,
                        wickPrice,
                        packagingPrice;

                gypsumPrice = Double.parseDouble(gypsumPriceInput.getText().toString())
                        / Double.parseDouble(gypsumMassInput.getText().toString())
                        * Double.parseDouble(gypsumContentInput.getText().toString());

                svvPrice = Double.parseDouble(svvPriceInput.getText().toString())
                        / Double.parseDouble(svvMassInput.getText().toString())
                        * Double.parseDouble(svvContentInput.getText().toString());

                waxPrice = Double.parseDouble(waxPriceInput.getText().toString())
                        / Double.parseDouble(waxMassInput.getText().toString())
                        * Double.parseDouble(waxContentInput.getText().toString());

                fragrancePrice = Double.parseDouble(fragrancePriceInput.getText().toString())
                        / Double.parseDouble(fragranceMassInput.getText().toString())
                        * Double.parseDouble(fragranceContentInput.getText().toString());

                wickPrice = Double.parseDouble(wickPriceInput.getText().toString())
                        / Double.parseDouble(wickMassInput.getText().toString())
                        * Double.parseDouble(wickContentInput.getText().toString());

                packagingPrice = Double.parseDouble(packagingPriceInput.getText().toString())
                        / Double.parseDouble(packagingMassInput.getText().toString())
                        * Double.parseDouble(packagingContentInput.getText().toString());

                double sumPrice = gypsumPrice + svvPrice + waxPrice + fragrancePrice + wickPrice + packagingPrice;

                costProductOutput.setText(String.valueOf(Math.round(sumPrice)));
            }
        });

        // обработчик события clearButton
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gypsumPriceInput.setText("");
                gypsumMassInput.setText("");
                gypsumContentInput.setText("");
                svvPriceInput.setText("");
                svvMassInput.setText("");
                svvContentInput.setText("");
                waxPriceInput.setText("");
                waxMassInput.setText("");
                waxContentInput.setText("");
                fragrancePriceInput.setText("");
                fragranceMassInput.setText("");
                fragranceContentInput.setText("");
                wickPriceInput.setText("");
                wickMassInput.setText("");
                wickContentInput.setText("");
                packagingPriceInput.setText("");
                packagingMassInput.setText("");
                packagingContentInput.setText("");
            }
        });

        // обработчик события returnMainButton
        returnMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMainActivity(v);
            }
        });
    }
    public void returnMainActivity(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}