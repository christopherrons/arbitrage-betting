package com.arbitragebetting.spread;

import com.arbitragebetting.arbitrage.ArbitrageCalculator;
import com.arbitragebetting.bets.WinLoseBet;
import com.arbitragebetting.enums.BettingType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.arbitragebetting.utils.ArbitrageResultPrinter.printSpreadData;
import static org.junit.jupiter.api.Assertions.*;

class SpreadSpreadBetCalculatorTest {
    private static final String BROKER_1 = "broker_1";
    private static final String BROKER_2 = "broker_2";

    @Test
    void test_WinLoseSpreadArbitrage() {
        var bet_1 = new WinLoseBet(BROKER_1, 1.90, 1.90);
        var bet_2 = new WinLoseBet(BROKER_2, 1.40, 2.50);

        var arbitrageCalculator = new ArbitrageCalculator();
        var result = arbitrageCalculator.calculateUnbiasedArbitrage(
                List.of(bet_1, bet_2),
                1000,
                BettingType.WIN_LOSE);
        assertTrue(result.get(0).isArbitrage());
        assertFalse(result.get(1).isArbitrage());

        printSpreadData(result);
    }
}