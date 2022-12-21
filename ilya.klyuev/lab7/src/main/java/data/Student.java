package data;

import logic.PrimaryKey;

import java.math.BigDecimal;

public record Student(@PrimaryKey int id, String fio, BigDecimal scholarship, int course, int group) {

}