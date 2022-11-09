package deals;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deal {

    private static final Logger logger = Logger.getLogger(Deal.class.getName());
    private final String managerName;
    private final String customerName;
    private final Integer amount;
    private final LocalDate date;

    public Deal(String managerName, String customerName, Integer amount, LocalDate date) {
        this.managerName = managerName;
        this.customerName = customerName;
        this.amount = amount;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Менеджер: %s. Покупатель: %s. Сумма сделки: %d. Дата: %s", managerName, customerName, amount, date);
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
        return managerName.equalsIgnoreCase(deal.managerName) &&
                customerName.equalsIgnoreCase(deal.customerName) &&
                amount.equals(deal.amount) &&
                date.equals(deal.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerName.toLowerCase(), customerName.toLowerCase(), amount, date);
    }

    // parse from string Manager;Customer;Amount;Date
    public static Deal parse(String text) {
        try {
            String[] parts = text.split(";");
            return new Deal(parts[0], parts[1], Integer.parseInt(parts[2]), LocalDate.parse(parts[3]));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "deals.Deal parse exception", e);
            return null;
        }
    }
}
