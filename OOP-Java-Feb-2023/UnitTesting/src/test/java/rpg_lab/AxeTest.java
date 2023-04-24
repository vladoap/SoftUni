package rpg_lab;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTest {

    private static final int ATTACK = 10;
    private static final int DURABILITY = 10;
    private static final int HEALTH = 10;
    private static final int EXPERIENCE = 10;

    private Axe axe;
    private Dummy dummy;
    private Axe brokenAxe;

    @Before
    public void setUp() {
          axe = new Axe(ATTACK, DURABILITY);
          brokenAxe = new Axe(ATTACK, 0);
          dummy = new Dummy(HEALTH, EXPERIENCE);
    }

    @Test
    public void testAttackLosesDurabilityAfterAttack() {
        int durabilityBefore = axe.getDurabilityPoints();
        axe.attack(dummy);
        int durabilityAfter = axe.getDurabilityPoints();
        Assert.assertEquals(durabilityBefore - 1, durabilityAfter);

    }

    @Test(expected = IllegalStateException.class)
    public void testAttackThrowsExceptionWhenAxeIsBroken() {
       brokenAxe.attack(dummy);
    }



}