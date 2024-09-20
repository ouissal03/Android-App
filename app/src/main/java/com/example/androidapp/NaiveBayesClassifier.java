package com.example.androidapp;

import java.util.ArrayList;
import java.util.List;

public class NaiveBayesClassifier {
    List<DataItem> dataItems;
    public static final Double PY = 0.55;
    public static final Double PX = 0.25;
    public static final Double PC = 0.2;

    public NaiveBayesClassifier() {
        this.dataItems = new ArrayList<>();
    }

    public void addDataItem(DataItem di) {
        this.dataItems.add(di);
    }

    public Pair<String, Double> probability_X(DataItem item) {
        Double probability = 1.0;
        Integer count = 0;
        Integer countX = 0;
        for (int i = 0; i < item.getCoordinates().size(); i++) {
            for (int j = 0; j < dataItems.size(); j++) {
                if (dataItems.get(j).getCoordinates().get(i) == item.getCoordinates().get(i)) {
                    count++;
                    if (dataItems.get(j).getClassifier().equals("drugX")) {
                        countX++;
                    }
                }
            }
            probability = probability * (double) countX / count;
        }
        return new Pair<String, Double>("drugX", probability * PX);
    }

    public Pair<String, Double> probability_Y(DataItem item) {
        Double probability = 1.0;
        Integer count = 0;
        Integer countX = 0;
        for (int i = 0; i < item.getCoordinates().size(); i++) {
            for (int j = 0; j < dataItems.size(); j++) {
                if (dataItems.get(j).getCoordinates().get(i) == item.getCoordinates().get(i)) {
                    count++;
                    if (dataItems.get(j).getClassifier().equals("drugY")) {
                        countX++;
                    }
                }
            }
            probability = probability * (double) countX / count;
        }
        return new Pair<String, Double>("drugY", probability * PY);
    }

    public Pair<String, Double> probability_C(DataItem item) {
        Double probability = 1.0;
        Integer count = 0;
        Integer countX = 0;
        for (int i = 0; i < item.getCoordinates().size(); i++) {
            for (int j = 0; j < dataItems.size(); j++) {
                if (dataItems.get(j).getCoordinates().get(i) == item.getCoordinates().get(i)) {
                    count++;
                    if (dataItems.get(j).getClassifier().equals("drugC")) {
                        countX++;
                    }
                }
            }
            probability = probability * (double) countX / count;
        }
        return new Pair<String, Double>("drugC", probability * PC);
    }

    public Pair<String, Double> predict(DataItem unlabelledPoint) {
        List<Pair<String, Double>> list = new ArrayList<>();
        list.add(probability_Y(unlabelledPoint));
        list.add(probability_C(unlabelledPoint));
        list.add(probability_X(unlabelledPoint));

        Pair<String, Double> prediction = new Pair<>("", 0.0);

        for (int i = 0; i < 3; i++) {
            if (list.get(i).getValue() >= prediction.getValue()) {
                prediction.setKey(list.get(i).getKey());
                prediction.setValue(list.get(i).getValue());
            }
        }

        return prediction;
    }
}