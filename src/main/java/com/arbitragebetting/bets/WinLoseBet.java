package com.arbitragebetting.bets;

import com.arbitragebetting.model.Bet;

import java.util.List;

public class WinLoseBet extends SpreadBet {
    public WinLoseBet(String broker, double winOdds, double loseOdds) {
        super(List.of(new Bet(broker, winOdds), new Bet(broker, loseOdds)));
    }

    public WinLoseBet(List<Bet> bets) {
        super(bets);
    }
}
