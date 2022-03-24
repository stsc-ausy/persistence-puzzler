package workshop.persistence;

import java.util.List;

import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class Puzzle3 extends EmptyPuzzle {

    public Puzzle3(MasterRepository masterRepository, DetailRepository detailRepository, HikariPool pool) {
        super(masterRepository, detailRepository, pool);
    }

    @Override
    @Transactional
    public void modifyData() {
        Master one = masterRepository.findByReference("One");
        modifyDetails(one);
        one.setStatus(Status.MODIFIED);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void modifyDetails(Master one) {
        List<Detail> onesDetails = detailRepository.findByMasterId(one.getObjectId());
        LOGGER.info("{} running transactions", pool.getActiveConnections());
        for (Detail detail : onesDetails) {
            detail.setModCounter(detail.getModCounter() + 1);
        }
    }
}
