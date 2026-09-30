package com.stark.armor.abstraction;

import com.stark.armor.implementor.ArcReactor;

public class HulkbusterSuit extends IronManSuit {
    public HulkbusterSuit(ArcReactor reactor) {
        super(reactor);
    }

    @Override
    public String deploySubsystem(double powerDemand) {
        return "Hulkbuster Heavy Actuators Engaged: " + reactor.outputPower(powerDemand * 3.0);
    }
}