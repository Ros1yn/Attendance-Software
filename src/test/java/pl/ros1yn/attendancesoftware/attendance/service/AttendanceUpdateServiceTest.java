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
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceFinder;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceUpdateHelper;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceUpdateServiceTest {

    @Mock
    private AttendanceMapper attendanceMapper;

    @Mock
    private AttendanceUpdateHelper updateHelper;

    @Mock
    private AttendanceFinder attendanceFinder;

    @InjectMocks
    private AttendanceUpdateService attendanceUpdateService;

    private Attendance attendance;
    private AttendanceResponse attendanceResponse;
    private AttendanceUpdateDTO updateDTO;


    @BeforeEach
    void setUp() {
        attendance = new Attendance();
        attendance.setId(1);

        updateDTO = AttendanceUpdateDTO.builder().build();

        attendanceResponse = new AttendanceResponse();
    }

    @Test
    void shouldReturnOkWhenAttendanceGetsFullyUpdated() {

        //Given
        Integer attendanceId = 1;
        when(attendanceFinder.find(attendanceId)).thenReturn(attendance);
        when(attendanceMapper.mapToAttendanceResponse(attendance)).thenReturn(attendanceResponse);

        //When
        ResponseEntity<AttendanceResponse> response = attendanceUpdateService.updateFullAttendance(attendanceId, updateDTO);

        //Given
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());

        verify(attendanceFinder, times(1)).find(attendanceId);
        verify(updateHelper, times(1)).updateAttendanceFromPutDTO(updateDTO, attendance);
        verify(attendanceMapper, times(1)).mapToAttendanceResponse(attendance);
    }

    @Test
    void shouldReturnOkWhenAttendanceGetsUpdatedPartially() {

        Integer attendanceId = 1;
        when(attendanceFinder.find(attendanceId)).thenReturn(attendance);
        when(attendanceMapper.mapToAttendanceResponse(attendance)).thenReturn(attendanceResponse);

        //When
        ResponseEntity<AttendanceResponse> response = attendanceUpdateService.updateAttendancePartially(attendanceId, updateDTO);

        //Given
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());

        verify(attendanceFinder, times(1)).find(attendanceId);
        verify(updateHelper, times(1)).updateAttendanceFromPatchDTO(updateDTO, attendance);
        verify(attendanceMapper, times(1)).mapToAttendanceResponse(attendance);
    }


    @Test
    void shouldThrowExceptionWhenAttendanceDoesNotExist() {

        //Given
        Integer attendanceId = 1;
        when(attendanceFinder.find(attendanceId)).thenThrow(new AttendanceNotFoundException());

        //Then
        assertThrows(AttendanceNotFoundException.class, () -> attendanceUpdateService.updateFullAttendance(attendanceId, updateDTO));

        verify(attendanceFinder, times(1)).find(attendanceId);
        verify(attendanceMapper, never()).mapToAttendanceResponse(attendance);
        verify(updateHelper, never()).updateAttendanceFromPutDTO(updateDTO, attendance);
        verify(updateHelper, never()).updateAttendanceFromPatchDTO(updateDTO, attendance);
    }
}
