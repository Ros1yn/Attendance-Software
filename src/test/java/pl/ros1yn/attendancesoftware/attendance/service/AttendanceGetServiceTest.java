package pl.ros1yn.attendancesoftware.attendance.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceGetServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private AttendanceMapper attendanceMapper;

    @InjectMocks
    private AttendanceGetService attendanceGetService;

    private Attendance attendance;
    private AttendanceResponse attendanceResponse;

    @BeforeEach
    void setUp() {
        attendance = new Attendance();
        attendance.setId(1);

        attendanceResponse = new AttendanceResponse();
    }

    @Test
    void shouldReturnAttendanceResponseWhenAttendanceExists() {

        //Given
        Integer listId = 1;
        when(attendanceRepository.findById(listId)).thenReturn(Optional.of(attendance));
        when(attendanceMapper.mapToAttendanceResponse(attendance)).thenReturn(attendanceResponse);

        //When
        ResponseEntity<AttendanceResponse> response = attendanceGetService.getAttendance(listId);

        //Then
        assertNotNull(response);
        assertEquals(ResponseEntity.ok(attendanceResponse), response);

        verify(attendanceRepository, times(1)).findById(listId);
        verify(attendanceMapper, times(1)).mapToAttendanceResponse(attendance);
    }

    @Test
    void shouldThrowExceptionWhenAttendanceDoesNotExist() {

        //Given
        Integer listId = 1;
        when(attendanceRepository.findById(listId)).thenReturn(Optional.empty());

        //When
        assertThrows(AttendanceNotFoundException.class, () -> attendanceGetService.getAttendance(listId));

        //Then
        verify(attendanceRepository, times(1)).findById(listId);
        verify(attendanceMapper, never()).mapToAttendanceResponse(attendance);
    }

    @Test
    void shouldReturnAllAttendances() {

        //given
        List<Attendance> attendances = List.of(attendance);
        when(attendanceRepository.findAll()).thenReturn(attendances);

        //When
        ResponseEntity<Iterable<Attendance>> response = attendanceGetService.getAllAttendances();

        //Then
        assertNotNull(response);
        assertEquals(ResponseEntity.ok(attendances), response);

        verify(attendanceRepository, times(1)).findAll();
    }

}