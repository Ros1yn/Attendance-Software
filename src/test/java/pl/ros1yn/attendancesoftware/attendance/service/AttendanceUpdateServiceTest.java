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
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceUpdateServiceTest {

    @Mock
    AttendanceMapper attendanceMapper;

    @Mock
    ClassFinder classFinder;

    @InjectMocks
    AttendanceUpdateService attendanceUpdateService;

    Attendance attendance;
    AttendanceResponse attendanceResponse;
    AttendanceUpdateDTO updateDTO;


    @BeforeEach
    void setUp() {
        attendance = new Attendance();
        attendance.setId(1);

        updateDTO = new AttendanceUpdateDTO();
        attendanceResponse = new AttendanceResponse();
    }

    @Test
    void shouldReturnOkWhenAttendanceGetsFullyUpdated() {

        //Given
        Integer attendanceId = 1;
        when(classFinder.findAttendance(attendanceId)).thenReturn(attendance);
        when(attendanceMapper.mapToAttendanceResponse(attendance)).thenReturn(attendanceResponse);

        //When
        ResponseEntity<AttendanceResponse> response = attendanceUpdateService.updateAttendance(attendanceId, updateDTO);

        //Given
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());

        verify(classFinder, times(1)).findAttendance(attendanceId);
        verify(attendanceMapper, times(1)).updateAttendanceFromPutDTO(updateDTO, attendance);
        verify(attendanceMapper, times(1)).mapToAttendanceResponse(attendance);
    }

    @Test
    void shouldReturnOkWhenAttendanceGetsUpdatedPartially() {

        Integer attendanceId = 1;
        when(classFinder.findAttendance(attendanceId)).thenReturn(attendance);
        when(attendanceMapper.mapToAttendanceResponse(attendance)).thenReturn(attendanceResponse);

        //When
        ResponseEntity<AttendanceResponse> response = attendanceUpdateService.updatePartially(attendanceId, updateDTO);

        //Given
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());

        verify(classFinder, times(1)).findAttendance(attendanceId);
        verify(attendanceMapper, times(1)).updateAttendanceFromPatchDTO(updateDTO, attendance);
        verify(attendanceMapper, times(1)).mapToAttendanceResponse(attendance);
    }


    @Test
    void shouldThrowExceptionWhenAttendanceDoesNotExist() {

        //Given
        Integer attendanceId = 1;
        when(classFinder.findAttendance(attendanceId)).thenThrow(new AttendanceNotFoundException());

        //When & Then
        assertThrows(AttendanceNotFoundException.class, () -> attendanceUpdateService.updateAttendance(attendanceId, updateDTO));

        verify(classFinder, times(1)).findAttendance(attendanceId);
        verify(attendanceMapper, never()).mapToAttendanceResponse(attendance);
        verify(attendanceMapper, never()).updateAttendanceFromPutDTO(updateDTO, attendance);
        verify(attendanceMapper, never()).updateAttendanceFromPatchDTO(updateDTO, attendance);
    }
}
