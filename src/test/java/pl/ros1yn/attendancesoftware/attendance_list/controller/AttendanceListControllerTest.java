package pl.ros1yn.attendancesoftware.attendance_list.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttedanceListUpdateService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListDeleteService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListGetService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListPostService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

    @Test
    void getAllAttendenceListsTest() {

        //given
        ResponseEntity<List<AttendanceListResponse>> response = ResponseEntity.ok(List.of());
        when(attendanceListGetService.getAllAttendanceLists()).thenReturn(response);
        //when
        ResponseEntity<List<AttendanceListResponse>> result = attendanceListController.getAllAttendenceLists();
        //then
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }
}