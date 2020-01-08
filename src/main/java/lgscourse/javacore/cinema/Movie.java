package lgscourse.javacore.cinema;

import java.io.Serializable;

public class Movie implements Serializable, Comparable<Movie> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String title;
    private Time duration;

    public Movie(String title, Time duration) {
        if(duration.getHour() <= 4) {
            if(title != "" || title != null) {
                this.title = title;
                this.duration = duration;
            }else System.out.println("Incorrect title");
        } else System.out.println("Duration is too long!");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        Movie other = (Movie) obj;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equalsIgnoreCase(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie title: " + title + ", Duration: " + duration;
    }

    @Override
    public int compareTo(Movie o) {
        if (title.compareTo(o.title) > 0)
            return 1;
        else if(title.compareTo(o.title) < 0)
            return -1;
        return 0;
    }
}
