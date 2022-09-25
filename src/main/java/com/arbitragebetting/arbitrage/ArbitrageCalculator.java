package com.arbitragebetting.arbitrage;

import com.arbitragebetting.model.ArbitrageCalculationResult;
import com.arbitragebetting.model.BetConfiguration;
import com.arbitragebetting.spread.SpreadBetCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class ArbitrageCalculator {

    private final UnbiasedArbitrageCalculator unbiasedArbitrageCalculator = new UnbiasedArbitrageCalculator();
    private final SpreadBetCalculator spreadBetCalculator = new SpreadBetCalculator();

    public List<ArbitrageCalculationResult> calculateUnbiasedArbitrage(BetConfiguration betConfiguration) {
        var spreadBets = spreadBetCalculator.calculate(betConfiguration.brokerBets(), betConfiguration.bettingType());

        return spreadBets.stream()
                .map(spreadBet -> unbiasedArbitrageCalculator.calculateArbitrage(spreadBet, betConfiguration.stake()))
                .collect(Collectors.toList());
    }
}
