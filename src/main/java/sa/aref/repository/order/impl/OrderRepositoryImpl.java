package sa.aref.repository.order.impl;

import sa.aref.base.repository.impl.BaseRepositoryImpl;
import sa.aref.entity.duties.MainDuties;
import sa.aref.entity.duties.SubDuties;
import sa.aref.entity.order.Order;
import jakarta.persistence.EntityManager;
import sa.aref.repository.order.OrderRepository;

import java.util.List;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order, Long> implements OrderRepository {

    public OrderRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Order> getEntityClass() {
        return null;
    }

    @Override
    public List<SubDuties> showSubDuties(MainDuties mainDuties) {
        return null;
    }
}
