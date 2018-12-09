package com.example.marchenkosergey.quadraticequation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    double a, b, c;
    String x1 = null, x2 = null;
    EditText aET, bET, cET;
    TextView answerTV;
    Button answerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aET = findViewById(R.id.a);
        bET = findViewById(R.id.b);
        cET = findViewById(R.id.c);
        answerTV = findViewById(R.id.answerTV);
        answerBtn = findViewById(R.id.answerBtn);
        answerBtn.setOnClickListener(this);

    }

    boolean setVariables() {
        try {
            a = Double.valueOf(aET.getText().toString());
            b = Double.valueOf(bET.getText().toString());
            c = Double.valueOf(cET.getText().toString());
        } catch (Exception e) {
            answerTV.setText("Введите больше данных!");
            return false;
        }
        if (Double.valueOf(aET.getText().toString()) != 0) {
            a = Double.valueOf(aET.getText().toString());
        } else {
            answerTV.setText("Это не квадратное уравнение!");
            return false;

        }
        b = Double.valueOf(bET.getText().toString());
        c = Double.valueOf(cET.getText().toString());
        return true;
    }

    void solveEquation() {
        x1 = null;
        x2 = null;
        // ax^2 = 0
        if (b == 0 && c == 0) {
            x1 = "0";
            x2 = "0";
        }

        // ax^2 + bx = 0
        if (b != 0 && c == 0) {
            x1 = "0";
            x2 = "" + -b/a;
        }

        // ax^2 + c == 0
        if (b == 0 && c != 0 && -c/a >= 0) {
            // if √ is int
            if (Math.sqrt(-c/a) % 1 == 0) {
                x1 = "" + Math.sqrt(-c/a) ;
                x2 = "" + -Math.sqrt(-c/a) ;
            } // if √ isn't int
            else {
                x1 = "√" + -c / a;
                x2 = "-√" + -c / a;
            }
        }

        // ax^2 + bx + c = 0
        if (b != 0 && c != 0) {
            double d = b*b + (-4 * a * c);
            if (d > 0 ) {
                // if √ is int
                if (Math.sqrt(d) % 1 == 0) {
                    x1 = "" + (-b + Math.sqrt(d)) / 2 * a ;
                    x2 = "" + (-b - Math.sqrt(d)) / 2 * a ;
                } // if √ isn't int
                else {
                    x1 = "(" + -b + "+ √" + d + ") / " + 2 * a;
                    x2 = "(" + -b + "- √" + d + ") / " + 2 * a;
                }
            } else if (d == 0) {
                x1 = "" + -b / 2 * a;
                x2 = "" + -b / 2 * a;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!setVariables()) return;
        solveEquation();
        String answer;
        if (x1 == null) {
            answer = "Нет действительных корней!";
        } else answer = x1 + "; " + x2;
        answerTV.setText(answer);
    }
}
