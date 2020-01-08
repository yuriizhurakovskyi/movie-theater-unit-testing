package lgscourse.javacore.cinema;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Schedule implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Set<Seance> seances = new TreeSet<>(Comparator.comparing(Seance::getStartTime).thenComparing(Seance::getMovie));

    void addSeance(Seance seance) {
        seances.add(seance);
    }

    void removeSeance(Seance seance) {
        seances.remove(seance);
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    @Override
    public String toString() {
        return seances.stream().map(seance -> seance + "\n").collect(Collectors.joining());
    }
}
