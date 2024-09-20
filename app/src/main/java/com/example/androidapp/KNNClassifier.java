package com.example.androidapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KNNClassifier {
    List<DataItem> dataItems;

    public KNNClassifier() {
        this.dataItems = new ArrayList<>();
    }

    public void addDataItem(DataItem di) {
        this.dataItems.add(di);
    }

    private Map<String, Integer> countOccurrences(List<String> list) {
        Map<String, Integer> occurrences = new HashMap<>();
        for (String str : list) {
            Integer currentCount = occurrences.get(str);
            occurrences.put(str, (currentCount == null) ? 1 : currentCount + 1);
        }
        return occurrences;
    }

    public Pair<String, Integer> predict(DataItem unlabelledPoint, Integer numberOfNeighbors) {
        List<Pair<String, Double>> pointDistances = this.dataItems.stream()
                .map((point) -> new Pair<String, Double>(point.getClassifier(), DataItemUtil.getDistance(point, unlabelledPoint)))
                .sorted((d1, d2) -> d1.getValue().compareTo(d2.getValue()))
                .collect(Collectors.toList());

        List<String> closestLabels = pointDistances.subList(0, Math.min(pointDistances.size(), numberOfNeighbors))
                .stream()
                .map((pair) -> pair.getKey())
                .collect(Collectors.toList());

        Map<String, Integer> map = countOccurrences(closestLabels);

        Pair<String, Integer> prediction = new Pair<>("", 0);
        for (Map.Entry<String, Integer> set : map.entrySet()) {
            if (set.getValue() > prediction.getValue()) {
                prediction.setKey(set.getKey());
                prediction.setValue(set.getValue());
            }
        }
        return prediction;
    }
}
