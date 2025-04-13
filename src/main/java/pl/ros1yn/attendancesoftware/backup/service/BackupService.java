package pl.ros1yn.attendancesoftware.backup.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.backup.exception.BackupExceptionHandler;
import pl.ros1yn.attendancesoftware.backup.utils.BackupFiles;
import pl.ros1yn.attendancesoftware.backup.utils.ZipPacker;
import pl.ros1yn.attendancesoftware.backup.utils.backup_helpers.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service
@AllArgsConstructor
@Slf4j
public class BackupService {

    private final LessonBackup lessonBackup;
    private final CodingBackup codingBackup;
    private final AttendanceListBackup attendanceListBackup;
    private final AttendanceBackup attendanceBackup;
    private final StudentBackup studentBackup;

    private static final String BACKUP_FAILED = "Backup failed: ";

    @Scheduled(cron = "0 0 * * * *")
    public void createBackup() {

        File backupDir = new File(BackupFiles.TEMPORARY_DIRECTORY_PATH.getPath());

        if (!backupDir.exists()) {
            backupDir.mkdir();
        } else {

            try {
                FileUtils.deleteDirectory(backupDir);
            } catch (IOException e) {

                getBackupErrorLog(e);
                throw new BackupExceptionHandler(BACKUP_FAILED + e.getMessage());
            }
            backupDir.mkdir();
        }

        ArrayList<String> filesToZip = new ArrayList<>();

        studentBackup.createBackup(filesToZip);
        lessonBackup.createBackup(filesToZip);
        codingBackup.createBackup(filesToZip);
        attendanceListBackup.createBackup(filesToZip);
        attendanceBackup.createBackup(filesToZip);

        try (FileOutputStream fos = new FileOutputStream(BackupFiles.COMPRESSED_ZIP_PATH.getPath())) {

            ZipPacker.addAllFilesToZip(fos, filesToZip);
            FileUtils.deleteDirectory(backupDir);
        } catch (IOException e) {

            getBackupErrorLog(e);
            throw new BackupExceptionHandler(BACKUP_FAILED + e.getMessage());
        }
        log.info("Backup has been created");

    }

    private static void getBackupErrorLog(IOException e) {

        log.error(BACKUP_FAILED + "{}", e.getMessage());
    }
}



