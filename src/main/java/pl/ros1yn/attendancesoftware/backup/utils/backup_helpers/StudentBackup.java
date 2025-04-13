package pl.ros1yn.attendancesoftware.backup.utils.backup_helpers;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.backup.exception.BackupExceptionHandler;
import pl.ros1yn.attendancesoftware.backup.utils.BackupFiles;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class StudentBackup {

    private final StudentRepository studentRepository;

    public void createBackup(List<String> filesToZip) {
        File studentBackup = new File(BackupFiles.STUDENT_PATH.getPath());
        try (CSVWriter writer = new CSVWriter(new FileWriter(studentBackup))) {
            writer.writeNext(new String[]{"indexNumber", "name", "surname"});

            studentRepository.findAll()
                    .forEach(student -> writer.writeNext(new String[]{
                            student.getIndexNumber().toString(),
                            student.getName(),
                            student.getSurname()
                    }));

            filesToZip.add(studentBackup.getPath());

        } catch (IOException e) {
            log.error("Student backup failed: {}", e.getMessage());
            throw new BackupExceptionHandler("Student backup failed: " + e.getMessage());
        }
    }
}
