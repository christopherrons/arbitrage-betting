package com.arbitragebetting.arbitrage;

import com.arbitragebetting.bets.SpreadBet;
import com.arbitragebetting.enums.ArbitrageTypeEnum;
import com.arbitragebetting.model.ArbitrageCalculationResult;
import com.arbitragebetting.model.ArbitrageCalculationResultItem;
import com.arbitragebetting.model.Bet;

import java.util.Comparator;
import java.util.List;

public class UnbiasedArbitrageCalculator {

    public ArbitrageCalculationResult calculateArbitrage(SpreadBet spreadBet, double stake) {
        double maxOdds = spreadBet.getBets().stream().max(Comparator.comparing(Bet::odds)).get().odds();
        double payout = maxOdds * stake;
        return new ArbitrageCalculationResult(
                payout,
                spreadBet,
                ArbitrageTypeEnum.UNBIASED,
                calculateArbitrage(payout, spreadBet)
        );
    }

    private List<ArbitrageCalculationResultItem> calculateArbitrage(double payout, SpreadBet spreadBet) {
        return spreadBet.getBets().stream()
                .map(bet -> new ArbitrageCalculationResultItem(bet.broker(), bet.odds(), payout / bet.odds()))
                .toList();
    }
}
