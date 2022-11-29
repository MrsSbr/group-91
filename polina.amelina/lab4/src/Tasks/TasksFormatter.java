package Tasks;

import MethodNameGetter.MethodNameGetter;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TasksFormatter {
    private static final Logger logger = Logger.getGlobal();

    public TasksFormatter() {}

    public String getAvgPrepTimeByDrinkFormattedResult(Map<String, LocalTime> avgPrepTime) {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        String result =
                avgPrepTime == null
                ? "Нет ни одного приготовленного напитка"
                : "Среднее время приготовления каждого напитка: "
                        + avgPrepTime
                        .entrySet()
                        .stream()
                        .map(x -> x.getKey() + ": " + x.getValue())
                        .collect(Collectors.joining(", "));

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getBusiestWeekdayHoursFormattedResult(List<Integer> busiestHours) {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        String result =
                busiestHours == null
                ? "По будням нет посетителей"
                : "Самые загруженные часы по будням: "
                        + busiestHours
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getMostPopularDrinksFormattedResult(List<String> mostPopularDrinks) {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        String result =
                mostPopularDrinks == null
                ? "С 7 до 12 утра нет заказов"
                : "Напитки, которые чаще всего заказывают с 7 до 12 утра: "
                        + mostPopularDrinks
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getOptimalDrinkFormattedResult(String optimalDrink) {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        String result =
                optimalDrink == null
                ? "Нет ни одного проданного напитка"
                : "Напиток с наилучшим соотношением цена/время: "
                        + optimalDrink;

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }
}