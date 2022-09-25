package com.arbitragebetting.arbitrage;

import com.arbitragebetting.bets.SpreadBet;
import com.arbitragebetting.enums.ArbitrageTypeEnum;
import com.arbitragebetting.model.ArbitrageCalculationResult;
import com.arbitragebetting.model.ArbitrageCalculationResultItem;
import com.arbitragebetting.model.Bet;

import java.util.Arrays;
import java.util.List;

import static com.arbitragebetting.utils.LinearEquationSolver.solveSystem;

public class UnbiasedArbitrageCalculator {

    public ArbitrageCalculationResult calculateArbitrage(SpreadBet spreadBet, double stake) {
        double payout = calculatePayout(spreadBet.getBets(), stake);
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

    private double calculatePayout(List<Bet> bets, double stake) {
        double[][] coefficients = getCoefficients(bets, stake);
        double[] constants = new double[bets.size() + 1];
        Arrays.fill(constants, 0);
        constants[constants.length - 1] = 1;
        return solveSystem(coefficients, constants).getEntry(bets.size());
    }

    private double[][] getCoefficients(List<Bet> bets, double stake) {
        int nrOfColumns = bets.size() + 1;
        int nrOfRows = nrOfColumns;
        double[][] coefficients = new double[nrOfRows][nrOfColumns];
        Arrays.stream(coefficients).forEach(a -> Arrays.fill(a, 0));

        for (int row = 0; row < nrOfRows - 1; row++) {
            coefficients[row][row] = bets.get(row).odds() * stake;
            coefficients[row][nrOfColumns - 1] = -1;
        }

        Arrays.fill(coefficients[nrOfRows - 1], 1);
        coefficients[nrOfRows - 1][nrOfColumns - 1] = 0.0;

        return coefficients;
    }
}
