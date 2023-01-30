package Filters;

import Interfaces.PixelFilter;
import core.Cluster;
import core.DImage;
import core.Point;

import javax.swing.*;
import java.util.ArrayList;

public class kMeansClusterFilter implements PixelFilter {
    private Cluster[] clusters;
    private int k;
    private Point[] prevCenterPoints;

    public kMeansClusterFilter() {
        k = Integer.parseInt(JOptionPane.showInputDialog("How many clusters?"));
        clusters = new Cluster[k];
        prevCenterPoints = new Point[k];
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        clusters = initClusters(k);
        ArrayList<Point> allPoints = makePointList(red,green,blue);

        for (int i = 0; i < k; i++) {
            clusters[i].setCenter(allPoints.get((int)(Math.random()*allPoints.size())));
            prevCenterPoints[i]=new Point((short)0,(short)0,(short)0);
        }
        boolean stable = false;
        do {
            clearClusters();
            assignPointsToClusters(allPoints);
            stable = reCalculateClusterCenters();
        } while(!stable);

        //DRAW
        for (Cluster curCluster: clusters) {
            for (Point p: curCluster.getColorPointList()) {
                red[p.getRow()][p.getCol()] = curCluster.getCenter().getR();
                green[p.getRow()][p.getCol()] = curCluster.getCenter().getG();
                blue[p.getRow()][p.getCol()] = curCluster.getCenter().getB();
                //System.out.println(p.getRow()+","+p.getCol());
            }
        }

        img.setColorChannels(red,green,blue);
        return img;
    }



    public boolean reCalculateClusterCenters() {
        Point[] prevCenters = new Point[k];
        int i=0;
        for (Cluster curCluster: clusters) {
            prevCenters[i]=curCluster.getCenter();
            curCluster.calculateCenter();
            Point curCenter = curCluster.getCenter();
            if (!(prevCenters[i].getR()==curCenter.getR() && prevCenters[i].getG()==curCenter.getG() && prevCenters[i].getB()==curCenter.getB())){
                return false;
            }
            i++;
        }
        return true;
    }

    public void assignPointsToClusters(ArrayList<Point> pointsList) {
        for (Point p: pointsList) {
            ArrayList<Double> clusterDists = new ArrayList<>();
            for (Cluster curCluster: clusters) {
                Point curCenter = curCluster.getCenter();
                clusterDists.add(p.distanceTo(curCenter.getR(),curCenter.getG(),curCenter.getB()));
            }
            getSmallest(clusterDists).add(p);
        }
    }

    public ArrayList<Point> makePointList(short[][] red, short[][] green, short[][] blue) {
        ArrayList<Point> pointList = new ArrayList<>();
        for (int r = 0; r < red.length; r++) {
            for (int c = 0; c < red[0].length; c++) {
                Point newPoint = new Point(red[r][c],green[r][c],blue[r][c],r,c);
                pointList.add(newPoint);
            }
        }
        return pointList;
    }

    public void clearClusters() {
        for (Cluster curCluster:clusters) {
            curCluster.clearPoints();
        }

    }

    public Cluster[] initClusters(int k) {
        Cluster [] clustersList = new Cluster[k];
        for (int i = 0; i < k; i++) {
            Cluster newCluster = new Cluster();
            clustersList[i] = newCluster;
        }
        return clustersList;
    }

    public Cluster getSmallest(ArrayList<Double> clusterDists){
        int smallest = 0;
        for (int i = 0; i < clusterDists.size(); i++) {
            double curDist = clusterDists.get(i);
            if (curDist<clusterDists.get(smallest)){
                smallest=i;
            }
        }
        return clusters[smallest];
    }

}
