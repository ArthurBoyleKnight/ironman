package com.stark.armor.legacy;

public class LegacyPalladiumCoreGen1 {
    public static class PalladiumOverloadException extends Exception {
        private final int errorCode;
        public PalladiumOverloadException(String message, int errorCode) {
            super(message);
            this.errorCode = errorCode;
        }
        public int getErrorCode() {
            return errorCode;
        }
    }

    public String generateWatts(int discreteSteps, boolean bypassSafety) throws PalladiumOverloadException {
        if (!bypassSafety) {
            throw new PalladiumOverloadException("Core safety lock active: Palladium toxicity critical", 507);
        }
        if (discreteSteps <= 0) {
            throw new PalladiumOverloadException("Insufficient core reaction steps", 400);
        }
        return "Legacy Palladium Core Step Output: " + (discreteSteps * 100) + " Megawatts.";
    }
}