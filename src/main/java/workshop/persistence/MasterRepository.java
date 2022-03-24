package workshop.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Integer> {

    Master findByReference(String reference);

}
