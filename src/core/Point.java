package core;

public class Point {
    private short r,g,b;
    int row,col;
    public Point(short r,short g,short b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public Point(short r,short g,short b,int row,int col){
        this.r = r;
        this.g = g;
        this.b = b;
        this.row=row;
        this.col=col;
    }

    public short getR() {
        return r;
    }
    public short getG() {
        return g;
    }
    public short getB() {
        return b;
    }

    public void setR(short red){
        r = red;
    }
    public void setG(short green){
        g = green;
    }
    public void setB(short blue){
        b = blue;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public double distanceTo(short red, short green, short blue){
        return (Math.sqrt(red-r)*(red-r)+(green-g)*(green-g)+(blue-b)*(blue-b));
    }
}