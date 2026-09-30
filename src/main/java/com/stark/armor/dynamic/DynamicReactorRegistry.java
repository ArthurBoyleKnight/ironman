package com.stark.armor.dynamic;

import java.util.HashMap;
import java.util.Map;

import com.stark.armor.implementor.ArcReactor;

public class DynamicReactorRegistry {
    private final Map<String, ArcReactor> registry = new HashMap<>();

    public void registerReactor(String key, ArcReactor reactor) {
        registry.put(key.toLowerCase(), reactor);
    }

    public ArcReactor selectReactor(String missionContext) {
        ArcReactor reactor = registry.get(missionContext.toLowerCase());
        if (reactor == null) {
            throw new IllegalArgumentException("No suited Arc Reactor found for mission context: " + missionContext);
        }
        return reactor;
    }
}