package logic;

import java.math.BigDecimal;

@PrimaryKey("id")
public record Student(int id, String fio, BigDecimal scholarship, int course, int group) {

}