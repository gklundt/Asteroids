
public interface IObservable {

    void AddObserver(IObserver obj);

    void NotifyObservers();

}
