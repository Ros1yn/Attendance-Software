package pl.ros1yn.attendancesoftware.backup.utils.backup_helpers;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.backup.exception.BackupExceptionHandler;
import pl.ros1yn.attendancesoftware.backup.utils.BackupFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class AttendanceListBackup {

    private final AttendanceListRepository attendanceListRepository;

    public void createBackup(List<String> filesToZip) {
        File attendanceListBackup = new File(BackupFiles.ATTENDANCE_LIST_PATH.getPath());
        try (CSVWriter writer = new CSVWriter(new FileWriter(attendanceListBackup))) {
            writer.writeNext(new String[]{"id", "date", "lessonId", "attendances"});

            attendanceListRepository.findAll()
                    .forEach(attendanceList -> writer.writeNext(new String[]{
                            attendanceList.getId().toString(),
                            attendanceList.getDate().toString(),
                            attendanceList.getLesson().getId().toString(),
                            attendanceList.getAttendances().toString()
                    }));

            filesToZip.add(attendanceListBackup.getPath());

        } catch (IOException e) {
            log.error("Attendance list backup failed: {}", e.getMessage());
            throw new BackupExceptionHandler("Attendance list backup failed: " + e.getMessage());
        }
    }
}
