package com.arbitragebetting.enums;

import com.arbitragebetting.bets.SpreadBet;
import com.arbitragebetting.bets.WinLoseBet;
import com.arbitragebetting.bets.WinLoseDrawBet;
import com.arbitragebetting.model.Bet;

import java.util.List;
import java.util.function.Function;

public enum BettingType {

    WIN_LOSE_DRAW(WinLoseDrawBet::new, 3),
    WIN_LOSE(WinLoseBet::new, 2);

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
