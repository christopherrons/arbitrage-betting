package com.arbitragebetting.spread;

import com.arbitragebetting.arbitrage.ArbitrageCalculator;
import com.arbitragebetting.bets.TwoWayBet;
import com.arbitragebetting.bets.ThreeWayBet;
import com.arbitragebetting.enums.BettingType;
import com.arbitragebetting.model.BetConfiguration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.arbitragebetting.utils.ArbitrageResultPrinter.printArbitrageResults;
import static org.junit.jupiter.api.Assertions.*;

class ArbitrageCalculatorTest {
    private static final String BROKER_1 = "broker_1";
    private static final String BROKER_2 = "broker_2";
    private static final String BROKER_3 = "broker_3";

    @Test
    void test_WinLoseSpreadArbitrage() {
        var bet_1 = new TwoWayBet(BROKER_1, 1.90, 1.90);
        var bet_2 = new TwoWayBet(BROKER_2, 1.40, 2.50);

        var arbitrageCalculator = new ArbitrageCalculator();
        var result = arbitrageCalculator.calculateUnbiasedArbitrage(
                new BetConfiguration(
                        BettingType.TWO_WAY_BET,
                        List.of(bet_1, bet_2),
                        1000)
        );
        assertTrue(result.get(0).isArbitrage());
        assertFalse(result.get(1).isArbitrage());

        assertEquals(79.54, result.get(0).calculateProfit(), 0.01);
        assertEquals(1000, result.get(0).calculateStake(), 0.01);
        assertEquals(1079.5, result.get(0).calculatePayout(), 0.1);

        assertEquals(-193.93, result.get(1).calculateProfit(), 0.01);
        assertEquals(1000, result.get(1).calculateStake(), 0.01);
        assertEquals(806.06, result.get(1).calculatePayout(), 0.1);

        printArbitrageResults(result);
    }

    @Test
    void test_WinLoseDrawSpreadArbitrage() {
        var bet_1 = new ThreeWayBet(BROKER_1, 1.90, 1.90, 1.4);
        var bet_2 = new ThreeWayBet(BROKER_2, 1.40, 2.50, 1.4);
        var bet_3 = new ThreeWayBet(BROKER_3, 1.40, 2.50, 1.4);

        var arbitrageCalculator = new ArbitrageCalculator();
        var result = arbitrageCalculator.calculateUnbiasedArbitrage(
                new BetConfiguration(
                        BettingType.THREE_WAY_BET,
                        List.of(bet_1, bet_2, bet_3),
                        1000)
        );

        printArbitrageResults(result);
    }

}