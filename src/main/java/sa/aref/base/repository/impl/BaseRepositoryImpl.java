package sa.aref.base.repository.impl;

import sa.aref.base.entity.BaseEntity;
import sa.aref.base.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<E, ID> {
    protected final EntityManager entityManager;

    public BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public abstract Class<E> getEntityClass();

    @Override
    public E saveOrUpdate(E e) {
        if (e.getId() == null) entityManager.persist(e);
        else entityManager.merge(e);
        return e;
    }

    @Override
    public E save(E e) {
        entityManager.persist(e);
        return e;
    }

    @Override
    public E update(E e) {
        entityManager.merge(e);
        return e;
    }

    @Override
    public void delete(E e) {
        entityManager.remove(e);
    }

    @Override
    public E findById(ID id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery("from " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
    }

    @Override
    public boolean isExistById(ID id) {
        TypedQuery<Long> query = entityManager.createQuery("select count(id) from " + getEntityClass().getSimpleName() + " where id= :pk", Long.class);
        Long countId = query.setParameter("pk", id).getSingleResult();
        return countId == 1L;
    }

    @Override
    public Long countAll() {
        return entityManager.createQuery("select count(id) from " + getEntityClass().getSimpleName() + " where id=:pk", Long.class).getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
