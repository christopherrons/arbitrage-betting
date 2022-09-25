package com.arbitragebetting.arbitrage;

import com.arbitragebetting.bets.SpreadBet;
import com.arbitragebetting.enums.BettingType;
import com.arbitragebetting.model.ArbitrageCalculationResult;
import com.arbitragebetting.spread.SpreadBetCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class ArbitrageCalculator {

    private final UnbiasedArbitrageCalculator unbiasedArbitrageCalculator = new UnbiasedArbitrageCalculator();
    private final SpreadBetCalculator spreadBetCalculator = new SpreadBetCalculator();

    public List<ArbitrageCalculationResult> calculateUnbiasedArbitrage(List<SpreadBet> brokerBets,
                                                                       double stake,
                                                                       BettingType bettingType) {
        var spreadBets = spreadBetCalculator.calculate(brokerBets, bettingType);

        return spreadBets.stream()
                .map(spreadBet -> unbiasedArbitrageCalculator.calculateArbitrage(spreadBet, stake))
                .collect(Collectors.toList());


    }
}
