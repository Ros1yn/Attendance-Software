package pl.ros1yn.attendancesoftware.attendance.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendancePostServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private AttendanceMapper mapper;

    @Mock
    private ClassFinder classFinder;

    @InjectMocks
    private AttendancePostService postService;

    private AttendanceUpdateDTO updateDTO;
    private AttendanceResponse attendanceResponse;
    private Attendance attendance;
    private Student student;
    private AttendanceList attendanceList;

    @BeforeEach
    void setUp() {

        student = new Student();
        student.setIndexNumber(123);

        attendanceList = new AttendanceList();
        attendanceList.setId(1);

        attendance = Attendance.builder()
                .student(student)
                .attendanceList(attendanceList)
                .isAttendance("PRESENT")
                .activity(5)
                .build();

        attendanceResponse = new AttendanceResponse();

        updateDTO = AttendanceUpdateDTO.builder().build();
        updateDTO.setIndexNumber(123);
        updateDTO.setListId(1);
        updateDTO.setIsAttendance("PRESENT");
        updateDTO.setActivity(5);
    }

    @Test
    void shouldSaveAttendanceAndReturnCreatedStatus(){

        //Given
        when(classFinder.findStudent(updateDTO.getIndexNumber())).thenReturn(student);
        when(classFinder.findAttendanceList(updateDTO.getListId())).thenReturn(attendanceList);
        when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);
        when(mapper.mapToAttendanceResponse(attendance)).thenReturn(attendanceResponse);

        //When
        ResponseEntity<AttendanceResponse> response = postService.addAttendance(updateDTO);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());

        verify(attendanceRepository, times(1)).save(any(Attendance.class));
        verify(classFinder, times(1)).findStudent(updateDTO.getIndexNumber());
        verify(classFinder, times(1)).findAttendanceList(updateDTO.getListId());
        verify(mapper, times(1)).mapToAttendanceResponse(attendance);

    }

}
