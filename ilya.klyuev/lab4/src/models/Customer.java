package models;

public class Customer implements Comparable<Customer> {
    private final String name;

    public Customer(String name) {
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

        Customer customer = (Customer) obj;
        return name.equalsIgnoreCase(customer.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    @Override
    public int compareTo(Customer o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        return name.compareToIgnoreCase(o.name);
    }

    public static Customer parse(String text) {
        return new Customer(text);
    }
}
