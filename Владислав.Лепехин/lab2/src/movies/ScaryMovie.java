package movies;

import java.util.Objects;

public class ScaryMovie extends movies.Movie implements movies.Scaryble {
    private int countFear;
    @Override
    public void create() {
        super.create();
        countFear = 0;
    }

    @Override
    public void getFear() {
        countFear++;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ScaryMovie that = (ScaryMovie) o;
        return countFear == that.countFear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countFear);
    }


    @Override
    public String toString() {
        return "Scary" + super.toString();
    }
}
