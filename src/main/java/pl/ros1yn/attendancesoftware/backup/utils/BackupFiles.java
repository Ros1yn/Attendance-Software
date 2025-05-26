package pl.ros1yn.attendancesoftware.backup.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BackupFiles {

    STUDENT_PATH("backupCSV/student.csv"),
    ATTENDANCE_PATH("backupCSV/attendance.csv"),
    ATTENDANCE_LIST_PATH("backupCSV/attendanceList.csv"),
    CODING_PATH("backupCSV/coding.csv"),
    LESSON_PATH("backupCSV/lesson.csv"),
    COMPRESSED_ZIP_PATH("backup/compressed.zip"),
    TEMPORARY_DIRECTORY_PATH("backupCSV"),
    BACKUP_FOLDER_PATH("backup");

    private final String path;
}
