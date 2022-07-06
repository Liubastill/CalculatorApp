package com.example.calculatorapp.ui;


import android.content.Intent;

import com.example.calculatorapp.model.Calculator;
import com.example.calculatorapp.model.Operator;

import java.text.DecimalFormat;


public class CalculatorPresenter {

    private DecimalFormat formater = new DecimalFormat("#.#######");
    private CalculatorView view;
    private Calculator calculator;


    private Double argOne;
    private Double argTwo;
    private Operator selectedOperator;
    private double lastRes;

    private boolean dotPressed = false;
    private boolean equalsPressed = false;
    private int n;
    private boolean dotAlreadyPressed = false;

    public boolean isDotPressed() {
        return dotPressed;
    }

    public void setDotPressed(boolean dotPressed) {
        this.dotPressed = dotPressed;
    }

    public boolean isEqualsPressed() {
        return equalsPressed;
    }

    public void setEqualsPressed(boolean equalsPressed) {
        this.equalsPressed = equalsPressed;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isDotAlreadyPressed() {
        return dotAlreadyPressed;
    }

    public void setDotAlreadyPressed(boolean dotAlreadyPressed) {
        this.dotAlreadyPressed = dotAlreadyPressed;
    }

    public double getLastRes() {
        return lastRes;
    }

    public void setLastRes(double lastRes) {
        this.lastRes = lastRes;
    }


    public void setArgOne(Double argOne) {
        this.argOne = argOne;
    }

    public void setArgTwo(Double argTwo) {
        this.argTwo = argTwo;
    }

    public void setSelectedOperator(Operator selectedOperator) {
        this.selectedOperator = selectedOperator;
    }

    public Double getArgOne() {
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


    public void onDigitPressed(double digit) {
        if (equalsPressed == false) {

            if (dotPressed == false) {

                if (selectedOperator == null) {
                    if (argOne != null) {

                        argOne = argOne * 10 + digit;
                        showFormatted(argOne);
                        lastRes = argOne;
                    } else {
                        argOne = digit;
                        showFormatted(argOne);
                        lastRes = argOne;
                    }

                } else if (selectedOperator != null) {
                    if (argTwo != null) {

                        argTwo = argTwo * 10 + digit;
                        showFormatted(argTwo);
                        lastRes = (double) argTwo;
                    } else {
                        argTwo = digit;
                        showFormatted(argTwo);
                        lastRes = argTwo;
                    }
                }
            } else if (dotPressed == true) {
                if (selectedOperator == null) {
                    if (argOne != null) {
                        argOne = argOne + digit * Math.pow(10, n);
                    } else {
                        argOne = digit * Math.pow(10, n);
                    }
                    showFormatted(argOne);
                    lastRes = argOne;
                    n--;
                } else if (selectedOperator != null) {
                    if (argTwo != null) {
                        argTwo = argTwo + digit * Math.pow(10, n);
                    } else {
                        argTwo = digit * Math.pow(10, n);
                    }
                    showFormatted(argTwo);
                    lastRes = (double) argTwo;
                    n--;
                }
            }

        }else{
            argOne = digit;
            showFormatted(argOne);
            lastRes = argOne;
            selectedOperator = null;
            argTwo = null;

        }
        equalsPressed = false;

    }


    public void onOperatorPressed(Operator operator) {


        if (equalsPressed == true) {

            if (argOne != null & selectedOperator != null & argTwo != null) {

                argTwo = null;
                selectedOperator = operator;

        } else if (argOne != null & selectedOperator != null & argTwo == null) {
            selectedOperator = operator;
        } else if (argOne != null & selectedOperator == null & argTwo == null) {
            selectedOperator = operator;
        } else if (argOne == null & selectedOperator == null & argTwo == null) {
            selectedOperator = null;
        }
            equalsPressed = false;
    }
        if (argOne != null & selectedOperator != null & argTwo != null) {
            if (argOne != null & selectedOperator == Operator.DIV & argTwo == 0) {
                view.showNotice();
                argTwo = null;

            } else {
                argOne = calculator.execute(argOne, argTwo, selectedOperator);
                showFormatted(argOne);
                lastRes = argOne;
                argTwo = null;
                selectedOperator = operator;
            }

        } else if (argOne != null & selectedOperator != null & argTwo == null) {
            selectedOperator = operator;
        } else if (argOne != null & selectedOperator == null & argTwo == null) {
            selectedOperator = operator;
        } else if (argOne == null & selectedOperator == null & argTwo == null) {
            selectedOperator = null;
        }
        dotAlreadyPressed = false;
        dotPressed = false;
        //equalsPressed = false;
        }



/*
    public void onEqualsPressed() {

        if(argOne != null & selectedOperator == Operator.DIV & argTwo == 0){
            view.showNotice();
            argTwo = null;
        }
        if (argOne == null & selectedOperator == null & argTwo == null) {
            equalsPressed = false;
        }
        if (argOne != null & selectedOperator != null & argTwo != null) {

            argOne = calculator.execute(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
            lastRes = argOne;
        }
        dotPressed = false;
        dotAlreadyPressed = false;
        argTwo = null;
        selectedOperator = null;
        equalsPressed = true;



    }*/

    public void onEqualsPressed() {

     if (argOne != null & selectedOperator != null & argTwo != null) {

            if (argOne != null & selectedOperator == Operator.DIV & argTwo == 0) {
                view.showNotice();
                argTwo = null;
                dotPressed = false;
                dotAlreadyPressed = false;
            } else {

                argOne = calculator.execute(argOne, argTwo, selectedOperator);
                showFormatted(argOne);
                lastRes = argOne;
                dotPressed = false;
                dotAlreadyPressed = false;
                equalsPressed = true;
            }
        }

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
