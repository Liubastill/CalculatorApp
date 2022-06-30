package com.example.calculatorapp.ui;


import android.content.Intent;

import com.example.calculatorapp.model.Calculator;
import com.example.calculatorapp.model.Operator;

import java.text.DecimalFormat;


public class CalculatorPresenter {

    private DecimalFormat formater = new DecimalFormat("#.#######");
    private CalculatorView view;
    private Calculator calculator;


    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;
    private double lastRes;
    private boolean dotPressed = false;
    //private boolean dotPressed = false;
    int n;
    private boolean dotAlreadyPressed = false;

    public double getLastRes() {
        return lastRes;
    }

    public void setLastRes(double lastRes) {
        this.lastRes = lastRes;
    }



    public void setArgOne(double argOne) {
        this.argOne = argOne;
    }

    public void setArgTwo(Double argTwo) {
        this.argTwo = argTwo;
    }

    public void setSelectedOperator(Operator selectedOperator) {
        this.selectedOperator = selectedOperator;
    }

    public double getArgOne() {
        return argOne;
    }

    public Double getArgTwo() {
        return argTwo;
    }

    public Operator getSelectedOperator() {
        return selectedOperator;
    }

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    public void onDigitPressed(int digit) {
        if(dotPressed == false){

        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showFormatted(argOne);
            lastRes = argOne;

        } else {
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);
            lastRes =(double) argTwo;
        }}else{
            if (argTwo == null) {
                argOne = argOne + digit*Math.pow(10, n);
                showFormatted(argOne);
                lastRes = argOne;
                n--;
            } else {
                argTwo = argTwo + digit*Math.pow(10, n);
                showFormatted(argTwo);
                lastRes = (double) argTwo;
                n--;
            }
        }
    }

    public void onOperatorPressed(Operator operator) {

        if (selectedOperator != null) {
            argOne = calculator.execute(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
            lastRes = argOne;
        }
        argTwo = 0.0;

        selectedOperator = operator;
        dotPressed = false;
        dotAlreadyPressed = false;
    }


    public void onEqualsPressed() {
        if (selectedOperator != null) {
            argOne = calculator.execute(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
            lastRes = argOne;
            dotPressed = false;
            dotAlreadyPressed = false;
        }
        argTwo = 0.0;//?

    }

    public void onDotPressed() {
        if(dotAlreadyPressed == false) {
            n = -1;
            if (dotPressed == false) {
                dotPressed = true;
            } else {
                dotPressed = false;
            }
        }
        dotAlreadyPressed = true;
    }

    public void onPlusMinusPressed() {


        }

    public void showFormatted(double val) {

        view.showResult((formater.format(val)));

    }


}
