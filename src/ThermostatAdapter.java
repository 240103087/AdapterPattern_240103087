public class ThermostatAdapter implements SmartDevice {
    private final LegacyThermostat thermostat;

    public ThermostatAdapter(LegacyThermostat thermostat) {
        if (thermostat == null) {
            throw new IllegalArgumentException("Thermostat cannot be null");
        }
        this.thermostat = thermostat;
    }

    public void turnOn() {
        String currentDial = thermostat.checkDial();
        if ("IDLE".equalsIgnoreCase(currentDial)) {
            thermostat.rotateDial("LOW");
        }
    }

    public void turnOff() {
        thermostat.rotateDial("IDLE");
    }

    public boolean isOn() {
        String state = thermostat.checkDial();
        if (state == null) {
            return false;
        }
        return "LOW".equalsIgnoreCase(state) ||
                "MEDIUM".equalsIgnoreCase(state) ||
                "MAX".equalsIgnoreCase(state);
    }

    public int getPowerPercent() {
        String state = thermostat.checkDial();
        if (state == null) {
            return -1; // Fault sentinel
        }
        switch (state.toUpperCase()) {
            case "IDLE":
                return 0;
            case "LOW":
                return 33;
            case "MEDIUM":
                return 66;
            case "MAX":
                return 100;
            default:
                return -1;
        }
    }
}
