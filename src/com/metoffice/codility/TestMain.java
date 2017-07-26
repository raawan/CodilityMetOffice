package com.metoffice.codility;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class TestMain {

   private static int[] s;

    public static void main(String[] args) {

        int correct = search1(199);
        System.out.println(correct);
    }


    public static int search1(int index) {

        long now = System.currentTimeMillis();

        List<BigInteger> elements = new ArrayList<>();
        for(int p=0;p<=100;p++) {
            for(int q =0;q<=100;q++) {
                elements.add(new BigInteger("2").pow(p).multiply(new BigInteger("3").pow(q)));
                //System.out.println("["+p+","+q+"]"+"="+new BigInteger("2").pow(p).multiply(new BigInteger("3").pow(q)));
            }
        }
        Collections.sort(elements);
        System.out.println("SIZe==>" + elements.size());
        //printEle(elements);
        long total = System.currentTimeMillis() - now;
        System.out.println("Time for search1 with List=" + total);
        return elements.get(index).intValue();
    }

    public static int search2(int index) {

        List<BigInteger> elements = new ArrayList<>();
        int count = 0;
        for(int p=0;p<=15;p++) {
            for(int q =0;q<=15;q++) {
                if(++count==200) {
                    break;
                } else {
                    elements.add(new BigInteger("2").pow(p).multiply(new BigInteger("3").pow(q)));
                    //System.out.println(count+":"+"["+p+","+q+"]"+"="+new BigInteger("2").pow(p).multiply(new BigInteger("3").pow(q)));
                }
            }
        }
        Collections.sort(elements);
        //printEle(elements);
        return elements.get(index).intValue();
    }


    public static int search3(int index) {

        long now = System.currentTimeMillis();
        Set<BigInteger> sortedSet = new TreeSet<>();
        sortedSet.add(new BigInteger("1"));
        for (int p=0;p<=100;p++) {
            for (int q=0;q<=100;q++) {
                sortedSet.add(new BigInteger("2").pow(p).multiply(new BigInteger("3").pow(q)));
            }
        }
        List<BigInteger> list = sortedSet.stream().collect(Collectors.toList());
        long total = System.currentTimeMillis() - now;
        System.out.println("search3 with tree=" + total);
        return list.get(index).intValue();
    }


    private static void printEle(List<BigInteger> elements) {
        for(int i =0;i<200;i++) {
            correctValueforPandQ(elements.get(i).intValue());
           // System.out.println("A["+i+"]="+ elements.get(i).toString());
            System.out.println(elements.get(i).toString());
        }
    }

    private static void correctValueforPandQ(int val) {

        for (int p = 0; p < 100; p++) {
            for (int q = 0; q < 100; q++) {
                if ((int)(Math.pow(2, p) * Math.pow(3, q)) == val) {
                    System.out.print("(p=" + p + "," + "q=" + q+")=");
                }
            }
        }
    }
}
