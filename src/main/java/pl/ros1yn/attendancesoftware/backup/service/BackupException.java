package pl.ros1yn.attendancesoftware.backup.service;

public class BackupException extends Exception {
    public BackupException(String message) {
        super(message);
    }

    public BackupException(String message, Throwable cause) {
        super(message, cause);
    }
}
