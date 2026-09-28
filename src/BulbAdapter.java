public class BulbAdapter implements SmartDevice {
    private final LegacyBulb bulb;
    private static final int K = 4;
    public BulbAdapter(LegacyBulb bulb) {
        if (bulb == null) {
            throw new IllegalArgumentException("Bulb cannot be null");
        }
        this.bulb = bulb;
    }

    public void turnOn() {
        bulb.setBrightness(255);
    }

    public void turnOff() {
        bulb.setBrightness(0);
    }

    public boolean isOn() {
        if (!bulb.hasPower()) {
            return false;
        }
        return bulb.readBrightness() > 0;
    }

    public int getPowerPercent() {
        if (!bulb.hasPower()) {
            return 0;
        }

        int rawBrightness = bulb.readBrightness();
        if (rawBrightness == 0) {
            return 0;
        }
        int rawPercent = (int) Math.floor((rawBrightness * 100.0) / 255.0);
        int calibratedPercent = rawPercent + K;
        return Math.min(100, calibratedPercent);
    }
}