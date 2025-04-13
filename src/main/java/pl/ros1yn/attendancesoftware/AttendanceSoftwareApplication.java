package pl.ros1yn.attendancesoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AttendanceSoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceSoftwareApplication.class, args);
    }

}
