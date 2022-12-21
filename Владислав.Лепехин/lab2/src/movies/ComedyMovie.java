package movies;

public class ComedyMovie extends Movie {
    @Override
    public void create() {
        super.create();
        rating += 2;
    }

    @Override
    public String toString() {
        return "Comedy" + super.toString();
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
