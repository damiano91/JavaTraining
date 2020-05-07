package Fib;
public class Fib {
    int first;
    int second;
    int temp;
    public Fib(){
        first = 1;
        second = 1;
    }
    public int calculate(int num){
        if(num == 0) return 0;
        else if(num == 1 || num ==2) return 1;
        else {
            for (int i = 3; i <= num; i++) {
                temp = first + second;
                first = second;
                second = temp;
            }
            return temp;
        }
    }
}
