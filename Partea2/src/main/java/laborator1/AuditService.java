package laborator1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {

    private static AuditService instance = null;
    private static final String FILE_PATH = "audit.csv";

    private AuditService() {
    }
    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void logEvent(String actionName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(actionName + "," + LocalDateTime.now());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
