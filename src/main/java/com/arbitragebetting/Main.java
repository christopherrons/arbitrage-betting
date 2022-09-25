package com.arbitragebetting;

import com.arbitragebetting.arbitrage.ArbitrageCalculator;
import com.arbitragebetting.bets.ThreeWayBet;
import com.arbitragebetting.bets.TwoWayBet;
import com.arbitragebetting.enums.BettingType;
import com.arbitragebetting.model.BetConfiguration;

import java.util.List;

import static com.arbitragebetting.utils.ArbitrageResultPrinter.printArbitrageResults;

public class Main {

    public static void main(String[] args) {
        var bet_1 = new TwoWayBet("Svenska Spel", 3.84, 1.24);
        var bet_2 = new TwoWayBet("Hajper", 3.50, 1.27);

        var arbitrageCalculator = new ArbitrageCalculator();
        var result = arbitrageCalculator.calculateUnbiasedArbitrage(
                new BetConfiguration(
                        BettingType.TWO_WAY_BET,
                        List.of(bet_1, bet_2),
                        1000)
        );

        printArbitrageResults(result);
    }
}
