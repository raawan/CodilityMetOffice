package com.metoffice.codility;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Compute2powerQMul3powerQ {

    public static void main(String[] args) {

        int correct = search(199);
        System.out.println(correct);
    }

    private static int search(int index) {
        long now = System.currentTimeMillis();
        List<Tuple> tuples = new ArrayList<>();
        boolean elementRemoved;
        boolean size200 = false;
        int count = 0;
        SortedSet<Long> sortedSet = new TreeSet<>();
        for (int i = 0; i <= 100; i++) {
            tuples = getPermutattions(i);
            elementRemoved = false;
            for (int j = 0; j < tuples.size(); j++) {
                long calculatedValueOfTuple = calculateValue(tuples.get(j));
                if (sortedSet.size() == 200) {
                    size200 = true;
                    if (sortedSet.last().compareTo(calculatedValueOfTuple) > 0) {
                        sortedSet.remove(sortedSet.last());
                        count++;
                        elementRemoved = true; // go for next iteration to calculate tuples
                        sortedSet.add(calculatedValueOfTuple);
                    }
                } else {
                    sortedSet.add(calculatedValueOfTuple);
                }
            }
            if (!elementRemoved && size200) {
                break;
            }
        }
        System.out.println("removed call times::" + count);
        System.out.println("Cache size = " + sortedSet.size());
        List<Long> sortedList = sortedSet.stream().collect(Collectors.toList());
        long total = System.currentTimeMillis() - now;
        System.out.println("Time for search1 with List=" + total);
        return sortedList.get(index).intValue();
    }

    private static long calculateValue(Tuple tuple) {
        return (long) (Math.pow(2, tuple.p) * Math.pow(3, tuple.q));
    }

    private static List<Tuple> getPermutattions(int noOfElements) {
        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < noOfElements; i++) {
            tuples.add(new Tuple(noOfElements, i));
            tuples.add(new Tuple(i, noOfElements));
        }
        tuples.add(new Tuple(noOfElements, noOfElements));
        return tuples;
    }

    private static class Tuple {
        private Integer p;
        private Integer q;

        Tuple(Integer p, Integer q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public boolean equals(Object o) {
            Tuple obj = (Tuple) o;
            if (this.p.equals(obj.p) && this.q.equals(obj.q)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.p.hashCode() * this.q.hashCode();
        }

        @Override
        public String toString() {
            return "[" + p + "]" + "[" + q + "]";
        }
    }
}

