package com.example.calculatorapp.model;

public class CalculatorImpl implements Calculator{


    @Override
    public double execute(double arg1, double arg2, Operator operator) {
        //обернуть в try, деление на ноль
        switch (operator){
            case ADD:
                return arg1 + arg2;
            case MULT:
                return arg1 * arg2;
            case SUB:
                return arg1 - arg2;
            case DIV:
               if(arg2 != 0.0)
                return arg1 / arg2;

            case PCENT:
                return (arg1 / 100) * arg2;

        }

        return 0.0;
    }


}
