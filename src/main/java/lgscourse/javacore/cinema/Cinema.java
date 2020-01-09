package lgscourse.javacore.cinema;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;

public class Cinema implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private TreeMap<Days, Schedule> schedules;
    private ArrayList<Movie> moviesLibrary;
    private Time open;
    private Time close;

    public Cinema() {
        this.open = new Time(LocalTime.parse("09:00"));
        this.close = new Time(LocalTime.parse("23:50"));
        schedules = new TreeMap<>();
        moviesLibrary = new ArrayList<>();
        for (Days day : Days.values()) {
            schedules.put(day, new Schedule());
        }
    }

    public Cinema(Time open, Time close) {
        if (open.getHour() >= close.getHour()){
            System.out.println("Incorrect opening and closing times");
        }else  {
            this.open = open;
            this.close = close;
            schedules = new TreeMap<>();
            moviesLibrary = new ArrayList<>();
            for (Days day : Days.values()) {
                schedules.put(day, new Schedule());
            }
        }
    }

    public TreeMap<Days, Schedule> getSchedules() {
        return schedules;
    }

    public ArrayList<Movie> getMoviesLibrary() {
        return moviesLibrary;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    public void addMovie(Movie movie) {
        moviesLibrary.add(movie);
        System.out.println("Add movie: " + movie + " to movie library");
    }

    public void addSeance(Seance seance, String day) {
        Days _day = Days.valueOf(day.toUpperCase());
        if (moviesLibrary.stream().filter(movie -> movie.equals(seance.getMovie())).findAny().isPresent()) {
            if (checkTime(seance, _day)) {
                schedules.get(_day).addSeance(seance);
                System.out.println(seance + " add to cinema schedules on " + _day);
            } else System.out.println("Cannot add this seance to this day");
        } else System.out.println("Our movie library doesn't contain this movie");
    }

    private boolean checkTime(Seance seance, Days _day) {
        if (seance.getEndTime().compareTo(close) <= 0 
        		&& seance.getEndTime().compareTo(open) > 0 
        		&& seance.getStartTime().compareTo(open) >= 0) {
            if (schedules.get(_day).getSeances().size() == 0)
                return true;
            Seance lastSeance = schedules.get(_day).getSeances().stream().max((s1, s2) -> {
                if (s1.compareTo(s2) < 0) return 1;
                return -1;
            }).get();
            if (lastSeance.getEndTime().compareTo(seance.getStartTime()) <= 0)
                return true;
        }
        return false;
    }

    public void showSchedules() {
        if (!schedules.values().stream().filter(s -> s.getSeances().size() != 0).findAny().isPresent())
            System.out.println("Schedule is empty");
        schedules.forEach((d, s) -> {
            if (s.getSeances().size() != 0) {
                System.out.println(d.name() + ": \n" + s);
            }
        });
    }

    public void showMovies() {
        if (moviesLibrary.size() != 0) {
            System.out.println("Movie Library: ");
            moviesLibrary.forEach(System.out::println);
        } else System.out.println("Movie library is empty");
    }

    public void removeMovie(Movie movie) {
        moviesLibrary.remove(movie);
        schedules.forEach((d, s) -> s.getSeances().removeIf(seance -> seance.getMovie().equals(movie)));
        System.out.println("Try to remove movie: " + movie);
    }

    public void removeSeance(Seance seance, String day) {
        try {
            schedules.get(Days.valueOf(day.toUpperCase())).removeSeance(seance);
            System.out.println(" Try to remove: " + day.toUpperCase() + " " + seance);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect day");
        }
    }
}
