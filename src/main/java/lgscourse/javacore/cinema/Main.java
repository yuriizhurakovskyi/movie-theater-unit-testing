package lgscourse.javacore.cinema;


import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.lang.System.*;
import static java.time.LocalTime.*;

public class Main {
    static Cinema cinema = new Cinema();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        Time open = null, close = null;
        while (true) {
            showMenu();
            switch (scanner.nextLine().toLowerCase()) {
                case "1":
                    while (true) {
                        try {
                            open = new Time(parse(scanner.nextLine()));
                            break;
                        } catch (DateTimeParseException dtp) {
                            out.println("Incorrect time data, enter again( hh:mm hh:mm ):");
                        }
                    }
                    while (true) {
                        try {
                            close = new Time(parse(scanner.nextLine()));
                            break;
                        } catch (DateTimeParseException dtp) {
                            out.println("Incorrect time data, enter again( hh:mm hh:mm ):");
                        }
                    }
                    cinema = new Cinema(open, close);
                    break;
                case "2":
                    cinema.showSchedules();
                    break;
                case "3":
                    cinema.showMovies();
                    break;
                case "4":
                    out.println("Enter a title of movie");
                    String title = scanner.nextLine();
                    Time duration = null;
                    while (true) {
                        try {
                            out.println("Enter a duration of movie hh:mm");
                            duration = new Time(parse(scanner.nextLine()));
                            cinema.addMovie(new Movie(title, duration));
                            break;
                        } catch (DateTimeParseException dtp) {
                            out.println("Incorrect time data, enter again (hh:mm):");
                        }
                    }
                    break;
                case "5":
                    out.println("Enter a title of movie");
                    title = scanner.nextLine();
                    out.println("Enter a duration of movie hh:mm");
                    while (true) {
                        try {
                            duration = new Time(parse(scanner.nextLine()));
                            break;
                        } catch (DateTimeParseException dtp) {
                            out.println("Incorrect time data, enter again (hh:mm):");
                        }
                    }
                    out.println("Enter a start time of the seance hh:mm");
                    Time start = null;
                    while (true) {
                        try {
                            start = new Time(parse(scanner.nextLine()));
                            break;
                        } catch (DateTimeParseException dtp) {
                            out.println("Incorrect time data, enter again(hh:mm):");
                        }
                    }
                    String day = null;
                    while (true) {
                        try {
                            out.println("Enter a day of the seance (SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY)");
                            day = scanner.nextLine();
                            cinema.addSeance(new Seance(new Movie(title, duration), start), day);
                            break;
                        } catch (IllegalArgumentException e) {
                            out.println("Incorrect day of week");
                        }
                    }
                    break;
                case "6":
                    out.println("Enter a title of movie");
                    title = scanner.nextLine();
                    out.println("Enter a duration of movie hh:mm");
                    while (true) {
                        try {
                            duration = new Time(parse(scanner.nextLine()));
                            cinema.removeMovie(new Movie(title, duration));
                            break;
                        } catch (DateTimeParseException dtp) {
                            out.println("Incorrect time data, enter again (hh:mm):");
                        }
                    }
                    break;
                case "7":
                    out.println("Enter a title of movie");
                    title = scanner.nextLine();
                    out.println("Enter a duration of movie hh:mm");
                    while (true) {
                        try {
                            duration = new Time(parse(scanner.nextLine()));
                            break;
                        } catch (DateTimeParseException dtp) {
                            out.println("Incorrect time data, enter again (hh:mm):");
                        }
                    }
                    out.println("Enter a start time of the seance hh:mm");
                    start = null;
                    while (true) {
                        try {
                            start = new Time(parse(scanner.nextLine()));
                            break;
                        } catch (DateTimeParseException dtp) {
                            out.println("Incorrect time data, enter again (hh:mm):");
                        }
                    }
                    while (true) {
                        try {
                            out.println("Enter a day of the seance (SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY)");
                            day = scanner.nextLine();
                            cinema.removeSeance(new Seance(new Movie(title, duration), start), day);
                            break;
                        } catch (IllegalArgumentException e) {
                            out.println("Incorrect day of week");
                        }
                    }
                    break;
                case "exit":
                    scanner.close();
                    exit(0);
                    break;
                default:
                    out.println("Incorrect input");
                    break;
            }
        }

    }

    public static void showMenu() {
        if (cinema.getOpen() != null && cinema.getClose() != null)
            out.println("The cinema is opened at " + cinema.getOpen() + ", closed at " + cinema.getClose());
        out.println("1 - Add opening and closing time of the cinema" +
                "( hh:mm hh:mm, opening < closing, if hh or mm < 10 then enter with 0) ");
        out.println("2 - Show schedule of the cinema");
        out.println("3 - Show all movies");
        out.println("4 - Add new movie");
        out.println("5 - Add new seance");
        out.println("6 - Remove movie");
        out.println("7 - Remove seance");
        out.println("exit - To exit. ");
    }
}
