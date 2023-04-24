package rpg_lab;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HeroTest {

    private Weapon weapon;
    private Target target;
    private Hero hero;

    @Before
    public void setUp() {
        weapon = mock(Weapon.class);
        target = mock(Target.class);
        hero = new Hero("Vlado");
    }

    @Test
    public void testAttackHeroReceivesExperience() {
        Target testTarget = mock(Target.class);
        when(testTarget.isDead()).thenReturn(true);
        when(testTarget.giveExperience()).thenReturn(10);
        int expBefore = hero.getExperience();
        hero.attack(testTarget);
        int expAfter = hero.getExperience();
        assertNotEquals(expAfter, expBefore);
    }

}