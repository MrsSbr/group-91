package classes;

import java.util.*;

public class OlympicMedalStatistics {
    private List<SportsmenInfo> sportsmenInfoList;

    private Set<String> getAllCountries() {
        Set<String> countries = new HashSet<>();
        for (SportsmenInfo info : sportsmenInfoList) {
            countries.add(info.getCountry());
        }
        return countries;
    }

    private CountOfMedalsInCountry getCountOfMedalsInCountry(String country) {
        CountOfMedalsInCountry count = new CountOfMedalsInCountry(country, 0, 0, 0);
        for (SportsmenInfo info : sportsmenInfoList) {
            if (info.getCountry().equals(country)) {
                switch (info.getPlace()) {
                    case 1:
                        count.setCountFirstPlaces(count.getCountFirstPlaces() + 1);
                        break;
                    case 2:
                        count.setCountSecondPlaces(count.getCountSecondPlaces() + 1);
                        break;
                    case 3:
                        count.setCountThirdPlaces(count.getCountThirdPlaces() + 1);
                        break;
                    default:
                        break;
                }
            }
        }
        return count;
    }

    private List<CountOfMedalsInCountry> getCountOfMedalsInAllCountries() {
        List<CountOfMedalsInCountry> counts = new ArrayList<>();
        Set<String> countries = getAllCountries();
        for (String country : countries) {
            counts.add(getCountOfMedalsInCountry(country));
        }
        return counts;
    }

    private Set<String> getTop3MedalCountCountries() {
        List<CountOfMedalsInCountry> counts = getCountOfMedalsInAllCountries();
        Set<String> top3Countries = new HashSet<>();
        for (int i = 0; i < counts.size() - 1; i++) {
            for (int j = i + 1; j < counts.size(); j++) {
                if (!counts.get(i).isGreater(counts.get(j))) {
                    Collections.swap(counts, i, j);
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            top3Countries.add(counts.get(i).getCountry());
        }
        return top3Countries;
    }

    private Set<String> getAllKindsOfSports() {
        Set<String> kindsOfSports = new HashSet<>();
        for (SportsmenInfo info : sportsmenInfoList) {
            kindsOfSports.add(info.getKindOfSport());
        }
        return kindsOfSports;
    }

    private List<String> getListOfSportsmenWithPlacesInKindOfSport(String sport) {
        List<String> sportsman = new ArrayList<>();
        for (SportsmenInfo info : sportsmenInfoList) {
            if (info.getKindOfSport().equals(sport) && info.getPlace() > 0 && info.getPlace() < 4) { // Здесь в зависимости от того, имеется ли в виду заняли с 1 по 3 места или вообще все места
                sportsman.add(info.getFullName());
            }
        }
        return sportsman;
    }

    private Set<String> getAllSportsman() {
        Set<String> sportsman = new HashSet<>();
        for (SportsmenInfo info : sportsmenInfoList) {
            sportsman.add(info.getFullName());
        }
        return sportsman;
    }

    private String getSportsmenWithMostMedals() {
        Set<String> sportsman = getAllSportsman();
        String needName = "";
        int max = 0;
        int current;
        for (String name : sportsman) {
            current = 0;
            for (SportsmenInfo info : sportsmenInfoList) {
                if (info.getFullName().equals(name) && info.getPlace() > 0 && info.getPlace() < 4) {
                    current++;
                }

                if (current > max) {
                    max = current;
                    needName = name;
                }
            }
        }
        return needName;
    }

    public void task(List<SportsmenInfo> sportsmenInfoList) {
        this.sportsmenInfoList = sportsmenInfoList;
        System.out.println("Task 1. Top 3 countries by medal count: ");
        for (String country : getTop3MedalCountCountries()) {
            System.out.println(country);
        }
        System.out.println("Task 2. List of sportsman who won prizes for each kind of sports: ");
        for (String sport : getAllKindsOfSports()) {
            System.out.print(sport + " — ");
            for (String name : getListOfSportsmenWithPlacesInKindOfSport(sport)) {
                System.out.print(name + "; ");
            }
            System.out.println();
        }
        System.out.print("Task 3. Sportsmen with the most medals: " + getSportsmenWithMostMedals());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OlympicMedalStatistics) {
            return sportsmenInfoList.equals(((OlympicMedalStatistics) obj).sportsmenInfoList);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return sportsmenInfoList.hashCode();
    }
}
