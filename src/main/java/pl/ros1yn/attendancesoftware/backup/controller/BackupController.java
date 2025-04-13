package pl.ros1yn.attendancesoftware.backup.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ros1yn.attendancesoftware.backup.service.BackupService;

@Controller
@RequestMapping("backup")
@AllArgsConstructor
@Slf4j
class BackupController {

    private final BackupService backupService;

    @GetMapping("/")
    ResponseEntity<String> createBackup() {


        log.info("Recived request for backupDataBase.");
        backupService.createBackup();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
