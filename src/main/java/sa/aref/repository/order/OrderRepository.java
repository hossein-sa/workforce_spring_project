package sa.aref.repository.order;

import sa.aref.base.repository.BaseRepository;
import sa.aref.entity.duties.MainDuties;
import sa.aref.entity.duties.SubDuties;
import sa.aref.entity.order.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order,Long>{
    List<SubDuties> showSubDuties(MainDuties mainDuties);
}
