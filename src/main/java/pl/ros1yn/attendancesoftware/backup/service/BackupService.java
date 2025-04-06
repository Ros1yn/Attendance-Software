package pl.ros1yn.attendancesoftware.backup.service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class BackupService {

    private AttendanceRepository attendanceRepository;
    private AttendanceListRepository attendanceListRepository;
    private StudentRepository studentRepository;
    private LessonRepository lessonRepository;
    private CodingRepository codingRepository;


    public void createBackup() throws BackupException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            Map<String, Object> backup = new HashMap<>();
            backup.put("students", studentRepository.findAll());
            backup.put("lessons", lessonRepository.findAll());
            backup.put("coding", codingRepository.findAll());
            backup.put("attendanceLists", attendanceListRepository.findAll());
            backup.put("attendance", attendanceRepository.findAll());

            objectMapper.writeValue(new File("backup.json"), backup);

            log.info("Backup completed successfully");
        } catch (IOException e) {
            log.warn("Backup failed", e);
            throw new BackupException("Backup failed: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void importBackup() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            Map<String, Object> dataBackup = objectMapper.readValue(
                    new File("backup.json"), new TypeReference<>() {
                    }
            );

            List<Student> students = objectMapper.convertValue(
                    dataBackup.get("students"), new TypeReference<>() {
                    }
            );
            List<Lesson> lessons = objectMapper.convertValue(
                    dataBackup.get("lessons"), new TypeReference<>() {
                    }
            );
            List<Coding> coding = objectMapper.convertValue(
                    dataBackup.get("coding"), new TypeReference<>() {
                    }
            );
            List<AttendanceList> attendanceLists = objectMapper.convertValue(
                    dataBackup.get("attendanceLists"), new TypeReference<>() {
                    }
            );
            List<Attendance> attendance = objectMapper.convertValue(
                    dataBackup.get("attendance"), new TypeReference<>() {
                    }
            );

            studentRepository.saveAll(students);
            log.info("Students have been saved");
            lessonRepository.saveAll(lessons);
            log.info("Lessons have been saved");
            codingRepository.saveAll(coding);
            log.info("Codings has been saved");
            attendanceListRepository.saveAll(attendanceLists);
            log.info("Attendance Lists have been saved");
            attendanceRepository.saveAll(attendance);
            log.info("Attendances have been saved");

        } catch (IOException e) {
            throw new RuntimeException("Backup has been faild", e);
        }

    }

}
