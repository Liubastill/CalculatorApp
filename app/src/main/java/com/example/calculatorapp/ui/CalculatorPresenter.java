package com.example.calculatorapp.ui;


import com.example.calculatorapp.model.Calculator;
import com.example.calculatorapp.model.Operator;

import java.text.DecimalFormat;


public class CalculatorPresenter {

    private DecimalFormat formater = new DecimalFormat("#.##");
    private CalculatorView view;
    private Calculator calculator;

    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    public void onDigitPressed(int digit) {

        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showFormatted(argOne);
        } else {
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);
        }
    }

    public void onOperatorPressed(Operator operator) {

        if (selectedOperator != null) {
            argOne = calculator.execute(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
        }
        argTwo = 0.0;

        selectedOperator = operator;
    }

    public void onDotPressed() {

    }

    private void showFormatted(double val) {

        view.showResult((formater.format(val)));

    }


}
