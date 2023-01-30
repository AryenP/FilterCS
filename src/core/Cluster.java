package core;

import java.util.ArrayList;

public class Cluster {
    private ArrayList<Point> colorPointList;
    private Point center;

    public Cluster() {
        colorPointList = new ArrayList<>();
        center = new Point((short)(Math.random()*256),(short)(Math.random()*256),(short)(Math.random()*256));
    }

    public void add(Point p) {
        colorPointList.add(p);
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void calculateCenter() {
        int rSum=0,gSum=0,bSum=0;
        for (Point curPoint: colorPointList) {
            rSum+=curPoint.getR();
            gSum+=curPoint.getG();
            bSum+=curPoint.getB();
        }
        if (colorPointList.size()>0) {
            center.setR((short) (rSum / colorPointList.size()));
            center.setG((short) (gSum / colorPointList.size()));
            center.setB((short) (bSum / colorPointList.size()));
        }
    }

    public void clearPoints() {
        colorPointList.clear();
    }

    public ArrayList<Point> getColorPointList() {
        return colorPointList;
    }

    public Point getCenter() {
        return center;
    }
}