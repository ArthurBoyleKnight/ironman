package com.stark.armor.abstraction;

import com.stark.armor.implementor.ArcReactor;

public abstract class IronManSuit {
    protected ArcReactor reactor;

    public IronManSuit(ArcReactor reactor) {
        this.reactor = reactor;
    }

    public void setReactor(ArcReactor reactor) {
        this.reactor = reactor;
    }

    public abstract String deploySubsystem(double powerDemand);
}