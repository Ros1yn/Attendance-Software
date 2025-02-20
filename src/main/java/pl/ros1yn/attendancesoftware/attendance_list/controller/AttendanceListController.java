package pl.ros1yn.attendancesoftware.attendance_list.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListPostDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AttendanceListController {

    private final AttendanceListService attendanceListService;

    @GetMapping("attendancelist")
    public ResponseEntity<List<AttendanceListDTO>> getAllAttendenceLists() {
        return attendanceListService.getAllAttendanceLists();
    }

    @GetMapping("attendancelist/{id}")
    public ResponseEntity<AttendanceListDTO> getSingleAttendanceList(@PathVariable Integer id) {
        return attendanceListService.getSingleAttendanceList(id);
    }

    @DeleteMapping("attendancelist/{id}")
    public ResponseEntity<AttendanceListDTO> deleteAttendanceList(@PathVariable Integer id) {
        return attendanceListService.deleteAttendanceList(id);
    }

    @PostMapping("attendancelist")
    public ResponseEntity<AttendanceListPostDTO> addNewAttendanceList(@RequestBody AttendanceListRequestDTO attendanceListDTO) {
        return attendanceListService.addNewAttendanceList(attendanceListDTO);
    }


}
