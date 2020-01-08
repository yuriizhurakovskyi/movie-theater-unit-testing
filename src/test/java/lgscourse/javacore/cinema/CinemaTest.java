package lgscourse.javacore.cinema;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import static java.time.LocalTime.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import static org.junit.Assert.*;

public class CinemaTest {

	private Cinema cinema;

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {

		protected void failed(Throwable e, org.junit.runner.Description description) {
			System.out.println("FAILED--> <" + description.getMethodName() + ">\n");
		};

		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("SUCCEED--> <" + description.getMethodName() + ">\n");
		};

	};

	@Before
	public void beforeTest() {
		cinema = new Cinema();
	}

	@After
	public void afterTest() {
		cinema = null;
	}

	@Test
	public void addMovieTest() {
		cinema.addMovie(new Movie("Movie 1", new Time(parse("02:17"))));
		cinema.addMovie(new Movie("Movie 2", new Time(parse("01:10"))));
		cinema.addMovie(new Movie("Movie 3", new Time(parse("03:04"))));
		ArrayList<Movie> actual = cinema.getMoviesLibrary();

		ArrayList<Movie> expected = cinema.getMoviesLibrary();
		expected.add(new Movie("Movie 1", new Time(parse("02:17"))));
		expected.add(new Movie("Movie 2", new Time(parse("01:10"))));
		expected.add(new Movie("Movie 3", new Time(parse("03:04"))));

		assertEquals(expected, actual);
	}

	@Test
	public void addSeanceTest() {
		Movie movie1 = new Movie("Movie 1", new Time(parse("02:17")));
		Movie movie2 = new Movie("Movie 2", new Time(parse("01:10")));
		Movie movie3 = new Movie("Movie 3", new Time(parse("03:04")));
		cinema.addMovie(movie1);
		cinema.addMovie(movie2);
		cinema.addMovie(movie3);

		cinema.addSeance(new Seance(movie1, new Time(of(12, 00))), "friday");
		cinema.addSeance(new Seance(movie2, new Time(of(15, 00))), "friday");
		cinema.addSeance(new Seance(movie3, new Time(of(16, 20))), "friday");

		Set<Seance> actual = cinema.getSchedules().entrySet().stream()
				.filter(e -> e.getKey().name().equalsIgnoreCase("friday")).findAny().get().getValue().getSeances();

		Set<Seance> expected = new TreeSet<>();
		expected.add(new Seance(movie1, new Time(of(12, 00))));
		expected.add(new Seance(movie2, new Time(of(15, 00))));
		expected.add(new Seance(movie3, new Time(of(16, 20))));

		assertEquals(expected, actual);
	}

	@Test
	public void removeMovieTest() {
		Movie movie1 = new Movie("Movie 1", new Time(parse("02:17")));
		Movie movie2 = new Movie("Movie 2", new Time(parse("01:10")));
		Movie movie3 = new Movie("Movie 3", new Time(parse("03:04")));

		cinema.addMovie(movie1);
		cinema.addMovie(movie2);
		cinema.addMovie(movie3);

		cinema.removeMovie(movie2);

		ArrayList<Movie> actual = cinema.getMoviesLibrary();

		ArrayList<Movie> expected = new ArrayList<>();
		expected.add(movie1);
		expected.add(movie3);

		assertEquals(expected, actual);
	}

	@Test
	public void removeSeanceTest() {
		Movie movie1 = new Movie("Movie 1", new Time(parse("02:17")));
		Movie movie2 = new Movie("Movie 2", new Time(parse("01:10")));
		Movie movie3 = new Movie("Movie 3", new Time(parse("03:04")));

		cinema.addMovie(movie1);
		cinema.addMovie(movie2);
		cinema.addMovie(movie3);

		cinema.addSeance(new Seance(movie1, new Time(of(12, 00))), "friday");
		cinema.addSeance(new Seance(movie2, new Time(of(15, 00))), "friday");
		cinema.addSeance(new Seance(movie3, new Time(of(16, 20))), "friday");

		cinema.removeSeance(new Seance(movie2, new Time(of(15, 00))), "friday");

		Set<Seance> actual = cinema.getSchedules().entrySet().stream()
				.filter(e -> e.getKey().name().equalsIgnoreCase("friday")).findAny().get().getValue().getSeances();

		Set<Seance> expected = new TreeSet<>();
		expected.add(new Seance(movie1, new Time(of(12, 00))));
		expected.add(new Seance(movie3, new Time(of(16, 20))));

		assertEquals(expected, actual);
	}
	
	@Test
	public void addSeanceNonWorkingHoursTest() {
		cinema.setOpen(new Time(of(9, 00)));
		cinema.setClose(new Time(of(11,59)));
		
		Movie movie1 = new Movie("Movie 1", new Time(parse("02:17")));
		Movie movie2 = new Movie("Movie 2", new Time(parse("01:10")));
		Movie movie3 = new Movie("Movie 3", new Time(parse("03:04")));
		cinema.addMovie(movie1);
		cinema.addMovie(movie2);
		cinema.addMovie(movie3);

		cinema.addSeance(new Seance(movie1, new Time(of(10, 40))), "friday");
		cinema.addSeance(new Seance(movie2, new Time(of(1, 00))), "friday");
		cinema.addSeance(new Seance(movie3, new Time(of(8, 50))), "friday");

		Set<Seance> actual = cinema.getSchedules().entrySet().stream()
				.filter(e -> e.getKey().name().equalsIgnoreCase("friday")).findAny().get().getValue().getSeances();

		Set<Seance> expected = new TreeSet<>();
		
		assertEquals(expected, actual);
	}
}
