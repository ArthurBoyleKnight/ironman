package com.stark.armor.implementor;

import com.stark.armor.legacy.LegacyPalladiumCoreGen1;

public class LegacyPalladiumAdapter implements ArcReactor {
    private final LegacyPalladiumCoreGen1 legacyCore;

    public LegacyPalladiumAdapter(LegacyPalladiumCoreGen1 legacyCore) {
        this.legacyCore = legacyCore;
    }

    @Override
    public String outputPower(double gigawatts) {
        int discreteSteps = (int) Math.round(gigawatts);
        try {
            return legacyCore.generateWatts(discreteSteps, true);
        } catch (LegacyPalladiumCoreGen1.PalladiumOverloadException e) {
            throw new IllegalStateException("Palladium Reactor Failure [Code " + e.getErrorCode() + "]: " + e.getMessage(), e);
        }
    }
}