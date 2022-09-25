package com.arbitragebetting.model;

import com.arbitragebetting.bets.SpreadBet;
import com.arbitragebetting.enums.ArbitrageTypeEnum;

import java.util.List;

public record ArbitrageCalculationResult(double payout,
                                         SpreadBet spreadBet,
                                         ArbitrageTypeEnum arbitrageTypeEnum,
                                         List<ArbitrageCalculationResultItem> arbitrageCalculationResultItems) {

    public boolean isArbitrage() {
        return calculateProfit() > 0;
    }

    public double calculateProfit() {
        return  calculatePayout() - calculateStake();
    }

    public double calculatePayout() {
        return payout;
    }

    public double getProfitPercentile() {
        return calculateProfit() / calculateStake();
    }

    public double calculateStake() {
        return arbitrageCalculationResultItems.stream().mapToDouble(ArbitrageCalculationResultItem::stake).sum();
    }
}
