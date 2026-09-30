package com.stark.armor.implementor;

public class BadantiumCoreReactor implements ArcReactor {
    @Override
    public String outputPower(double gigawatts) {
        if (gigawatts <= 0) {
            throw new IllegalArgumentException("Power draw must be positive");
        }
        return "Badantium core delivering " + gigawatts + " GW with high thermal resistance.";
    }
}