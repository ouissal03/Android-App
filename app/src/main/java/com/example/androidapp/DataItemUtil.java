package com.example.androidapp;

import java.util.List;

public class DataItemUtil {
	public static Double getDistance(DataItem item1, DataItem item2) {
		List<Integer> item1Coordinates = item1.getCoordinates();
		List<Integer> item2Coordinates = item2.getCoordinates();

		Double sum = 0.0;
		for (int i = 0; i < item1Coordinates.size(); i++) {
			Integer dimensionDistance = item2Coordinates.get(i) - item1Coordinates.get(i);
			sum += dimensionDistance * dimensionDistance;
		}
		return Math.sqrt(sum);
	}
}
