package models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deal implements Comparable<Deal> {

    private static final Logger logger = Logger.getLogger(Deal.class.getName());
    private final Manager manager;
    private final Customer customer;
    private final Integer amount;
    private final LocalDate date;

    public Deal(Manager manager, Customer customer, Integer amount, LocalDate date) {
        this.manager = manager;
        this.customer = customer;
        this.amount = amount;
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Manager getManager() {
        return manager;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Менеджер: %s. Покупатель: %s. Сумма сделки: %d. Дата: %s", manager, customer, amount, date);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Deal deal = (Deal) obj;
        return Objects.equals(manager, deal.manager) &&
                Objects.equals(customer, deal.customer) &&
                Objects.equals(amount, deal.amount) &&
                Objects.equals(date, deal.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manager, customer, amount, date);
    }

    @Override
    public int compareTo(Deal o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return -1;
        }
        // будет сортировка первоначально по дате от большего у меньшему, потом по сумме сделки от большой к меньшей
        // потом по менеджеру, покупателю в алфавитном порядке

        int dateCompare = o.date.compareTo(date);
        if (dateCompare != 0) {
            return dateCompare;
        }

        int amountCompare = o.amount.compareTo(amount);
        if (amountCompare != 0) {
            return amountCompare;
        }

        int managerCompare = manager.compareTo(o.manager);
        if (managerCompare != 0) {
            return managerCompare;
        }

        return customer.compareTo(o.customer);
    }

    // parse from string Manager;Customer;Amount;Date
    public static Deal parse(String text) {
        try {
            String[] parts = text.split(";");
            return new Deal(Manager.parse((parts[0])), Customer.parse(parts[1]), Integer.parseInt(parts[2]), LocalDate.parse(parts[3]));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Deal parse exception", e);
            return null;
        }
    }
}
