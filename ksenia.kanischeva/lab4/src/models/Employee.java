package models;

import java.util.Objects;

public class Employee {
    private final String fio;
    private final int salary;

    public Employee(String fio, int salary){
        this.fio = fio;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Employee){
            return fio.equals(((Employee) o).fio) && salary == ((Employee) o).salary;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(fio, salary);
    }

    @Override
    public String toString(){
        return fio + ";"+ salary;
    }

    public int getSalary() {
        return salary;
    }
}
