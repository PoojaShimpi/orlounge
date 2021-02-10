package org.orlounge.common;

public enum BillingStatus {
    FREE_TRIAL(4), EXPIRED(3), PAID(2), EXTENSION(1);

    private int weight;

    BillingStatus(int weight) {
        this.weight = weight;
    }

    public static int compare(BillingStatus a, BillingStatus b) {
        return Integer.compare(a.getWeight(), b.getWeight());
    }

    public int getWeight() {
        return weight;
    }


}
