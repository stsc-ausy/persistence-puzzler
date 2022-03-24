package workshop.persistence;

import com.zaxxer.hikari.pool.HikariPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyPuzzle implements Puzzle {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected final MasterRepository masterRepository;
    protected final DetailRepository detailRepository;
    protected final HikariPool pool;

    public EmptyPuzzle(MasterRepository masterRepository, DetailRepository detailRepository, HikariPool pool) {
        this.masterRepository = masterRepository;
        this.detailRepository = detailRepository;
        this.pool = pool;
    }

    @Override
    public void modifyData() {
        LOGGER.info("Running transactions: {}\n", pool.getActiveConnections());
    }
}
