package exercise.p06_TirePressureMonitoringSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SensorTest {

    private Sensor sensor;

    @Before
    public void setUp() {
        sensor = new Sensor();
    }

    @Test
    public void testPopNextPressurePsiValueReturnsRandomPressure() {
        double pressure1 = sensor.popNextPressurePsiValue();
        double pressure2 = sensor.popNextPressurePsiValue();
        assertNotEquals(pressure1, pressure2);
    }

}