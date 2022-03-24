package workshop.persistence;

import java.util.List;

import com.zaxxer.hikari.pool.HikariPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DetailHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DetailHandler.class);
    private DetailRepository detailRepository;
    private HikariPool pool;

    public DetailHandler(DetailRepository detailRepository, HikariPool pool) {
        this.detailRepository = detailRepository;
        this.pool = pool;
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
