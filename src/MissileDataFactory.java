
public class MissileDataFactory implements IMissileDataFactory {

    public static IMissileData getMissileData(int missileType) {
        switch (missileType) {
            case ASTEROIDS:
                return new MissileData();
            default:
                return null;
        }
    }

}
