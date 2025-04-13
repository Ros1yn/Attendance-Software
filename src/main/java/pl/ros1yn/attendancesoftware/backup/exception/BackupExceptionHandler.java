package pl.ros1yn.attendancesoftware.backup.exception;

import java.io.IOException;

public class BackupExceptionHandler extends RuntimeException {

    public BackupExceptionHandler(String message) {
        super(message);
    }
}
