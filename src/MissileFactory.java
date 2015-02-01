
public class MissileFactory implements IMissileFactory {

    public static IGameFigure createMissile(IMissileData missileData, ISharedData sharedData) {
        IGameFigure m = new Missile(missileData, sharedData);
        m.addToSharedData();
        return m;
    }

}
