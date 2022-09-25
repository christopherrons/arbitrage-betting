package com.arbitragebetting.bets;

import com.arbitragebetting.model.Bet;

import java.util.List;

public class WinLoseDrawBet extends SpreadBet {
    public WinLoseDrawBet(String broker, double winOdds, double drawOdds, double loseOdds) {
        this(List.of(new Bet(broker, winOdds), new Bet(broker, drawOdds), new Bet(broker, loseOdds)));
    }

    public WinLoseDrawBet(List<Bet> bets) {
        super(bets);
    }
}
