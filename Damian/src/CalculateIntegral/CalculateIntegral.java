package CalculateIntegral;

public class CalculateIntegral {
    private double delta = 0.1;

    public double calculate(double start, double end, double[] function){
        double result = 0;
        double funVal;
        while(start < end){
            funVal =0;
            for(int i = 0; i < function.length; i++){
                funVal += function[i] * pow(start, function.length-i-1);
            }
            result = result + funVal*delta;
            start += delta;
        }
        return result;
    }

    private double pow(double x, int powVal){
        double res = 1;
        for(int i =0; i<powVal; i++){
            res *= x;
        }
        return res;
    }
}
