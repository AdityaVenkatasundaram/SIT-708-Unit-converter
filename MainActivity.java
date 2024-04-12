package com.example.unitconverterappsit708;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText userInputValueEditText;
    private Spinner sourceUnitSpinner;
    private Spinner destinationUnitSpinner;
    private TextView convertedValueTextView;
    private TextView outputDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInputValueEditText = findViewById(R.id.UserInputValueEditText);
        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        Button convertButton = findViewById(R.id.convertButton);
        convertedValueTextView = findViewById(R.id.convertedValueTextView);
        outputDisplayTextView = findViewById(R.id.outputDisplayTextView);
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(this,
                R.array.unit_options, android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(unitAdapter);
        destinationUnitSpinner.setAdapter(unitAdapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String inputText = userInputValueEditText.getText().toString().trim();
                // Checking if the input value is empty
                if (inputText.isEmpty()) {
                    userInputValueEditText.setError("Please enter a valid input value");
                    return;
                }
                double inputValue;
                try {
                    inputValue = Double.parseDouble(inputText);
                } catch (NumberFormatException e) {
                    userInputValueEditText.setError("Please enter a valid number");
                    return;
                }

                String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
                String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();

                // Checking if the source and destination units are the same if so exit
                if (sourceUnit.equals(destinationUnit)) {
                    outputDisplayTextView.setText("Source and destination units cannot be the same");
                    return;
                }

                //  conversion
                double convertedValue = performConversion(inputValue, sourceUnit, destinationUnit);

                // Display value
                convertedValueTextView.setText(String.valueOf(convertedValue));
            }
        });
    }

    // conversion
    // conversion
// conversion
    private double performConversion(double value, String sourceUnit, String destinationUnit) {
        double convertedValue = 0.0;
        // length
        if (sourceUnit.equals("Inch") && destinationUnit.equals("Centimeter")) {
            convertedValue = value * 2.54;
        } else if (sourceUnit.equals("Inch") && destinationUnit.equals("Foot")) {
            convertedValue = value / 12;
        } else if (sourceUnit.equals("Foot") && destinationUnit.equals("Centimeter")) {
            convertedValue = value * 30.48;
        } else if (sourceUnit.equals("Yard") && destinationUnit.equals("Centimeter")) {
            convertedValue = value * 91.44;
        } else if (sourceUnit.equals("Mile") && destinationUnit.equals("Kilometer")) {
            convertedValue = value * 1.60934;
        }
        // weight
        else if (sourceUnit.equals("Pound") && destinationUnit.equals("Kilogram")) {
            convertedValue = value * 0.453592;
        } else if (sourceUnit.equals("Ounce") && destinationUnit.equals("Gram")) {
            convertedValue = value * 28.3495;
        } else if (sourceUnit.equals("Ton") && destinationUnit.equals("Kilogram")) {
            convertedValue = value * 907.185;
        }
        // temperature
        else if (sourceUnit.equals("Celsius") && destinationUnit.equals("Fahrenheit")) {
            convertedValue = (value * 1.8) + 32;
        } else if (sourceUnit.equals("Fahrenheit") && destinationUnit.equals("Celsius")) {
            convertedValue = (value - 32) / 1.8;
        } else if (sourceUnit.equals("Celsius") && destinationUnit.equals("Kelvin")) {
            convertedValue = value + 273.15;
        } else if (sourceUnit.equals("Kelvin") && destinationUnit.equals("Celsius")) {
            convertedValue = value - 273.15;
        }

        return convertedValue;
    }



    }

