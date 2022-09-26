package com.arbitragebetting;

import com.arbitragebetting.arbitrage.ArbitrageCalculator;
import com.arbitragebetting.bets.ThreeWayBet;
import com.arbitragebetting.bets.TwoWayBet;
import com.arbitragebetting.enums.BettingType;
import com.arbitragebetting.model.BetConfiguration;

import java.util.List;

import static com.arbitragebetting.enums.BettingType.*;
import static com.arbitragebetting.utils.ArbitrageResultPrinter.printArbitrageResults;

public class Main {

    public static void main(String[] args) {
        var bet_1 = new ThreeWayBet("Svenska Spel", 4.9, 1.64, 4.55);
        var bet_2 = new ThreeWayBet("Betsson", 3.95, 1.70, 4.45);
        var bet_3 = new ThreeWayBet("NordicBet", 3.70, 1.76, 4.40);

        var arbitrageCalculator = new ArbitrageCalculator();
        var result = arbitrageCalculator.calculateUnbiasedArbitrage(
                new BetConfiguration(
                        THREE_WAY_BET,
                        List.of(bet_1, bet_2, bet_3),
                        1000)
        );

        printArbitrageResults(result);
    }
}
