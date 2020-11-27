package client.handler;

public interface EventListener<T> {
    void onEventAction(T object);
}
