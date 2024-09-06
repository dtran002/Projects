package Projects.Project5;
/*
Program Name: PlaneEficcieny
Purpose:This program is designed to process two sets of input strings representing aircraft efficiency metrics and plane data.
Name:Dylan Tran
Date:03/31/2024
Class Section (CMSC 255, 901)
 */

import java.util.ArrayList;
import java.util.Arrays;

public class PlaneEfficiency {
    public static void main(String[] args) {
        String warehouseMetrics = args[0];
        String warehouseData = args[1];

        String[] metrics = getMetrics(warehouseMetrics);
        double[][] warehouseValues = getPlaneData(warehouseData);

        int[] viablePlanes = findPlaneViability(warehouseValues);
        System.out.println("Planes above the viability criteria are: " + Arrays.toString(viablePlanes));

        String highestMetricsForPlane1 = searchHighestPlaneMetric(warehouseValues, metrics, 1);
        System.out.println("The two highest value efficiency metrics for plane 1 are: " + highestMetricsForPlane1);

        int highestWeightCapacityPlane = searchHighestPlane(warehouseValues, metrics, "weight-capacity");
        System.out.println("The plane with the highest weight-capacity is: " + highestWeightCapacityPlane);
    }

    public static String[] getMetrics(String efficiencyMetrics){
        return efficiencyMetrics.split(",");
    }

    public static double[][] getPlaneData(String planes) {
        String[] planeStrings = planes.split("<>");
        int numRows = planeStrings.length;
        int numCols = planeStrings[0].split(",").length;
        double[][] planeData = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] planeMetrics = planeStrings[i].split(",");
            for (int j = 0; j < numCols; j++) {
                planeData[i][j] = Double.parseDouble(planeMetrics[j]);
            }
        }

        return planeData;
    }

    public static int[] findPlaneViability(double[][] planes) {
        ArrayList<Integer> viablePlanesList = new ArrayList<>();

        for (int i = 0; i < planes.length; i++) {
            double mpg = planes[i][0];
            double volumeCapacity = planes[i][1];
            double weightCapacity = planes[i][2];
            double maintenanceCosts = planes[i][3];
            double landingDistance = planes[i][4];
            double lifespan = planes[i][5];

            double viability = ((mpg * 100 + maintenanceCosts) / (2 * lifespan)) *
                    (volumeCapacity / 700) * (weightCapacity / 83) *
                    (((2.4 - landingDistance) / 2.4) + 0.5);

            if (viability >= 83) {
                viablePlanesList.add(i + 1); // Add 1 to convert index to plane number
            }
        }

        // Convert ArrayList to int array
        int[] viablePlanesArray = new int[viablePlanesList.size()];
        for (int i = 0; i < viablePlanesList.size(); i++) {
            viablePlanesArray[i] = viablePlanesList.get(i);
        }

        return viablePlanesArray;
    }

    public static String searchHighestPlaneMetric(double[][] planes, String[] metrics, int planeNumber) {
        int planeIndex = planeNumber -1;// Convert plane number to array index
        double[] planeValues = planes[planeIndex];

        int highestIndex = 0;
        int secondHighestIndex = 0;

        for (int i = 1; i < planeValues.length; i++) {
            if (planeValues[i] > planeValues[highestIndex]) {
                secondHighestIndex = highestIndex;
                highestIndex = i;
            } else if (planeValues[i] > planeValues[secondHighestIndex]) {
                secondHighestIndex = i;
            }
        }

        return metrics[highestIndex] + " and " + metrics[secondHighestIndex];
    }

    public static int searchHighestPlane(double[][] planes, String[] metrics, String metric) {
        int metricIndex = -1;
        for (int i = 0; i < metrics.length; i++) {
            if (metrics[i].equals(metric)) {
                metricIndex = i;
                break;
            }
        }

        if (metricIndex == -1) {
            System.out.println("Metric not found");
            return -1;
        }

        int highestPlane = -1;
        double highestValue = Double.MIN_VALUE;

        for (int i = 0; i < planes.length; i++) {
            if (planes[i][metricIndex] > highestValue) {
                highestPlane = i + 1; // Convert index to plane number
                highestValue = planes[i][metricIndex];
            }
        }

        return highestPlane;
    }
}
