package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.Getter;

@Getter
public enum EmptyAttendanceListLog {

    EMPTY_ATTENDANCE_LIST_LOG("Attendance list cannot be empty");

    private final String messageLog;

    EmptyAttendanceListLog(String messageLog) {
        this.messageLog = messageLog;
    }

}
