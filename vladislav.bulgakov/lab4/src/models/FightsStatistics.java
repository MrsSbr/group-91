package models;

import java.time.LocalDate;
import java.util.*;

public class FightsStatistics {
    private List<Fight> fights;

    private Set<Integer> getAllTournaments() {
        Set<Integer> tournaments = new HashSet<>();
        for (Fight f : fights) {
            tournaments.add(f.getTournamentNumber());
        }
        return tournaments;
    }

    private Set<String> getAllMembers() {
        Set<String> members = new HashSet<>();
        for (Fight f : fights) {
            members.add(f.getMemberFirst());
            members.add(f.getMemberSecond());
        }
        return members;
    }

    private Set<String> getMembersOfTournament(int tournamentNum) {
        Set<String> members = new HashSet<>();
        for (Fight f : fights) {
            if (f.getTournamentNumber() == tournamentNum) {
                members.add(f.getMemberFirst());
                members.add(f.getMemberSecond());
            }
        }
        return members;
    }

    private int getMemberWins(String member) {
        int winsNum = 0;
        for (Fight f : fights) {
            if (f.getWinner().equals(member)) {
                winsNum++;
            }
        }
        return winsNum;
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
        for (Fight f : fights) {
            if (isDateFrom3YearGapWithFatality(f)) {
                String dateWithoutDay = f.getDate().getYear() + "-" + f.getDate().getMonth();
                int fatalitiesNum = dateWithFatalities.getOrDefault(dateWithoutDay, 0);
                dateWithFatalities.put(dateWithoutDay, ++fatalitiesNum);
            }
        }
        return dateWithFatalities;
    }

    private Integer getMaxFatalityInMonth(Map<String, Integer> datesWithFatalities) {
        Integer maxFatalitiesNum = -1;
        for (Map.Entry<String, Integer> me : datesWithFatalities.entrySet()) {
            Integer value = me.getValue();
            if (maxFatalitiesNum.compareTo(value) < 0) {
                maxFatalitiesNum = value;
            }
        }
        return maxFatalitiesNum;
    }

    private Set<String> getMonthWithMaxFatalities(Map<String, Integer> datesWithFatalities) {
        Set<String> maxFatalitiesMonths = new HashSet<>();
        Integer maxFatalitiesNum = getMaxFatalityInMonth(datesWithFatalities);
        for (Map.Entry<String, Integer> me : datesWithFatalities.entrySet()) {
            if (Objects.equals(me.getValue(), maxFatalitiesNum)) {
                maxFatalitiesMonths.add(me.getKey());
            }
        }
        return maxFatalitiesMonths;
    }

    public void mainProcessing(List<Fight> fights) {
        this.fights = fights;
        Map<String, Integer> datesWithFatalities = getStringDateWithFatalities();
        Set<String> maxFatalitiesMonths = getMonthWithMaxFatalities(datesWithFatalities);
        System.out.println("\nБольше всего фаталити было сделано в следующие месяцы: ");
        for (String m : maxFatalitiesMonths) {
            System.out.println("  " + m + " ");
        }

        System.out.println("---");

        Set<String> allMembers = getAllMembers();
        for (String m : allMembers) {
            System.out.println(m + "; побед: " + getMemberWins(m));
        }

        System.out.println("---");

        Set<Integer> tournaments = getAllTournaments();
        for (Integer t : tournaments) {
            Set<String> membersOfTournament = getMembersOfTournament(t);
            System.out.print("Участники турнира [" + t + "]: ");
            for (String m : membersOfTournament) {
                System.out.print(m + "; ");
            }
            System.out.println();
        }
    }
}