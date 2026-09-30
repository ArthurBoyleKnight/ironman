package com.stark.armor.abstraction;

import com.stark.armor.implementor.ArcReactor;

public class StealthSuit extends IronManSuit {
    public StealthSuit(ArcReactor reactor) {
        super(reactor);
    }

    @Override
    public String deploySubsystem(double powerDemand) {
        return "Stealth Cloaking Matrix Active: " + reactor.outputPower(powerDemand * 0.5);
    }
}