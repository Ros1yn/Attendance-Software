package pl.ros1yn.attendancesoftware.attendance_list.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListPostDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListGetService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListPostService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListDeleteService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AttendanceListController {

    private final AttendanceListDeleteService attendanceListDeleteService;

    private final AttendanceListPostService attendanceListPostService;

    private final AttendanceListGetService attendanceListGetService;

    @GetMapping("attendancelist")
    public ResponseEntity<List<AttendanceListDTO>> getAllAttendenceLists() {
        return attendanceListGetService.getAllAttendanceLists();
    }

    @GetMapping("attendancelist/{id}")
    public ResponseEntity<AttendanceListDTO> getSingleAttendanceList(@PathVariable Integer id) {
        return attendanceListGetService.getSingleAttendanceList(id);
    }

    @DeleteMapping("attendancelist/{id}")
    public ResponseEntity<AttendanceListDTO> deleteAttendanceList(@PathVariable Integer id) {
        return attendanceListDeleteService.deleteAttendanceList(id);
    }

    @PostMapping("attendancelist")
    public ResponseEntity<AttendanceListPostDTO> addNewAttendanceList(@RequestBody AttendanceListRequestDTO attendanceListDTO) {
        return attendanceListPostService.addNewAttendanceList(attendanceListDTO);
    }


}
