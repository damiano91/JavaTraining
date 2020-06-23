package HoughTransform;

public class XYPoints {
    int[] x;
    int[] y;
    int count;
    int size;

    XYPoints(){
        size=2;
        count =0;
        x = new int[size];
        y = new int[size];
    }

    public void addPoint(int inX, int inY){
        if (count == size) resize();
        x[count] = inX;
        y[count++] = inY;
    }

    public void resize(){
        size *= 2;
        int[] tempx = new int[size];;
        int[] tempy = new int[size];
        System.arraycopy(x,0,tempx,0,count);
        x = tempx;
        System.arraycopy(y,0,tempy,0,count);
        y=tempy;

    }
}
