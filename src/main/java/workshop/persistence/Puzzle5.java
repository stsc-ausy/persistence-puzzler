package workshop.persistence;

import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.transaction.annotation.Transactional;

public class Puzzle5 extends EmptyPuzzle {

    private final DetailHandler detailHandler;

    public Puzzle5(MasterRepository masterRepository, DetailRepository detailRepository,
            HikariPool pool, DetailHandler detailHandler) {
        super(masterRepository, detailRepository, pool);
        this.detailHandler = detailHandler;
    }

    @Override
    @Transactional
    public void modifyData() {
        Master one = masterRepository.findByReference("Two");
        try {
            detailHandler.modifyDetails(one.getObjectId());
        } catch (PuzzleException e) {
            LOGGER.error("Failed to update details {}", e.getMessage());
        }
    }

}
