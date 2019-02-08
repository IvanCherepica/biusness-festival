package dao;

public interface AbstractDao<T> {
    T getById(long id);
    void save(T t);
    void remove(long id);
    void update(T t);
}
