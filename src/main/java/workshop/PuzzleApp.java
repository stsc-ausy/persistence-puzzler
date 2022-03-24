package workshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import workshop.persistence.Puzzle;
import workshop.persistence.TableContentLogger;

@SpringBootApplication
public class PuzzleApp implements CommandLineRunner {

    private final TableContentLogger logger;
    private final Puzzle action;

    public PuzzleApp(TableContentLogger logger, Puzzle action) {
        this.logger = logger;
        this.action = action;
    }

    public static void main(String[] args) {
        SpringApplication.run(PuzzleApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logTables();
        action.modifyData();
        logTables();
    }

    private void logTables() {
        logger.logTable("master");
        logger.logTable("detail");
    }
}
