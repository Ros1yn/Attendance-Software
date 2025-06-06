package pl.ros1yn.attendancesoftware.attendance.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.dto.StudentResponse;
import pl.ros1yn.attendancesoftware.student.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@Slf4j
class AttendanceMapperTest {

    private AttendanceList attendanceList;
    private Student student;
    private StudentResponse studentResponse;
    private Attendance attendance;
    private AttendanceResponse attendanceResponse;

    @BeforeEach
    void setup() {

        attendanceList = new AttendanceList();

        student = Student.builder()
                .indexNumber(12345)
                .name("John")
                .surname("Flower")
                .build();

        studentResponse = StudentResponse.builder()
                .indexNumber(student.getIndexNumber())
                .name(student.getName())
                .surname(student.getSurname())
                .build();

        attendance = Attendance.builder()
                .id(1)
                .isAttendance("PRESENT")
                .activity(1)
                .attendanceList(attendanceList)
                .student(student)
                .build();

        attendanceResponse = AttendanceResponse.builder()
                .id(attendance.getId())
                .isAttendance(attendance.getIsAttendance())
                .activity(attendance.getActivity())
                .studentResponse(studentResponse)
                .build();

    }

    @Test
    void shouldMapAttendanceToAttendanceResponseCorrectly() {

        //given
        AttendanceMapper attendanceMapper = new AttendanceMapper();

        //when
        AttendanceResponse result = attendanceMapper.mapToAttendanceResponse(attendance);

        //then
        assertNotNull(result);
        assertEquals(attendanceResponse.getId(), result.getId());
        assertEquals(attendanceResponse.getIsAttendance(), result.getIsAttendance());
        assertEquals(attendanceResponse.getActivity(), result.getActivity());
        assertEquals(attendanceResponse.getStudentResponse().getIndexNumber(), result.getStudentResponse().getIndexNumber());
        assertEquals(attendanceResponse.getStudentResponse().getName(), result.getStudentResponse().getName());
        assertEquals(attendanceResponse.getStudentResponse().getSurname(), result.getStudentResponse().getSurname());
    }

}