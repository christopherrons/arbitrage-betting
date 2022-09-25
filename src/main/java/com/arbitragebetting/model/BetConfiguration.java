package com.arbitragebetting.model;

import com.arbitragebetting.bets.SpreadBet;
import com.arbitragebetting.enums.BettingType;

import java.util.List;

public record BetConfiguration(BettingType bettingType, List<SpreadBet> brokerBets, double stake) {
}
