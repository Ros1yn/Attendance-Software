package pl.ros1yn.attendancesoftware.attendance_list.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttendanceListGetServiceTest {

    @Mock
    private AttendanceListRepository attendanceListRepository;

    @Mock
    private AttendanceListMapper attendanceListMapper;

    @InjectMocks
    private AttendanceListGetService attendanceListGetService;

    @Test
    void shouldReturnAllAttendances() {

        //Given
        AttendanceList attendanceList1 = new AttendanceList();
        AttendanceList attendanceList2 = new AttendanceList();

        AttendanceListResponse attendanceListResponse1 = new AttendanceListResponse();
        AttendanceListResponse attendanceListResponse2 = new AttendanceListResponse();

        when(attendanceListRepository.findAll()).thenReturn(List.of(attendanceList1, attendanceList2));
        when(attendanceListMapper.mapToResponseDTO(attendanceList1)).thenReturn(attendanceListResponse1);
        when(attendanceListMapper.mapToResponseDTO(attendanceList2)).thenReturn(attendanceListResponse2);

        //When
        ResponseEntity<List<AttendanceListResponse>> response = attendanceListGetService.getAllAttendanceLists();

        //Then
        assertNotNull(response);
        assertEquals(attendanceListResponse1, Objects.requireNonNull(response.getBody()).getFirst());
        assertEquals(attendanceListResponse2, Objects.requireNonNull(response.getBody().get(1)));

        verify(attendanceListRepository).findAll();
        verify(attendanceListMapper).mapToResponseDTO(attendanceList1);
        verify(attendanceListMapper).mapToResponseDTO(attendanceList2);

    }

}