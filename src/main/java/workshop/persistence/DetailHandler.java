package workshop.persistence;

import java.util.List;

import com.zaxxer.hikari.pool.HikariPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DetailHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DetailHandler.class);
    private final MasterRepository masterRepository;
    private DetailRepository detailRepository;
    private HikariPool pool;

    public DetailHandler(MasterRepository masterRepository, DetailRepository detailRepository, HikariPool pool) {
        this.masterRepository = masterRepository;
        this.detailRepository = detailRepository;
        this.pool = pool;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyDetails(Integer masterId) {
        Master one = masterRepository.findById(masterId).get();
        one.setStatus(Status.MODIFIED);
        if ("Two".equals(one.getReference())) {
            throw new PuzzleException("Wrong master " + one);
        }
        List<Detail> onesDetails = detailRepository.findByMasterId(one.getObjectId());
        LOGGER.info("{} running transactions", pool.getActiveConnections());
        for (Detail detail : onesDetails) {
            detail.setModCounter(detail.getModCounter() + 1);
        }
    }
}
