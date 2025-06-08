package pl.ros1yn.attendancesoftware.utils;

public interface Finder<T, ID> {

    T find(ID id);

}
