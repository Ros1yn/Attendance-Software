package pl.ros1yn.attendancesoftware.backup.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ros1yn.attendancesoftware.backup.service.BackupService;

import java.io.IOException;

@Controller
@RequestMapping("backup")
@AllArgsConstructor
@Slf4j
class BackupController {

    private final BackupService backupService;

    @GetMapping("/")
    ResponseEntity<String> createBackup() {

        try {

            log.info("Recived request for backupDataBase.");
            backupService.createBackup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("backup has been created");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/restore")
    ResponseEntity<String> restoreBackup() {

        log.info("Recived request for restoreBackup.");

        log.info("backup has been imported");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
