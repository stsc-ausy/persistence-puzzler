package workshop.persistence;

import java.util.List;

import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class Puzzle3 extends EmptyPuzzle {

    private final DetailHandler detailHandler;

    public Puzzle3(MasterRepository masterRepository, DetailRepository detailRepository,
            HikariPool pool, DetailHandler detailHandler) {
        super(masterRepository, detailRepository, pool);
        this.detailHandler = detailHandler;
    }

    @Override
    @Transactional
    public void modifyData() {
        Master one = masterRepository.findByReference("One");
        detailHandler.modifyDetails(one);
        one.setStatus(Status.MODIFIED);
    }

}
