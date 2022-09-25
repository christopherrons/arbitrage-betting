package com.arbitragebetting.spread;

import com.arbitragebetting.bets.SpreadBet;
import com.arbitragebetting.enums.BettingType;
import com.arbitragebetting.model.Bet;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SpreadBetCalculator {

    public List<SpreadBet> calculate(List<SpreadBet> brokerBets, BettingType bettingType) {
        var permutationIterator = createPermutationIterator(brokerBets.size());
        List<SpreadBet> spreadBets = new ArrayList<>();
        while (permutationIterator.hasNext()) {
            List<Integer> betIndices = permutationIterator.next().subList(0, bettingType.getNrOfBets());
            List<Bet> bets = IntStream.range(0, betIndices.size())
                    .mapToObj(index -> brokerBets.get(betIndices.get(index)).getBetData(index))
                    .toList();
            spreadBets.add(bettingType.buildBet(bets));
        }

        return spreadBets;
    }

    private PermutationIterator<Integer> createPermutationIterator(int nrOfBets) {
        return new PermutationIterator<>(IntStream.range(0, nrOfBets).boxed().toList());
    }

}
