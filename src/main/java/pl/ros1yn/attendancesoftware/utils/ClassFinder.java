package pl.ros1yn.attendancesoftware.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.exception.*;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ClassFinder {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final AttendanceListRepository attendanceListRepository;
    private final LessonRepository lessonRepository;
    private final CodingRepository codingRepository;

    public Attendance findAttendance(Integer attendanceId) {
        return attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> {
                    log.error("Attendance was not found");
                    return new AttendanceNotFoundException();
                });
    }

    public Coding findCoding(Integer codingId) {
        return codingRepository.findById(codingId)
                .orElseThrow(() -> {
                    log.error("Coding was not found");
                    return new CodingNotFoundException();
                });
    }

    public Student findStudent(Integer indexNumber) {
        return studentRepository.findById(indexNumber)
                .orElseThrow(() -> {
                    log.error("Student was not found");
                    return new StudentNotFoundException();
                });
    }

    public AttendanceList findAttendanceList(Integer listId) {
        return attendanceListRepository.findById(listId)
                .orElseThrow(() -> {
                    log.error("Attendance list was not found");
                    return new AttendanceListNotFoundException();
                });
    }

    public Attendance findAttendanceByIdFromList(Integer attendanceId, List<Attendance> attendanceList) {
        return attendanceList.stream()
                .filter(attendance -> attendance.getId().equals(attendanceId))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Attendance from list was not found");
                    return new AttendanceNotFoundException();
                });
    }

    public Lesson findLesson(Integer lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() ->  {
                    log.error("Lesson was not found");
                    return new LessonNotFoundException();
                });
    }
}
