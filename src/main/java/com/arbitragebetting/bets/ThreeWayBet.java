package com.arbitragebetting.bets;

import com.arbitragebetting.model.Bet;

import java.util.List;

public class ThreeWayBet extends SpreadBet {
    public ThreeWayBet(String broker, double oddsOne, double oddsTwo , double oddsThree) {
        this(List.of(new Bet(broker, oddsOne), new Bet(broker, oddsTwo),  new Bet(broker, oddsThree)));
    }

    public ThreeWayBet(List<Bet> bets) {
        super(bets);
    }
}
