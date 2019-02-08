package dao;

public interface EventPointDAO<T> {
    T getById(long id);
    Long add(T hotPoint);
    void remove(long id);
    void update(T hotPoint);
}
