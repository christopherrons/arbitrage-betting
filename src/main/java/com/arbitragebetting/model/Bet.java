package com.arbitragebetting.model;

public record Bet(String broker, double odds) {

  public double getImpliedProbability() {
      return 1 / odds;
  }

    @Override
    public String toString() {
        return String.format("%s (odds=%s)", broker, odds);
    }
}
