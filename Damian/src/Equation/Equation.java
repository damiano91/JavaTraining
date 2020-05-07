package Equation;

import java.io.InputStream;

public class Equation {
    float num1,num2;
    char symbol;
    public void readAndSplitEquation(String inputEq){
        int index = 0;
        for(int i=0; i<inputEq.length(); i++){
            if(inputEq.charAt(i) == '-' || inputEq.charAt(i) == '+' || inputEq.charAt(i) == '/' ||inputEq.charAt(i) == '*') {
                index = i;
                break;
            }
        }
        symbol = inputEq.charAt(index);
        num1 = Float.parseFloat(inputEq.substring(0,index));
        num2 = Float.parseFloat(inputEq.substring(index+1));

    }

    public float solveEquation(){
        switch (symbol) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return -1;

    }
}
