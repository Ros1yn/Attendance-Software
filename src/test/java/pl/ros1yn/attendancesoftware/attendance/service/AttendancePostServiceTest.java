package pl.ros1yn.attendancesoftware.attendance.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendancePostServiceTest {

    @Mock
    AttendanceRepository attendanceRepository;

    @InjectMocks
    AttendancePostService postService;

    Attendance attendance;

    @BeforeEach
    void setUp(){
        attendance = new Attendance();
        attendance.setId(1);
    }

    @Test
    void shouldSaveAttendanceAndReturnCreatedStatus(){

        //Given
        when(attendanceRepository.save(attendance)).thenReturn(attendance);

        //When
        ResponseEntity<Attendance> response = postService.addAttendance(attendance);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(attendance, response.getBody());

        verify(attendanceRepository, times(1)).save(attendance);

    }

}
