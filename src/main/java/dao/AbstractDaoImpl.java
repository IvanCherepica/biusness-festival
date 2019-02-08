package dao;

abstract class AbstractDaoImpl<T> implements AbstractDao<T>{

    @Override
    public T getById(long id) {
        return null;
    }

    @Override
    public void save(T t) {

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(T t) {

    }
}
