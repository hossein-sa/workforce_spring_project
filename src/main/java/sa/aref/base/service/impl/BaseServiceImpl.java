package sa.aref.base.service.impl;

import sa.aref.base.entity.BaseEntity;
import sa.aref.base.repository.BaseRepository;
import sa.aref.base.service.BaseService;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable, R extends BaseRepository<E, ID>> implements BaseService<E, ID> {
    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E saveOrUpdate(E e) {
        repository.getEntityManager().getTransaction().begin();
        e = repository.saveOrUpdate(e);
        repository.getEntityManager().getTransaction().commit();
        return e;
    }

    @Override
    public E save(E e) {
        try {
            repository.getEntityManager().getTransaction().begin();
            repository.save(e);
            repository.getEntityManager().getTransaction().commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            repository.getEntityManager().getTransaction().rollback();
        }
        return e;
    }

    @Override
    public E update(E e) {

        repository.getEntityManager().getTransaction().begin();
        repository.update(e);
        repository.getEntityManager().getTransaction().commit();
        return e;
    }

    @Override
    public void delete(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.delete(e);
        repository.getEntityManager().getTransaction().commit();
    }

    @Override
    public E findById(ID id) {
        if (isExistById(id)) return repository.findById(id);
        else return null;
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean isExistById(ID id) {
        return repository.isExistById(id);
    }

    @Override
    public Long countAll() {
        return repository.countAll();
    }
}
