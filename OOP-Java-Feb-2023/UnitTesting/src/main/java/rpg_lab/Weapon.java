package rpg_lab;

public interface Weapon {

    public void attack(Target target);
    public int getDurabilityPoints();
    public int getAttackPoints();
}
