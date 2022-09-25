package com.arbitragebetting.bets;

import com.arbitragebetting.model.Bet;

import java.util.List;

public class TwoWayBet extends SpreadBet {
    public TwoWayBet(String broker, double oddsOne, double oddsTwo) {
        super(List.of(new Bet(broker, oddsOne), new Bet(broker, oddsTwo)));
    }

    public TwoWayBet(List<Bet> bets) {
        super(bets);
    }
}
