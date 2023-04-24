package magicGame.models.magics;

public class BlackMagic extends MagicImpl {

    private static final int FIRED_BULLETS_AT_ONCE = 10;

    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    private int bullets = getBulletsCount();

    @Override
    public int fire() {
        if (getBulletsCount() - 10 < 0) {
            bullets = 0;
            return 0;
        } else {
            bullets -= 10;
            return 10;
        }
    }
}
