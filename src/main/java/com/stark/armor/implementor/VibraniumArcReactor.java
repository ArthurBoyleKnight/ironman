package com.stark.armor.implementor;

public class VibraniumArcReactor implements ArcReactor {
    @Override
    public String outputPower(double gigawatts) {
        if (gigawatts <= 0) {
            throw new IllegalArgumentException("Power draw must be positive");
        }
        return "Vibranium core outputting " + gigawatts + " GW with 99.9% energy absorption retention.";
    }
}