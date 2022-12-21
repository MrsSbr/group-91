package models;

import java.time.LocalDate;
import java.util.*;

public class FightsStatistics {
    private List<Fight> fights;

    private Set<Integer> getAllTournaments() {
        Set<Integer> tournaments = new HashSet<>();
        fights.forEach(f -> tournaments.add(f.getTournamentNumber()));
        return tournaments;
    }

    private Set<String> getAllMembers() {
        Set<String> members = new HashSet<>();
        fights.forEach(f -> members.add(f.getMemberFirst()));
        fights.forEach(f -> members.add(f.getMemberSecond()));
        return members;
    }

    private Set<String> getMembersOfTournament(int tournamentNum) {
        Set<String> members = new HashSet<>();
        fights.stream().filter(f -> f.getTournamentNumber() == tournamentNum).
                forEach(f -> members.add(f.getMemberFirst()));
        fights.stream().filter(f -> f.getTournamentNumber() == tournamentNum).
                forEach(f -> members.add(f.getMemberSecond()));
        return members;
    }

    private long getMemberWins(String member) {
        return fights.stream().filter(f -> f.getWinner().equals(member)).count();
    }

    private boolean isDateFrom3YearGapWithFatality(Fight f) {
        return !f.getDate().isBefore(LocalDate.now().minusYears(3)) && f.getIsFatality() == 1;
    }

    private Map<String, Integer> getStringDateWithFatalities() {
        Map<String, Integer> dateWithFatalities = new HashMap<>();
        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 36; i++) {
            dateWithFatalities.put(startDate.getYear() + "-" + startDate.getMonth(), 0);
            startDate = startDate.minusMonths(1);
        }

        fights.stream().filter(this::isDateFrom3YearGapWithFatality).forEach(f -> {
            String dateWithoutDay = f.getDate().getYear() + "-" + f.getDate().getMonth();
            int fatalitiesNum = dateWithFatalities.getOrDefault(dateWithoutDay, 0);
            dateWithFatalities.put(dateWithoutDay, ++fatalitiesNum);
        });

        return dateWithFatalities;
    }

    private Integer getMaxFatalityInMonth(Map<String, Integer> datesWithFatalities) {
        return datesWithFatalities.values().stream()
                .mapToInt(integer -> integer)
                .max()
                .orElseThrow();
    }

    private Set<String> getMonthWithMaxFatalities(Map<String, Integer> datesWithFatalities) {
        Set<String> maxFatalitiesMonths = new HashSet<>();
        Integer maxFatalitiesNum = getMaxFatalityInMonth(datesWithFatalities);
        datesWithFatalities.entrySet().stream()
                .filter(key -> Objects.equals(key.getValue(), maxFatalitiesNum))
                .forEach(key -> maxFatalitiesMonths.add(key.getKey()));
        return maxFatalitiesMonths;
    }

    public void mainProcessing(List<Fight> fights) {
        this.fights = fights;

        Map<String, Integer> datesWithFatalities = getStringDateWithFatalities();
        Set<String> maxFatalitiesMonths = getMonthWithMaxFatalities(datesWithFatalities);
        System.out.println("\nБольше всего фаталити было сделано в следующие месяцы: ");
        maxFatalitiesMonths.forEach(m -> System.out.println("  " + m + " "));

        System.out.println("---");

        Set<String> allMembers = getAllMembers();
        allMembers.forEach(m -> System.out.println(m + "; побед: " + getMemberWins(m)));

        System.out.println("---");

        Set<Integer> tournaments = getAllTournaments();
        tournaments.forEach(t -> {
            Set<String> membersOfTournament = getMembersOfTournament(t);
            System.out.print("Участники турнира [" + t + "]: ");
            membersOfTournament.forEach(m -> System.out.print(m + "; "));
            System.out.println();
        });
    }
}