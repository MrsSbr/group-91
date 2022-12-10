package orderProcessing;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record Order(String name, LocalDateTime date, LocalTime prepTime, double cost) {}