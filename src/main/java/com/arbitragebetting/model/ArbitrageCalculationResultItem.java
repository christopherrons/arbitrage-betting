package com.arbitragebetting.model;

public record ArbitrageCalculationResultItem(String broker, double odds, double stake) {


    @Override
    public String toString() {
        return "ArbitrageCalculationResultItem{" +
                "broker='" + broker + '\'' +
                ", odds=" + odds +
                ", stake=" + stake +
                '}';
    }
}
