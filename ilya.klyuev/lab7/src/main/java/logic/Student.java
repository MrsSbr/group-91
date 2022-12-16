package logic;

import java.math.BigDecimal;

@Entity(primaryKey = "id")
public record Student(int id, String fio, BigDecimal scholarship, int course, int group) {

}