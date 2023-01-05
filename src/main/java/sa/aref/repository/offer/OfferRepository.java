package sa.aref.repository.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.offer.Offer;
@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
