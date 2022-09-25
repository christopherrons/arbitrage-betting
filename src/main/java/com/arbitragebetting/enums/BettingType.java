package com.arbitragebetting.enums;

import com.arbitragebetting.bets.SpreadBet;
import com.arbitragebetting.bets.ThreeWayBet;
import com.arbitragebetting.bets.TwoWayBet;
import com.arbitragebetting.model.Bet;

import java.util.List;
import java.util.function.Function;

public enum BettingType {

    TWO_WAY_BET(TwoWayBet::new, 2),
    THREE_WAY_BET(ThreeWayBet::new, 3);

    private final Function<List<Bet>, SpreadBet> betBuilder;
    private final int nrOfBets;

    BettingType(Function<List<Bet>, SpreadBet> betBuilder, int nrOfBets) {
        this.betBuilder = betBuilder;
        this.nrOfBets = nrOfBets;
    }

    public SpreadBet buildBet(List<Bet> bets) {
        return this.betBuilder.apply(bets);
    }

    public int getNrOfBets() {
        return nrOfBets;
    }
}
