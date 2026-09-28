import java.util.List;
public class Main {
    public static void main(String[] args) {
        LegacyBulb rawBulb = new LegacyBulb();
        LegacyThermostat rawThermostat = new LegacyThermostat();

        BulbAdapter bulbAdapter = new BulbAdapter(rawBulb);
        ThermostatAdapter thermostatAdapter = new ThermostatAdapter(rawThermostat);

        List<SmartDevice> devices = List.of(bulbAdapter, thermostatAdapter);
        ModernHub hub = new ModernHub(devices);

        hub.activateAll();
        System.out.println("Average power: " + hub.calculateAveragePowerUsage() + "%");

        hub.emergencyShutdown();
        System.out.println("Power after shutdown: " + hub.calculateAveragePowerUsage() + "%");
    }
}
