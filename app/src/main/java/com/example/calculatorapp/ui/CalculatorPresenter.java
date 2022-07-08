package com.example.calculatorapp.ui;


import android.content.Intent;

import com.example.calculatorapp.model.Calculator;
import com.example.calculatorapp.model.Operator;

import java.text.DecimalFormat;


public class CalculatorPresenter {

    private final DecimalFormat FORMATER = new DecimalFormat("#.##########");
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
    private boolean plusminPressed = false;


    public boolean isPlusminPressed() {
        return plusminPressed;
    }

    public void setPlusminPressed(boolean plusminPressed) {
        this.plusminPressed = plusminPressed;
    }


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
        if (!plusminPressed) {
            if (!equalsPressed) {
                if (!dotPressed) {
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

                    } else {
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
                } else if (dotPressed) {
                    if (selectedOperator == null) {
                        if (argOne != null) {
                            argOne = argOne + digit * Math.pow(10, n);
                        } else {
                            argOne = digit * Math.pow(10, n);
                        }
                        showFormatted(argOne);
                        lastRes = argOne;
                        n--;
                    } else {
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

            } else {
                argOne = digit;
                showFormatted(argOne);
                lastRes = argOne;
                selectedOperator = null;
                argTwo = null;

            }
            equalsPressed = false;
        } else if (plusminPressed) {
            if (!equalsPressed) {

                if (!dotPressed) {

                    if (selectedOperator == null) {
                        if (argOne != null) {

                            argOne = argOne * 10 + (digit * -1);
                            showFormatted(argOne);
                            lastRes = argOne;
                        } else {
                            argOne = digit * -1;
                            showFormatted(argOne);
                            lastRes = argOne;
                        }

                    } else {
                        if (argTwo != null) {

                            argTwo = argTwo * 10 + (digit * -1);
                            showFormatted(argTwo);
                            lastRes = (double) argTwo;
                        } else {
                            argTwo = digit * -1;
                            showFormatted(argTwo);
                            lastRes = argTwo;
                        }
                    }
                } else if (dotPressed) {
                    if (selectedOperator == null) {
                        if (argOne != null) {
                            argOne = argOne + digit * Math.pow(10, n) * -1;
                        } else {
                            argOne = digit * Math.pow(10, n) * -1;
                        }
                        showFormatted(argOne);
                        lastRes = argOne;
                        n--;
                    } else {
                        if (argTwo != null) {
                            argTwo = argTwo + digit * Math.pow(10, n) * -1;
                        } else {
                            argTwo = digit * Math.pow(10, n) * -1;
                        }
                        showFormatted(argTwo);
                        lastRes = (double) argTwo;
                        n--;
                    }
                }
            } else {
                argOne = digit;
                showFormatted(argOne);
                lastRes = argOne;
                selectedOperator = null;
                argTwo = null;
            }
            equalsPressed = false;
        }
    }


    public void onOperatorPressed(Operator operator) {
        plusminPressed = false;
        if (equalsPressed) {

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
            if (selectedOperator == Operator.DIV & argTwo == 0) {
                view.showNotice();
                argTwo = null;
                lastRes = 0;

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
    }


    public void onEqualsPressed() {

        if (argOne != null & selectedOperator != null & argTwo != null) {

            if (argOne != null & selectedOperator == Operator.DIV & argTwo == 0) {
                view.showNotice();
                argTwo = null;
                dotPressed = false;
                dotAlreadyPressed = false;
                lastRes = 0;
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
        n = -1;
        if (!dotAlreadyPressed) {

            if (!dotPressed) {
                dotPressed = true;
            } else {
                dotPressed = false;
            }
        }
        dotAlreadyPressed = true;
    }

    public void onPlusMinusPressed() {

        if (!equalsPressed) {
            if (!plusminPressed) {
                if (argOne != null & selectedOperator != null & argTwo != null) {
                    argTwo = argTwo * (-1);
                    plusminPressed = true;
                    showFormatted(argTwo);
                    lastRes = argTwo;
                } else if (argOne != null & selectedOperator == null & argTwo == null) {
                    argOne = argOne * (-1);
                    showFormatted(argOne);
                    plusminPressed = true;
                    lastRes = argOne;
                } else {
                    plusminPressed = true;
                }
            } else {
                if (argOne != null & selectedOperator != null & argTwo != null) {
                    argTwo = argTwo * (-1);
                    plusminPressed = false;
                    showFormatted(argTwo);
                    lastRes = argTwo;

                } else if (argOne != null & selectedOperator == null & argTwo == null) {
                    argOne = argOne * (-1);
                    showFormatted(argOne);
                    plusminPressed = false;
                    lastRes = argOne;
                } else {
                    plusminPressed = false;
                }
            }
        } else {
        }
    }

    public void onCencelPressed() {
        argTwo = null;
        argOne = null;
        selectedOperator = null;
        lastRes = 0;
        dotPressed = false;
        equalsPressed = false;
        n = -1;
        dotAlreadyPressed = false;
        plusminPressed = false;
    }


    public void showFormatted(double val) {

        view.showResult((FORMATER.format(val)));

    }
}
