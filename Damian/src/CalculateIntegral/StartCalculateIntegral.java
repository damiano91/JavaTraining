package CalculateIntegral;

public class StartCalculateIntegral {
    public static void main(String[] args) {
        CalculateIntegral integral = new CalculateIntegral();
        double start = 0;
        double end = 100;
        double[] function = {1,0};
        System.out.println(integral.calculate(start, end, function));
    }
}
