package pl.ros1yn.attendancesoftware.attendance.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceDeleteServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private ClassFinder classFinder;

    @InjectMocks
    private AttendanceDeleteService attendanceDeleteService;

    private Attendance attendance;

    @BeforeEach
    void setUp() {

        attendance = new Attendance();
        attendance.setId(1);
    }

    @Test
    void shouldReturnNoContentWhenDelete() {

        //Given
        Integer attendanceId = 1;
        when(classFinder.findAttendance(attendanceId)).thenReturn(attendance);

        //When
        ResponseEntity<Void> response = attendanceDeleteService.deleteAttendance(attendanceId);

        //Then
        assertNotNull(response);
        assertEquals(ResponseEntity.noContent().build(), response);

        verify(classFinder, times(1)).findAttendance(attendanceId);
        verify(attendanceRepository, times(1)).deleteById(attendanceId);
    }

    @Test
    void shouldReturnNotFoundWhenDoesNotExist() {

        //Given
        Integer attendanceId = 1;
        when(classFinder.findAttendance(attendanceId)).thenThrow(new AttendanceNotFoundException());

        //When & Then
        assertThrows(AttendanceNotFoundException.class, () -> attendanceDeleteService.deleteAttendance(attendanceId));

        verify(classFinder, times(1)).findAttendance(attendanceId);
        verify(attendanceRepository, never()).deleteById(attendanceId);
    }
}
