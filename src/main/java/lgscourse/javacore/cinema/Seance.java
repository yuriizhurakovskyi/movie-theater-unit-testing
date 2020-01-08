package lgscourse.javacore.cinema;

import java.io.Serializable;
import java.util.function.Function;

public class Seance implements Serializable, Comparable<Seance> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Movie movie;
    private Time startTime;
    private Time endTime;

    public Seance(Movie movie, Time startTime) {
        this.movie = movie;
        this.startTime = startTime;
        Function<Time, Time> fn = duration -> new Time(startTime.getLocalTime().plusHours(duration.getHour()).plusMinutes(duration.getMin()));
        this.endTime = getEndTime(movie.getDuration(), fn);
    }

    private Time getEndTime(Time movieDuration, Function<Time, Time> fn) {
        return fn.apply(movieDuration);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((movie == null) ? 0 : movie.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
        Seance other = (Seance) obj;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (movie == null) {
            if (other.movie != null)
                return false;
        } else if (!movie.equals(other.movie))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        return true;
    }

    @Override
    public int compareTo(Seance o) {
        if (startTime.compareTo(o.startTime) > 0)
            return 1;
        else if (startTime.compareTo(o.startTime) < 0)
            return -1;
        else if (endTime.compareTo(o.endTime) > 0)
            return 1;
        else if (endTime.compareTo(o.endTime) < 0)
            return -1;
        return 0;
    }
    @Override
    public String toString() {
        return "Seance: [" + movie + ", Start time: " + startTime + ", End time: " + endTime + "]";
    }
}
