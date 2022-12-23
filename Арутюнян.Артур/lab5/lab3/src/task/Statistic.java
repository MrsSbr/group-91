package task;

import data.Note;
import enums.StationType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Statistic {

    public Set<StationType> getCountMoreFifty(List<Note> list) {
        return list.stream()
                .filter(note->note.getPower()>50)
                .collect(Collectors.groupingBy(Note::getType)).keySet();
    }

    public Map<StationType,Double> getAvgPowerForLastThreeMonth(List<Note> list) {
        return list.stream()
                .filter(note->note.getDate().getMonthValue()>9&&note.getDate().getYear()==2022)
                .collect(Collectors.groupingBy(Note::getType, Collectors.averagingDouble(Note::getPower)));
    }

    public int getLastYearSumPower(List<Note> list){
        return list.stream()
                .filter(note->note.getDate().getYear()==2022)
                .collect(Collectors.summingInt(Note::getPower));
    }
}
