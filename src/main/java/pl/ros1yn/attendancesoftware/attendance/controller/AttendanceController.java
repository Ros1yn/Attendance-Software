package pl.ros1yn.attendancesoftware.attendance.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceDeleteService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceGetService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendancePostService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceUpdateService;

import java.util.List;

@RestController
@RequestMapping("attendance")
@AllArgsConstructor
@Slf4j
class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceDeleteService attendanceDeleteService;
    private final AttendanceGetService attendanceGetService;
    private final AttendancePostService attendancePostService;
    private final AttendanceUpdateService attendanceUpdateService;

    @GetMapping("/")
    ResponseEntity<List<Attendance>> getAllAttendances() {

        log.info("Recived request for getAllAttendances.");
        return ResponseEntity.ok(attendanceRepository.findAll());
    }

    @GetMapping("/{attendanceId}")
    ResponseEntity<AttendanceResponse> getSingleAttendance(@PathVariable Integer attendanceId) {

        ResponseEntity<AttendanceResponse> response = attendanceGetService.getAttendance(attendanceId);
        log.info("Recived request for getSingleAttendance with id: {}. Response: {}", attendanceId, response.getBody());
        return response;
    }

    @DeleteMapping("/{attendanceId}")
    ResponseEntity<Void> deleteAttendance(@PathVariable Integer attendanceId) {

        ResponseEntity<Void> response = attendanceDeleteService.deleteAttendance(attendanceId);
        log.info("Recived request for deleteAttendance with id: {}. Response status: {}", attendanceId, response.getStatusCode());
        return response;
    }

    @PostMapping("/")
    ResponseEntity<AttendanceResponse> addAttendance(@RequestBody AttendanceUpdateDTO updateDTO) {
        ResponseEntity<AttendanceResponse> response = attendancePostService.addAttendance(updateDTO);
        log.info("Recived request for addAttendance with body: {}. Response: {}", updateDTO, response.getBody());
        return response;
    }

    @PutMapping("/{attendanceId}")
    ResponseEntity<AttendanceResponse> updateFullAttendance(@PathVariable Integer attendanceId, @RequestBody AttendanceUpdateDTO updateDTO) {

        ResponseEntity<AttendanceResponse> response = attendanceUpdateService.updateFullAttendance(attendanceId, updateDTO);
        log.info("Recived request for updateFullAttendance with id: {} - and body: {}. Response: {}", attendanceId, updateDTO, response.getBody());
        return response;
    }

    @PatchMapping("/{attendanceId}")
    ResponseEntity<AttendanceResponse> updateAttendancePartially(@PathVariable Integer attendanceId, @RequestBody AttendanceUpdateDTO updateDTO) {

        ResponseEntity<AttendanceResponse> response = attendanceUpdateService.updateAttendancePartially(attendanceId, updateDTO);
        log.info("Recived request for updateAttendancePartially with id: {} - and body: {}. Response: {}", attendanceId, updateDTO, response.getBody());
        return response;
    }
}

