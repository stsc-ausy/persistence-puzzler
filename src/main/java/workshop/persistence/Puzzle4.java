package workshop.persistence;

import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.transaction.annotation.Transactional;

public class Puzzle4 extends EmptyPuzzle {

    private final DetailHandler detailHandler;

    public Puzzle4(MasterRepository masterRepository, DetailRepository detailRepository,
            HikariPool pool, DetailHandler detailHandler) {
        super(masterRepository, detailRepository, pool);
        this.detailHandler = detailHandler;
    }

    @Override
    @Transactional
    public void modifyData() {
        Master one = masterRepository.findByReference("Two");
        detailHandler.modifyDetails(one);
        one.setStatus(Status.MODIFIED);
    }

}
