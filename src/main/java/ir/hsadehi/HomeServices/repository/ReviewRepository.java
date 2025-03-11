package ir.hsadehi.HomeServices.repository;

import ir.hsadehi.HomeServices.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findBySpecialistId(Long specialistId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.specialist.id = :specialistId")
    Optional<Double> calculateAverageRatingForSpecialist(@Param("specialistId") Long specialistId);

}
