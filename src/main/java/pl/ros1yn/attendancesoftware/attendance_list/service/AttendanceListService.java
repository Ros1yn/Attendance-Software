package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendancePostDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListPostDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceListChecker;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceListToDTO;
import pl.ros1yn.attendancesoftware.exception.StudentNotFoundException;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.utils.LessonToSimpleDTO;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendanceListService {

    private final AttendanceListToDTO attendanceListToDTO;

    private final AttendanceListRepository attendanceListRepository;

    private final AttendanceListChecker attendanceListChecker;

    private final StudentRepository studentRepository;

    private final StudentNotFoundException studentNotFoundException;

    private final AttendanceRepository attendanceRepository;

    private final LessonToSimpleDTO lessonToSimpleDTO;

    public ResponseEntity<List<AttendanceListDTO>> getAllAttendanceLists() {

        Iterable<AttendanceList> all = attendanceListRepository.findAll();

        List<AttendanceListDTO> attendanceListDTOList = new ArrayList<>();

        for (AttendanceList attendanceListDTO : all) {
            attendanceListDTOList.add(attendanceListToDTO.convertToDTO(attendanceListDTO));
        }


        return ResponseEntity.ok(attendanceListDTOList);
    }

    public ResponseEntity<AttendanceListDTO> getSingleAttendanceList(Integer id) {
        return attendanceListRepository.findById(id)
                .map(attendanceListToDTO::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    public ResponseEntity<AttendanceListDTO> deleteAttendanceList(Integer id) {

        Optional<AttendanceList> optionalAttendanceList = attendanceListRepository.findById(id);

        if (optionalAttendanceList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        attendanceListRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<AttendanceListPostDTO> addNewAttendanceList(AttendanceListRequestDTO requestDTO) {

        Lesson lesson = attendanceListChecker.checkerForLesson(requestDTO);

        AttendanceList attendanceList = new AttendanceList();
        attendanceList.setDate(requestDTO.getDate());
        attendanceList.setLesson(lesson);

        AttendanceList savedList = attendanceListRepository.save(attendanceList);

        List<Attendance> attendances = requestDTO.getAttendances().stream()
                .map(dtoForList -> {
                    Student student = studentRepository.findById(dtoForList.getIndexNumber())
                            .orElseThrow(StudentNotFoundException::new);

                    Attendance attendance = new Attendance();
                    attendance.setStudent(student);
                    attendance.setIsAttendance(dtoForList.getIsAttendance());
                    attendance.setAttendanceList(savedList);
                    attendance.setActivity(0);

                    return attendance;
                })
                .toList();

        attendanceRepository.saveAll(attendances);

        AttendanceListPostDTO attendanceListDTO = new AttendanceListPostDTO(
                savedList.getId(),
                savedList.getDate(),
                lessonToSimpleDTO.convertToSimpleDTO(savedList.getLesson()),
                attendances.stream().map(a -> new AttendancePostDTO(new StudentDTO(
                        a.getStudent().getIndexNumber(),
                        a.getStudent().getName(),
                        a.getStudent().getSurname()),
                        a.getIsAttendance())
                ).toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceListDTO);
    }
}
