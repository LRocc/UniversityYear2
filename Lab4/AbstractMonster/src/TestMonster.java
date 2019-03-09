import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMonster {

    // Test fixture -- named by my son :)
    private Monster megaCharizardX, greninja, froaky, frogadier, alolanGolem, daleFox, redGyarados;

    @BeforeEach
    void setUp() throws Exception {
        megaCharizardX = new FireMonster (200, 100);
        greninja = new WaterMonster (120, 50);
        froaky = new WaterMonster (80, 50);
        frogadier = new WaterMonster (100, 50);
        alolanGolem = new ElectricMonster(150, 10);
        daleFox = new FireMonster(0, 100);
        redGyarados = new WaterMonster (200, 100);
    }

    @AfterEach
    void tearDown() throws Exception {
        megaCharizardX = null;
        greninja = null;
        froaky = null;
        frogadier = null;
        alolanGolem = null;
        daleFox = null;
        redGyarados = null;
    }

    @Test
    public void dodgeExistsAndHasCorrectSignature() {
        try {
            Method m = Monster.class.getDeclaredMethod("dodge", new Class[0]);
            Assert.assertTrue("Monster.dodge is not protected", Modifier.isProtected(m.getModifiers()));
            Assert.assertTrue("Monster.dodge is not abstract", Modifier.isAbstract(m.getModifiers()));
            Assert.assertEquals("Monster.dodge method does not have correct return type",
                    boolean.class, m.getReturnType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assert.fail("Monster.dodge method not found");
        }
    }

    @Test
    public void fireMonsterDodgeWorksProperly() {
        Assert.assertTrue("First call to FireMonster.dodge() should return true",
                megaCharizardX.dodge());
        Assert.assertFalse("Second call to FireMonster.dodge() should return false",
                megaCharizardX.dodge());
        Assert.assertTrue("Third call to FireMonster.dodge() should return true",
                megaCharizardX.dodge());
    }

    @Test
    public void waterMonsterDodgeWorksProperly() {
        Assert.assertTrue("WaterMonster.dodge() should return true if HP is over 100",
                greninja.dodge());
        Assert.assertFalse("WaterMonster.dodge() should return false if HP is less than 100",
                froaky.dodge());
        Assert.assertTrue("WaterMonster.dodge() should return true if HP is exactly 100",
                frogadier.dodge());
    }

    @Test
    public void electricMonsterDodgeWorksProperly() {
        Assert.assertFalse("ElectricMonster.dodge() should always return false",
                alolanGolem.dodge());
    }

    @Test
    public void attackThrowsExceptionIfAttackSelf() {
        Assertions.assertThrows(MonsterException.class,
                () -> { megaCharizardX.attack(megaCharizardX); });
    }

    public void attackDoesNotThrowExceptionIfAttackTwin() {
        FireMonster fm2 = new FireMonster(megaCharizardX.getHitPoints(), megaCharizardX.getAttackPoints());
        try {
            fm2.attack(megaCharizardX);
        } catch (MonsterException ex) {
            Assert.fail("attack() should not throw an exception when a monster attacks an identical monster");
        }
    }

    @Test
    public void attackThrowsExceptionIfAttackerIsKO() {
        Assertions.assertThrows(MonsterException.class,
                () -> { daleFox.attack(megaCharizardX); });
    }

    @Test
    public void attackThrowsExceptionIfAttackedIsKO() {
        Assertions.assertThrows(MonsterException.class,
                () -> { megaCharizardX.attack(daleFox); });
    }

    @Test
    public void fireMonsterTypeAndWeaknessesCorrect() {
        Assert.assertEquals("Fire monster type incorrect", "Fire", megaCharizardX.getType());
        Assert.assertTrue ("Fire monster should be weak against Water", megaCharizardX.isWeakAgainst("Water"));
        Assert.assertFalse ("Fire monster should not be weak against Electric", megaCharizardX.isWeakAgainst("Electric"));
    }

    @Test
    public void waterMonsterTypeAndWeaknessesCorrect() {
        Assert.assertEquals("Water monster type incorrect", "Water", greninja.getType());
        Assert.assertTrue ("Water monster should be weak against Fire", greninja.isWeakAgainst("Fire"));
        Assert.assertTrue ("Water monster should be weak against Electric", greninja.isWeakAgainst("Electric"));
    }

    @Test
    public void electricMonsterTypeAndWeaknessesCorrect() {
        Assert.assertEquals("Electric monster type incorrect", "Electric", alolanGolem.getType());
        Assert.assertFalse ("Electric monster should not be weak against Water", alolanGolem.isWeakAgainst("Water"));
        Assert.assertFalse ("Electric monster should not be weak against Fire", alolanGolem.isWeakAgainst("Fire"));
    }

    @Test
    public void attackUsesDodge() {
        try {
            // This will not dodge
            greninja.attack(froaky);
            Assert.assertEquals("Attacking monster HP should not be lower after no dodge", 120, greninja.getHitPoints());
            Assert.assertEquals("Attacked monster HP should be lower after attack", 30, froaky.getHitPoints());

            // This will dodge
            alolanGolem.attack(megaCharizardX);
            Assert.assertEquals("Attacking monster HP should be lower after dodge", 140, alolanGolem.getHitPoints());
            Assert.assertEquals("Attacked monster HP should not be lower after dodge", 200, megaCharizardX.getHitPoints());
        } catch (MonsterException ex) {
            Assert.fail ("Monster.attack() should not throw exception in normal use");
        }
    }
}
