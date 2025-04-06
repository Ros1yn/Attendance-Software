package pl.ros1yn.attendancesoftware.backup.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ros1yn.attendancesoftware.backup.service.BackupException;
import pl.ros1yn.attendancesoftware.backup.service.BackupService;

@Controller
@RequestMapping("backup")
@AllArgsConstructor
@Slf4j
class BackupController {

    private final BackupService backupService;

    @GetMapping("/")
    ResponseEntity<String> createBackup() throws BackupException {

        try {

            log.info("Recived request for backupDataBase.");
            backupService.createBackup();
        } catch (BackupException e) {

            log.warn("Backup failed", e);
            throw new BackupException("Backup failed: " + e.getMessage(), e);
        }

        log.info("backup has been created");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/restore")
    ResponseEntity<String> restoreBackup() {

        log.info("Recived request for restoreBackup.");
        backupService.importBackup();

        log.info("backup has been imported");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
