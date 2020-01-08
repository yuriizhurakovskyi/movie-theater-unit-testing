package lgscourse.javacore.cinema;

import java.io.Serializable;
import java.time.LocalTime;

public class Time implements Serializable, Comparable<Time> {
    private int min;
    private int hour;

    public Time(LocalTime localTime) {
        this.hour = localTime.getHour();
        this.min = localTime.getMinute();
    }

    public int getMin() {
        return min;
    }

    public int getHour() {
        return hour;
    }

    public LocalTime getLocalTime() {
        return LocalTime.of(hour, min);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hour;
        result = prime * result + min;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Time other = (Time) obj;
        if (hour != other.hour)
            return false;
        if (min != other.min)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(LocalTime.of(hour, min));
    }

    @Override
    public int compareTo(Time o) {
        if (hour > o.hour)
            return 1;
        else if (hour < o.hour)
            return -1;
        else if (min > o.min)
            return 1;
        else if (min < o.min)
            return -1;
        return 0;
    }
}
