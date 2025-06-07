package pl.ros1yn.attendancesoftware.attendance_list.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttedanceListUpdateService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListDeleteService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListGetService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListPostService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceListControllerTest {

    @Mock
    private AttendanceListDeleteService attendanceListDeleteService;

    @Mock
    private AttendanceListPostService attendanceListPostService;

    @Mock
    private AttendanceListGetService attendanceListGetService;

    @Mock
    private AttedanceListUpdateService attedanceListUpdateService;

    @InjectMocks
    private AttendanceListController attendanceListController;

    private AttendanceListResponse attendanceListResponse;
    private AttendanceListRequestDTO requestDTO;

    @BeforeEach
    void setup() {

        attendanceListResponse = AttendanceListResponse.builder()
                .id(1)
                .build();

        requestDTO = AttendanceListRequestDTO.builder()
                .attendances(List.of())
                .date(LocalDate.of(2025, 1, 1))
                .lessonId(1)
                .build();

    }

    @Test
    void shouldReturnAllAttendanceListsAndReturnOk() {

        //given
        ResponseEntity<List<AttendanceListResponse>> response = ResponseEntity.ok(List.of());
        when(attendanceListGetService.getAllAttendanceLists()).thenReturn(response);
        //when
        ResponseEntity<List<AttendanceListResponse>> result = attendanceListController.getAllAttendenceLists();
        //then
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void shouldReturnSingleAttendanceListAndReturnOk() {

        //given
        int attendanceListId = 1;
        ResponseEntity<AttendanceListResponse> response = ResponseEntity.ok(attendanceListResponse);
        when(attendanceListGetService.getSingleAttendanceList(attendanceListId)).thenReturn(response);

        //when
        ResponseEntity<AttendanceListResponse> result = attendanceListController.getSingleAttendanceList(attendanceListId);

        //then
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(attendanceListResponse, result.getBody());

        verify(attendanceListGetService, times(1)).getSingleAttendanceList(attendanceListId);

    }

    @Test
    void shouldReturnNoContentAndDeleteAttendance() {

        int attendanceListId = 1;
        //given
        when(attendanceListDeleteService.removeAttendanceListById(attendanceListId)).thenReturn(ResponseEntity.noContent().build());

        //when
        ResponseEntity<AttendanceListResponse> result = attendanceListController.deleteAttendanceList(attendanceListId);

        //then
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        verify(attendanceListDeleteService, times(1)).removeAttendanceListById(attendanceListId);

    }

    @Test
    void shouldCreateAttendanceListAndReturnStatusCreated(){

        //given
        when(attendanceListPostService.addNewAttendanceList(requestDTO))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(attendanceListResponse));

        //when
        ResponseEntity<AttendanceListResponse> result = attendanceListController.addNewAttendanceList(requestDTO);

        //then
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(attendanceListResponse, result.getBody());

        verify(attendanceListPostService, times(1)).addNewAttendanceList(requestDTO);
    }

    @Test
    void shouldFullyUpdateAttendanceAndReturnOk(){

        //given
        int listId = 1;
        when(attedanceListUpdateService.updateAttendanceList(listId, requestDTO))
                .thenReturn(ResponseEntity.ok(attendanceListResponse));

        //when
        ResponseEntity<AttendanceListResponse> result = attendanceListController.updateAttendanceList(listId, requestDTO);

        //then
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(attendanceListResponse, result.getBody());

        verify(attedanceListUpdateService, times(1)).updateAttendanceList(listId, requestDTO);
    }

    @Test
    void shouldUpdatePartiallyAttendanceAndReturnOk(){

        //given
        int listId = 1;
        when(attedanceListUpdateService.updateAttendanceListPartially(listId, requestDTO))
                .thenReturn(ResponseEntity.ok(attendanceListResponse));

        //when
        ResponseEntity<AttendanceListResponse> result = attendanceListController.updateAttendanceListPartially(listId, requestDTO);

        //then
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(attendanceListResponse, result.getBody());

        verify(attedanceListUpdateService, times(1)).updateAttendanceListPartially(listId, requestDTO);

    }

}