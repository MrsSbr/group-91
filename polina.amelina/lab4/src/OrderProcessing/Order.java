package OrderProcessing;

import java.time.Duration;
import java.time.LocalDateTime;

public record Order(String name,
                    LocalDateTime date,
                    Duration preparationTime,
                    double cost) {}