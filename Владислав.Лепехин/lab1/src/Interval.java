public class Interval {
    private double start;
    private double end;

    public Interval(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public double getStart() {
        return this.start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return this.end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public boolean isIntersects(double start, double end) {
        if (this.start <= start && this.end >= start) {
            return true;
        } else {
            return this.start >= start && this.start <= end;
        }
    }
}