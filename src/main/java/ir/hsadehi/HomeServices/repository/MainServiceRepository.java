package ir.hsadehi.HomeServices.repository;

import ir.hsadehi.HomeServices.model.MainService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainServiceRepository extends JpaRepository<MainService, Long> {
    Optional<MainService> findByName(String name);
}
