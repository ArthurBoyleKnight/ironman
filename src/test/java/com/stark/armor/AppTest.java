package com.stark.armor;

import com.stark.armor.abstraction.HulkbusterSuit;
import com.stark.armor.abstraction.IronManSuit;
import com.stark.armor.abstraction.StealthSuit;
import com.stark.armor.implementor.ArcReactor;
import com.stark.armor.implementor.LegacyPalladiumAdapter;
import com.stark.armor.legacy.LegacyPalladiumCoreGen1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArmorSystemTest {

    private ArcReactor mockReactor;

    @BeforeEach
    void setUp() {
        mockReactor = Mockito.mock(ArcReactor.class);
    }

    @Test
    void testHulkbusterSuitDelegation() {
        when(mockReactor.outputPower(30.0)).thenReturn("Mocked High Output");
        IronManSuit suit = new HulkbusterSuit(mockReactor);

        String result = suit.deploySubsystem(10.0);

        assertEquals("Hulkbuster Heavy Actuators Engaged: Mocked High Output", result);
        verify(mockReactor, times(1)).outputPower(30.0);
    }

    @Test
    void testStealthSuitDelegation() {
        when(mockReactor.outputPower(5.0)).thenReturn("Mocked Low Output");
        IronManSuit suit = new StealthSuit(mockReactor);

        String result = suit.deploySubsystem(10.0);

        assertEquals("Stealth Cloaking Matrix Active: Mocked Low Output", result);
        verify(mockReactor, times(1)).outputPower(5.0);
    }

    @Test
    void testAdapterSuccessPath() throws Exception {
        LegacyPalladiumCoreGen1 mockLegacy = Mockito.mock(LegacyPalladiumCoreGen1.class);
        when(mockLegacy.generateWatts(10, true)).thenReturn("Legacy Palladium Core Step Output: 1000 Megawatts.");

        ArcReactor adapter = new LegacyPalladiumAdapter(mockLegacy);
        IronManSuit suit = new StealthSuit(adapter);

        String result = suit.deploySubsystem(20.0);

        assertEquals("Stealth Cloaking Matrix Active: Legacy Palladium Core Step Output: 1000 Megawatts.", result);
        verify(mockLegacy, times(1)).generateWatts(10, true);
    }

    @Test
    void testAdapterFailureTranslation() throws Exception {
        LegacyPalladiumCoreGen1 mockLegacy = Mockito.mock(LegacyPalladiumCoreGen1.class);
        when(mockLegacy.generateWatts(anyInt(), anyBoolean()))
                .thenThrow(new LegacyPalladiumCoreGen1.PalladiumOverloadException("Core safety lock active", 507));

        ArcReactor adapter = new LegacyPalladiumAdapter(mockLegacy);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> adapter.outputPower(10.0)
        );

        assertTrue(exception.getMessage().contains("Palladium Reactor Failure [Code 507]"));
        assertTrue(exception.getMessage().contains("Core safety lock active"));
    }
}