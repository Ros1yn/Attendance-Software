package pl.ros1yn.attendancesoftware.backup.utils;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.backup.exception.BackupExceptionHandler;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class CodingBackup {

    private final CodingRepository codingRepository;

    public void createBackup(List<String> filesToZip) {
        File codingBackup = new File("backupCSV/coding.csv");
        try (CSVWriter writer = new CSVWriter(new FileWriter(codingBackup))) {
            writer.writeNext(new String[]{"id", "studentIndexNumber", "lessonId", "group"});

            codingRepository.findAll()
                    .forEach(coding -> writer.writeNext(new String[]{
                            coding.getId().toString(),
                            coding.getStudent().getIndexNumber().toString(),
                            coding.getLesson().getId().toString(),
                            coding.getGroup().toString()
                    }));

            filesToZip.add(codingBackup.getPath());

        } catch (IOException e) {
            log.error("Coding backup failed: {}", e.getMessage());
            throw new BackupExceptionHandler("Coding backup failed: " + e.getMessage());
        }
    }
}
