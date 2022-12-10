package classes;

import java.util.*;

public class OlympicMedalStatistics {
    private List<SportsmenInfo> sportsmenInfoList;

    private List<Integer> getCountOfMedalsInCountry(String country) {
        List<Integer> countOfMedalsInCountry = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            countOfMedalsInCountry.add(0);
        }
        for (SportsmenInfo info : sportsmenInfoList) {
            int place = info.getPlace();
            if (info.getCountry().equals(country) && place > 0 && place < 4) {
                countOfMedalsInCountry.set(place - 1, countOfMedalsInCountry.get(place - 1) + 1);
            }
        }
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
        for (SportsmenInfo info : sportsmenInfoList) {
            countriesWithMedalsCount.put(info.getCountry(), getCountOfMedalsInCountry(info.getCountry()));
        }
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
        for (SportsmenInfo info : sportsmenInfoList) {
            if (info.getKindOfSport().equals(sport) && info.getPlace() > 0 && info.getPlace() < 4) {
                sportsman.add(info.getFullName());
            }
        }
        return sportsman;
    }

    private Map<String, List<String>> getListOfSportsmenWithPlacesInKindOfSport() {
        Map<String, List<String>> sportWithSportsman = new HashMap<>();
        for (SportsmenInfo info : sportsmenInfoList) {
            sportWithSportsman.put(info.getKindOfSport(), getSportsmanForKindOfSportWithPlaces(info.getKindOfSport()));
        }
        return sportWithSportsman;
    }

    private int getCountOfMedalsForSportsmen(String name) {
        int count = 0;
        for (SportsmenInfo info : sportsmenInfoList) {
            if (info.getFullName().equals(name) && info.getPlace() > 0 && info.getPlace() < 4) {
                count++;
            }
        }
        return count;
    }

    private String getSportsmenWithMostMedals() {
        String needName = "";
        Map<String, Integer> sportsmanWithMedals = new HashMap<>();
        for (SportsmenInfo info : sportsmenInfoList) {
            sportsmanWithMedals.put(info.getFullName(), getCountOfMedalsForSportsmen(info.getFullName()));
        }
        int max = Collections.max(sportsmanWithMedals.values());
        for (String name : sportsmanWithMedals.keySet()) {
            if (sportsmanWithMedals.get(name) == max) {
                needName = name;
            }
        }
        return needName;
    }

    public void task(List<SportsmenInfo> sportsmenInfoList) {
        this.sportsmenInfoList = sportsmenInfoList;
        System.out.println("Task 1. Top 3 countries by medal count: " + getTop3MedalCountCountries());
        System.out.println("Task 2. List of sportsman who won prizes for each kind of sports: ");
        for (Map.Entry<String, List<String>> entry : getListOfSportsmenWithPlacesInKindOfSport().entrySet()) {
            System.out.println(entry.getKey() + " — " + entry.getValue());
        }
        System.out.print("Task 3. Sportsmen with the most medals: " + getSportsmenWithMostMedals());
    }

}