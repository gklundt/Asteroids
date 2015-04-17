
public class ShipFactory implements IShipFactory {

    public static IGameFigure createShip(ISharedData sharedData, int shipType) {
        IGameFigure s;
        switch (shipType) {
            case DEFAULTSHIP:
                s = new Ship(sharedData);
                s.addToSharedData();
                return s;

            default:
                return null;
        }

    }

}
