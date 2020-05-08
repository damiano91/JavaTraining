package Equation;

import java.io.InputStream;

public class Equation {
    float num1,num2;
    char symbol;
    public void readAndSplitEquation(String inputEq){
        try{
            int index = -1;
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
        catch(IndexOutOfBoundsException e){
            System.out.println("Improper or lack of arithmetic operation");
        }
        catch (Exception e){
            System.out.println(e);
        }

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
