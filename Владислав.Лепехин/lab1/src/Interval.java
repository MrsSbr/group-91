public class Interval {

    public double start;
    public double end;

    public Interval(double start, double end) {

        this.start = start;
        this.end = end;

    }

    public boolean isIntersects(double start, double end) {

        if (this.start <= start && this.end >= start) {

            return true;

        }

        return this.start >= start && this.start <= end;


    }

}
