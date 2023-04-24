package exercise.p06_TirePressureMonitoringSystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AlarmTest {

    private static final double LOW_PRESSURE_THRESHOLD = 17;
    private static final double HIGH_PRESSURE_THRESHOLD = 21;


    @Test
    public void testCheckShouldSetAlarmLampWhenOverThreshold() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_THRESHOLD + 1);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testCheckShouldSetAlarmLampWhenBelowThreshold() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_THRESHOLD - 1);
        Alarm alarm = new Alarm(sensor);
//        boolean alarmLampBefore = alarm.getAlarmOn();
        alarm.check();
//        assertTrue(alarm.getAlarmOn() != alarmLampBefore);
        assertTrue(alarm.getAlarmOn());
    }


}