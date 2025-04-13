package pl.ros1yn.attendancesoftware.attendance.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceDeleteService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceGetService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendancePostService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceUpdateService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@WebMvcTest(AttendanceController.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
class AttendanceControllerTest {

    @MockitoBean
    private AttendanceGetService attendanceGetService;

    @MockitoBean
    private AttendanceDeleteService attendanceDeleteService;

    @MockitoBean
    private AttendancePostService attendancePostService;

    @MockitoBean
    private AttendanceUpdateService attendanceUpdateService;

    private Attendance attendance1;
    private Attendance attendance2;
    private AttendanceResponse attendanceResponse;
    private AttendanceUpdateDTO updateDTO;


    @BeforeEach
    void setUp() {

        attendance1 = new Attendance();
        attendance1.setId(1);

        attendance2 = new Attendance();
        attendance2.setId(2);

        attendanceResponse = new AttendanceResponse();
        attendanceResponse.setId(1);

        updateDTO = AttendanceUpdateDTO.builder()
                .indexNumber(1234)
                .isAttendance("PRESENT")
                .activity(1)
                .listId(1)
                .build();
    }

    @Test
    void shouldGetAllAttendancesAndReturnOk() {
        //Given
        List<Attendance> attendances = List.of(attendance1, attendance2);
        when(attendanceGetService.getAllAttendances())
                .thenReturn(ResponseEntity.ok(attendances));

        //When
        ResponseEntity<List<Attendance>> response = attendanceGetService.getAllAttendances();

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendances, response.getBody());
        verify(attendanceGetService, times(1)).getAllAttendances();
    }

    @Test
    void shouldGetSingleAttendanceAndReturnOk() {

        //Given
        int attendanceId = 1;
        when(attendanceGetService.getAttendance(attendanceId))
                .thenReturn(ResponseEntity.ok(attendanceResponse));

        //When
        ResponseEntity<AttendanceResponse> response = attendanceGetService.getAttendance(attendanceId);

        //Given
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());
        verify(attendanceGetService, times(1)).getAttendance(attendanceId);
    }

    @Test
    void shouldDeleteAttendanceAndReturnNoContent() {

        //Given
        int attendanceId = 1;
        when(attendanceDeleteService.deleteAttendance(attendanceId))
                .thenReturn(ResponseEntity.noContent().build());

        //When
        ResponseEntity<Void> response = attendanceDeleteService.deleteAttendance(attendanceId);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(attendanceDeleteService, times(1)).deleteAttendance(attendanceId);
    }

    @Test
    void shouldAddAttendanceAndReturnCreated() {

        //Given
        when(attendancePostService.addAttendance(updateDTO))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(attendanceResponse));

        //When

        ResponseEntity<AttendanceResponse> response = attendancePostService.addAttendance(updateDTO);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());

        verify(attendancePostService, times(1)).addAttendance(updateDTO);
    }

    @Test
    void shouldFullyUpdateAttendanceAndReturnOk() {

        //When
        int attendanceId = 1;
        when(attendanceUpdateService.updateAttendance(attendanceId, updateDTO))
                .thenReturn(ResponseEntity.ok(attendanceResponse));

        //When
        ResponseEntity<AttendanceResponse> response = attendanceUpdateService.updateAttendance(attendanceId, updateDTO);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());

        verify(attendanceUpdateService, times(1)).updateAttendance(attendanceId, updateDTO);
    }

    @Test
    void shouldUpdateAttendancePartiallyAndReturnOk() {

        //When
        int attendanceId = 1;
        when(attendanceUpdateService.updatePartially(attendanceId, updateDTO))
                .thenReturn(ResponseEntity.ok(attendanceResponse));

        //When
        ResponseEntity<AttendanceResponse> response = attendanceUpdateService.updatePartially(attendanceId, updateDTO);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceResponse, response.getBody());

        verify(attendanceUpdateService, times(1)).updatePartially(attendanceId, updateDTO);
    }



}
