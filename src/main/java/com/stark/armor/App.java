package com.stark.armor;

import com.stark.armor.abstraction.HulkbusterSuit;
import com.stark.armor.abstraction.IronManSuit;
import com.stark.armor.abstraction.StealthSuit;
import com.stark.armor.dynamic.DynamicReactorRegistry;
import com.stark.armor.implementor.ArcReactor;
import com.stark.armor.implementor.BadantiumCoreReactor;
import com.stark.armor.implementor.LegacyPalladiumAdapter;
import com.stark.armor.implementor.VibraniumArcReactor;
import com.stark.armor.legacy.LegacyPalladiumCoreGen1;

public class App {
    public static void main(String[] args) {
        ArcReactor vibranium = new VibraniumArcReactor();
        ArcReactor badantium = new BadantiumCoreReactor();
        
        LegacyPalladiumCoreGen1 legacyCore = new LegacyPalladiumCoreGen1();
        ArcReactor adapter = new LegacyPalladiumAdapter(legacyCore);

        DynamicReactorRegistry registry = new DynamicReactorRegistry();
        registry.registerReactor("stealth", vibranium);
        registry.registerReactor("heavy", badantium);
        registry.registerReactor("legacy", adapter);

        IronManSuit hulkbuster = new HulkbusterSuit(registry.selectReactor("heavy"));
        System.out.println(hulkbuster.deploySubsystem(50.0));

        IronManSuit stealthSuit = new StealthSuit(registry.selectReactor("stealth"));
        System.out.println(stealthSuit.deploySubsystem(10.0));

        stealthSuit.setReactor(registry.selectReactor("legacy"));
        System.out.println(stealthSuit.deploySubsystem(5.0));
    }
}