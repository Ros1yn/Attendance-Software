package pl.ros1yn.attendancesoftware.backup.utils.backup_helpers;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.backup.exception.BackupExceptionHandler;
import pl.ros1yn.attendancesoftware.backup.utils.BackupFiles;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class LessonBackup {

    private final LessonRepository lessonRepository;

    public void createBackup(List<String> filesToZip) {
        File lessonBackup = new File(BackupFiles.LESSON_PATH.getPath());
        try (CSVWriter writer = new CSVWriter(new FileWriter(lessonBackup))) {
            writer.writeNext(new String[]{"id", "title", "semester", "year"});

            lessonRepository.findAll()
                    .forEach(lesson -> writer.writeNext(new String[]{
                            lesson.getId().toString(),
                            lesson.getTitle(),
                            lesson.getSemester().toString(),
                            lesson.getYear().toString(),
                    }));

            filesToZip.add(lessonBackup.getPath());

        } catch (IOException e) {
            log.error("Lesson backup failed: {}", e.getMessage());
            throw new BackupExceptionHandler("Lesson backup failed: " + e.getMessage());
        }
    }
}