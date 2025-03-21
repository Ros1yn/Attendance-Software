package pl.ros1yn.attendancesoftware.attendance_list.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttedanceListUpdateService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListDeleteService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListGetService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListPostService;

import java.util.List;

@RestController
@RequestMapping("attendancelist")
@AllArgsConstructor
@Slf4j
class AttendanceListController {

    private final AttendanceListDeleteService attendanceListDeleteService;
    private final AttendanceListPostService attendanceListPostService;
    private final AttendanceListGetService attendanceListGetService;
    private final AttedanceListUpdateService attedanceListUpdateService;

    @GetMapping("/")
    ResponseEntity<List<AttendanceListResponse>> getAllAttendenceLists() {
        log.info("Recived request for getAllAttendenceLists");
        return attendanceListGetService.getAllAttendanceLists();
    }

    @GetMapping("/{id}")
    ResponseEntity<AttendanceListResponse> getSingleAttendanceList(@PathVariable Integer id) {
        log.info("Recived request for getSingleAttendanceList with id: {}", id);
        return attendanceListGetService.getSingleAttendanceList(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AttendanceListResponse> deleteAttendanceList(@PathVariable Integer id) {
        log.info("Recived request for deleteAttendanceList id: {}", id);
        return attendanceListDeleteService.deleteAttendanceList(id);
    }

    @PostMapping("/")
    ResponseEntity<AttendanceListResponse> addNewAttendanceList(@RequestBody AttendanceListRequestDTO attendanceListDTO) {
        log.info("Recived request for addNewAttendanceList with body: {}", attendanceListDTO);
        return attendanceListPostService.addNewAttendanceList(attendanceListDTO);
    }

    @PutMapping("/{id}")
    ResponseEntity<AttendanceListResponse> updateAttendanceList(@PathVariable Integer id, @RequestBody AttendanceListRequestDTO requestDTO) {
        log.info("Recived request for updateAttendanceList with id: {} - and body: {}", id, requestDTO);
        return attedanceListUpdateService.updateAttendanceList(id, requestDTO);
    }

    @PatchMapping("/{id}")
    ResponseEntity<AttendanceListResponse> updateAttendanceListPartially(@PathVariable Integer id, @RequestBody AttendanceListRequestDTO requestDTO) {
        log.info("Recived request for updateAttendanceListPartially with id: {} - and body: {}", id, requestDTO);
        return attedanceListUpdateService.updateAttendanceListPartially(id, requestDTO);
    }
}

