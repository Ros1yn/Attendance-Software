package pl.ros1yn.attendancesoftware.backup.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.backup.utils.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service
@AllArgsConstructor
@Slf4j
public class BackupService {

    private final LessonBackup lessonBackup;
    private CodingBackup codingBackup;
    private AttendanceListBackup attendanceListBackup;
    private AttendanceBackup attendanceBackup;
    private StudentBackup studentBackup;
    private ZipPacker zipPacker;

    @Scheduled(cron = "0 * * * *")
    public void createBackup() throws IOException {

        File backupDir = new File("backupCSV");

        if (!backupDir.exists()) {
            backupDir.mkdir();
        } else {
            FileUtils.deleteDirectory(backupDir);
            backupDir.mkdir();
        }

        ArrayList<String> filesToZip = new ArrayList<>();

        studentBackup.createBackup(filesToZip);
        lessonBackup.createBackup(filesToZip);
        codingBackup.createBackup(filesToZip);
        attendanceListBackup.createBackup(filesToZip);
        attendanceBackup.createBackup(filesToZip);

        FileOutputStream fos = new FileOutputStream("backup/compressed.zip");

        ZipPacker.addAllFilesToZip(fos, filesToZip);

        FileUtils.deleteDirectory(backupDir);
    }


}



