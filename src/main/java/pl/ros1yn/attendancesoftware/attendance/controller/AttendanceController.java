package pl.ros1yn.attendancesoftware.attendance.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceService;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;

    private final AttendanceService attendanceService;


    @GetMapping("attendance")
    public ResponseEntity<Iterable<Attendance>> getAllAttendances() {
        return ResponseEntity.ok(attendanceRepository.findAll());
    }

    @GetMapping("attendance/{id}")
    public ResponseEntity<Optional<Attendance>> getSingleAttendance(@PathVariable Integer id) {
        return attendanceService.getAttendance(id);
    }

    @DeleteMapping("attendance/{id}")
    public ResponseEntity<Attendance> deleteAttendance(@PathVariable Integer id) {
        return attendanceService.deleteAttendance(id);
    }

    @PostMapping("attendance")
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {

        return attendanceService.addAttendance(attendance);
    }

    @PatchMapping("attendance/{id}")
    public ResponseEntity<Attendance> updateFullAttendance(@PathVariable Integer id, @RequestBody Attendance attendance) {

        return attendanceService.updateAttendance(id, attendance);

    }

    @PutMapping("attendance/{id}")
    public ResponseEntity<Attendance> updateAttendancePartially(@PathVariable Integer id, @RequestBody Attendance attendance) {
        return attendanceService.updatePartially(id, attendance);
    }


}

