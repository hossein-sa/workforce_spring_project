package ir.hsadehi.HomeServices.repository;

import ir.hsadehi.HomeServices.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findBySpecialistId(Long specialistId);
}
