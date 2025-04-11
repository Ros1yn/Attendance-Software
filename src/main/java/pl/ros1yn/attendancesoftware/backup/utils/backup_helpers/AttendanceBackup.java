package pl.ros1yn.attendancesoftware.backup.utils.backup_helpers;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.backup.exception.BackupExceptionHandler;
import pl.ros1yn.attendancesoftware.backup.utils.BackupFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class AttendanceBackup {

    private final AttendanceRepository attendanceRepository;

    public void createBackup(List<String> filesToZip) {
        File attendanceBackup = new File(BackupFiles.ATTENDANCE_PATH.getPath());
        try (CSVWriter writer = new CSVWriter(new FileWriter(attendanceBackup))) {
            writer.writeNext(new String[]{"id", "studentIndexNumber", "isAttendance", "activity", "attendanceListId"});

            attendanceRepository.findAll()
                    .forEach(attendance -> writer.writeNext(new String[]{
                            attendance.getId().toString(),
                            attendance.getStudent().getIndexNumber().toString(),
                            attendance.getIsAttendance(),
                            attendance.getActivity().toString(),
                            attendance.getAttendanceList().getId().toString()
                    }));

            filesToZip.add(attendanceBackup.getPath());

        } catch (IOException e) {
            log.error("Attendance backup failed: {}", e.getMessage());
            throw new BackupExceptionHandler("Attendance backup failed: " + e.getMessage());
        }
    }

}
