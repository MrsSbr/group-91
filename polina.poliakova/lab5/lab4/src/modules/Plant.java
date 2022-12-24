package lab4.src.modules;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.IllegalFormatException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//название растения;тип;размер горшка;цена;дата продажи
public class Plant {
    private static final Logger logger = Logger.getLogger(Plant.class.getName());
    private final String plantName;
    private final PlantType typeName;
    private final double sizePot;
    private final double price;
    private final LocalDate date;

    public Plant(String plantName, PlantType typeName, double sizePot, double price, LocalDate date) {
        this.plantName = plantName;
        this.typeName = typeName;
        this.sizePot = sizePot;
        this.price = price;
        this.date = date;
    }

    public String getPlantName() {
        return plantName;
    }

    public PlantType getTypeName() {
        return typeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getSizePot() {
        return sizePot;
    }

    public double getPrice() {
        return price;
    }


    // parse from string Plant;Type;Size of Pot;Price;Date
    public static Plant parse(String text) {
        try {
            String[] parts = text.split(";");
            return new Plant(parts[0], PlantType.valueOf(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), LocalDate.parse(parts[4]));
        } catch (IllegalFormatException e) {
            logger.log(Level.SEVERE, "PlantType parse exception");
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Size/Price parse exception", e);
        } catch (DateTimeParseException e) {
            logger.log(Level.SEVERE, "Date parse exception", e);
        }

        return null;
    }

    @Override
    public String toString() {
        return String.format("Растение: %s. Тип: %s. Размер горшка: %.2f. Цена: %.2f. Дата: %s", plantName, typeName, sizePot, price, date);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Plant plant = (Plant) obj;
        return plantName.equalsIgnoreCase(plant.plantName) &&
                typeName == plant.typeName &&
                sizePot == plant.sizePot &&
                price == plant.price &&
                date.equals(plant.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plantName.toLowerCase(), typeName, sizePot, price, date);
    }
}
