package workshop.persistence;

import java.util.List;

import com.zaxxer.hikari.pool.HikariPool;

public class Puzzle2 extends EmptyPuzzle {

    public Puzzle2(MasterRepository masterRepository, DetailRepository detailRepository, HikariPool pool) {
        super(masterRepository, detailRepository, pool);
    }

    @Override
    public void modifyData() {
        Master one = masterRepository.findByReference("One");
        List<Detail> onesDetails = detailRepository.findByMasterId(one.getObjectId());
        LOGGER.info("{} running transactions", pool.getActiveConnections());
        for (Detail detail : onesDetails) {
            detail.setModCounter(detail.getModCounter() + 1);
            detailRepository.save(detail);
        }
        one.setStatus(Status.MODIFIED);
        masterRepository.save(one);
    }
}
