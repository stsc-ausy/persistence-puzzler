package workshop.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail, Integer> {

    List<Detail> findByMasterId(Integer masterId);

}
