package com.arbitragebetting.bets;

import com.arbitragebetting.model.Bet;

import java.util.List;

public abstract class SpreadBet {
    private final List<Bet> bets;

    protected SpreadBet(List<Bet> bets) {
        this.bets = bets;
    }

    public boolean isArbitrageBet() {
        return calculateSpreadImpliedProbability() < 1;
    }

    public double calculateSpreadImpliedProbability() {
        return bets.stream()
                .mapToDouble(Bet::getImpliedProbability)
                .sum();
    }

    public Bet getBetData(int index) {
        return bets.get(index);
    }

    public List<Bet> getBets() {
        return bets;
    }

    @Override
    public String toString() {
        return "SpreadBet{" +
                "bets=" + bets +
                ", spreadImpliedProbability=" + calculateSpreadImpliedProbability() +
                ", spreadImpliedOdds=" + 1 / calculateSpreadImpliedProbability() +
                '}';
    }
}
