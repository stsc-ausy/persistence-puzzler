package workshop.persistence;

import java.util.List;
import java.util.Optional;

import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.transaction.annotation.Transactional;

public class Puzzle1 extends EmptyPuzzle {
    public Puzzle1(MasterRepository masterRepository, DetailRepository detailRepository, HikariPool pool) {
        super(masterRepository, detailRepository, pool);
    }

    @Override
    @Transactional
    public void modifyData() {
        Master one = masterRepository.findByReference("One");
        List<Detail> onesDetails = detailRepository.findByMasterId(one.getObjectId());
        for (Detail detail : onesDetails) {
            Optional<Detail> byId = detailRepository.findById(detail.getObjectId());
            byId.ifPresent(d -> LOGGER.info("Found {}", d));
        }
    }
}
