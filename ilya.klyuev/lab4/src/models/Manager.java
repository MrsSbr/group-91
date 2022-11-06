package models;

public class Manager implements Comparable<Manager> {

    private final String name;

    public Manager(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Manager manager = (Manager) obj;
        return name.equalsIgnoreCase(manager.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    @Override
    public int compareTo(Manager o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return -1;
        }
        return name.compareToIgnoreCase(o.name);
    }

    public static Manager parse(String text) {
        return new Manager(text);
    }
}
