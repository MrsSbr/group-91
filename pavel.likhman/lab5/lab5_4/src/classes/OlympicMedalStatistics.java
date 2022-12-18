package classes;

import java.util.*;

public class OlympicMedalStatistics {
    private List<SportsmenInfo> sportsmenInfoList;

    private List<Integer> getCountOfMedalsInCountry(String country) {
        List<Integer> countOfMedalsInCountry = new ArrayList<>(List.of(0, 0, 0));
        sportsmenInfoList.stream()
                .filter(info -> info.getCountry().equals(country) && info.getPlace() > 0 && info.getPlace() < 4)
                .forEach(info -> countOfMedalsInCountry.set(info.getPlace() - 1, countOfMedalsInCountry.get(info.getPlace() - 1) + 1));
        return countOfMedalsInCountry;
    }

    private boolean isGreater(List<Integer> count1, List<Integer> count2) {
        if (count1.get(0) > count2.get(0)) {
            return true;
        }
        if (count1.get(0) < count2.get(0)) {
            return false;
        }
        if (count1.get(1) > count2.get(1)) {
            return true;
        }
        if (count1.get(1) < count2.get(1)) {
            return false;
        }
        return count1.get(2) > count2.get(2);
    }

    private Set<String> getTop3MedalCountCountries() {
        Map<String, List<Integer>> countriesWithMedalsCount = new HashMap<>();
        sportsmenInfoList.forEach(info -> countriesWithMedalsCount.put(info.getCountry(), getCountOfMedalsInCountry(info.getCountry())));
        List<List<Integer>> counts = new ArrayList<>(countriesWithMedalsCount.values());
        List<String> countries = new ArrayList<>(countriesWithMedalsCount.keySet());
        for (int i = 0; i < counts.size() - 1; i++) {
            for (int j = i; j < counts.size(); j++) {
                if (!isGreater(counts.get(i), counts.get(j))) {
                    Collections.swap(counts, i, j);
                    Collections.swap(countries, i, j);
                }
            }
        }
        Set<String> top3Countries = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            top3Countries.add(countries.get(i));
        }
        return top3Countries;
    }

    private List<String> getSportsmanForKindOfSportWithPlaces(String sport) {
        List<String> sportsman = new ArrayList<>();
        sportsmenInfoList.stream()
                .filter(info -> info.getKindOfSport().equals(sport) && info.getPlace() > 0 && info.getPlace() < 4)
                .forEach(info -> sportsman.add(info.getFullName()));
        return sportsman;
    }

    private Map<String, List<String>> getListOfSportsmenWithPlacesInKindOfSport() {
        Map<String, List<String>> sportWithSportsman = new HashMap<>();
        sportsmenInfoList.forEach(info -> sportWithSportsman.put(info.getKindOfSport(), getSportsmanForKindOfSportWithPlaces(info.getKindOfSport())));
        return sportWithSportsman;
    }

    private int getCountOfMedalsForSportsmen(String name) {
        return (int) sportsmenInfoList.stream().
                filter(info -> info.getFullName().equals(name) && info.getPlace() > 0 && info.getPlace() < 4).count();
    }

    private String getSportsmenWithMostMedals() {
        Map<String, Integer> sportsmanWithMedals = new HashMap<>();
        sportsmenInfoList.forEach(info -> sportsmanWithMedals.put(info.getFullName(), getCountOfMedalsForSportsmen(info.getFullName())));
        int max = Collections.max(sportsmanWithMedals.values());
        return sportsmanWithMedals.keySet().stream().filter(name -> sportsmanWithMedals.get(name) == max).findFirst().get();
    }

    public void task(List<SportsmenInfo> sportsmenInfoList) {
        this.sportsmenInfoList = sportsmenInfoList;
        System.out.println("Task 1. Top 3 countries by medal count: " + getTop3MedalCountCountries());
        System.out.println("Task 2. List of sportsman who won prizes for each kind of sports: ");
        getListOfSportsmenWithPlacesInKindOfSport().forEach((key, value) -> System.out.println(key + " — " + value));
        System.out.print("Task 3. Sportsmen with the most medals: " + getSportsmenWithMostMedals());
    }

}