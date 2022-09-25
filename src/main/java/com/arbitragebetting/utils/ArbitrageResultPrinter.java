package com.arbitragebetting.utils;

import com.arbitragebetting.model.ArbitrageCalculationResult;

import java.util.Comparator;
import java.util.List;

public class ArbitrageResultPrinter {

    public static void printArbitrageResults(List<ArbitrageCalculationResult> arbitrageCalculationResults) {
        arbitrageCalculationResults.sort(Comparator.comparing(ArbitrageCalculationResult::calculateProfit).reversed());

        var bestResult = arbitrageCalculationResults.get(0);
        System.out.printf("Top Result!%nProfit=%s, Payout=%s, Total Stake=%s, Percentile=%s | Bets: %s %n%n", bestResult.calculateProfit(), bestResult.calculatePayout(), bestResult.calculateStake(), bestResult.getProfitPercentile() * 100, bestResult.arbitrageCalculationResultItems());

        System.out.println("All Results!");
        for (int i = 0; i < arbitrageCalculationResults.size(); i++) {
            int index = i + 1;
            var result = arbitrageCalculationResults.get(i);

            System.out.printf("%s. Profit=%s, Payout=%s, Total Stake=%s, Percentile=%s | Details: %s %n", index, result.calculateProfit(), result.calculatePayout(), result.calculateStake(), result.getProfitPercentile() * 100, result);
        }
    }
}
