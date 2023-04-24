package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {


    private static final int ATTACK_POINTS = 30;
    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 10;

    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setUp() {
        dummy = new Dummy(HEALTH, EXPERIENCE);
        deadDummy = new Dummy(0, EXPERIENCE);
    }

    @Test
    public void testAttackLosesHealthWhenAttacked() {
        int healthBefore = dummy.getHealth();
        dummy.takeAttack(ATTACK_POINTS);
        int healthAfter = dummy.getHealth();
        assertEquals(healthBefore - ATTACK_POINTS, healthAfter);
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackThrowsWhenDummyIsDead() {
        deadDummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void testGiveExperienceWhenDummyIsDead() {
        int exp = deadDummy.giveExperience();
        assertEquals(EXPERIENCE, exp);
    }

    @Test(expected = IllegalStateException.class)
    public void testGiveExperienceThrowsWhenDummyIsAlive() {
        dummy.giveExperience();
    }



}