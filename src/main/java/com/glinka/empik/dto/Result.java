package com.glinka.empik.dto;

public class Result {

    private int sum;
    private int counter;

    public Result() {
    }

    public Result(int sum, int counter) {
        this.sum = sum;
        this.counter = counter;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
