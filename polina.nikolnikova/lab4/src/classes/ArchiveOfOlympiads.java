package classes;

import enums.ListOfSubjects;

import java.util.*;

public record ArchiveOfOlympiads(Map<Integer, Olympiad> olympiadsList) {

    public List<String> creatingListOfStudentsWhoHaveTakenPlacesEachYearOfTheirStudiesAtTheSchool() {
        Map<String, Integer> listOfStudents = new HashMap<>();

        for (int i = 1; i < 12; i++) {
            for (Olympiad olympiad : olympiadsList.values()) {

                if (olympiad.getClassForWhichTheOlympiadWasIntended() == 1) {

                    if (!listOfStudents.containsKey(olympiad.getStudentWhoTookTheFirstPlace())) {
                        listOfStudents.put(olympiad.getStudentWhoTookTheFirstPlace(), 1);
                    }

                    if (!listOfStudents.containsKey(olympiad.getStudentWhoTookTheSecondPlace())) {
                        listOfStudents.put(olympiad.getStudentWhoTookTheSecondPlace(), 1);
                    }

                    if (!listOfStudents.containsKey(olympiad.getStudentWhoTookTheThirdPlace())) {
                        listOfStudents.put(olympiad.getStudentWhoTookTheThirdPlace(), 1);
                    }
                } else {

                    if (listOfStudents.containsKey(olympiad.getStudentWhoTookTheFirstPlace()) &&
                            listOfStudents.get(olympiad.getStudentWhoTookTheFirstPlace()) == i - 1) {
                        listOfStudents.replace(olympiad.getStudentWhoTookTheFirstPlace(), i);
                    }

                    if (listOfStudents.containsKey(olympiad.getStudentWhoTookTheSecondPlace()) &&
                            listOfStudents.get(olympiad.getStudentWhoTookTheSecondPlace()) == i - 1) {
                        listOfStudents.replace(olympiad.getStudentWhoTookTheSecondPlace(), i);
                    }

                    if (listOfStudents.containsKey(olympiad.getStudentWhoTookTheThirdPlace()) &&
                            listOfStudents.get(olympiad.getStudentWhoTookTheThirdPlace()) == i - 1) {
                        listOfStudents.replace(olympiad.getStudentWhoTookTheThirdPlace(), i);
                    }
                }
            }
        }

        List<String> listAnswer = new LinkedList<>();

        for (String people : listOfStudents.keySet()) {
            if (listOfStudents.get(people) == 11) {
                listAnswer.add(people);
            }
        }

        return listAnswer;
    }

    public List<String> creatingListOfStudentsWhoHaveOccupiedPlacesInTheLast10Years(int i) {
        List<String> listOfStudents = new LinkedList<>();

        for (Olympiad olympiad : olympiadsList.values()) {
            if (olympiad.getYearInWhichTheOlympiadWasHeld() > 2012 && Objects.equals(ListOfSubjects.getById(i),
                    olympiad.getSubjectOnWhichTheOlympiadWasHeld())) {

                if (!listOfStudents.contains(olympiad.getStudentWhoTookTheFirstPlace())) {
                    listOfStudents.add(olympiad.getStudentWhoTookTheFirstPlace());
                }

                if (!listOfStudents.contains(olympiad.getStudentWhoTookTheSecondPlace())) {
                    listOfStudents.add(olympiad.getStudentWhoTookTheSecondPlace());
                }

                if (!listOfStudents.contains(olympiad.getStudentWhoTookTheThirdPlace())) {
                    listOfStudents.add(olympiad.getStudentWhoTookTheThirdPlace());
                }
            }
        }

        return listOfStudents;
    }

    public List<String> creatingListOfStudentsWhoParticipatedInLargeNumberOfOlympiads(int year) {

        Map<String, Integer> listOfStudents = new HashMap<>();
        List<String> listAnswer = new LinkedList<>();

        for (Olympiad olympiad : olympiadsList.values()) {

            if (olympiad.getYearInWhichTheOlympiadWasHeld() == year) {

                if (!listOfStudents.containsKey(olympiad.getStudentWhoTookTheFirstPlace())) {
                    listOfStudents.put(olympiad.getStudentWhoTookTheFirstPlace(), 1);
                } else {

                    int x = listOfStudents.get(olympiad.getStudentWhoTookTheFirstPlace());
                    listOfStudents.replace(olympiad.getStudentWhoTookTheFirstPlace(), x + 1);
                }

                if (!listOfStudents.containsKey(olympiad.getStudentWhoTookTheSecondPlace())) {
                    listOfStudents.put(olympiad.getStudentWhoTookTheSecondPlace(), 1);
                } else {

                    int x = listOfStudents.get(olympiad.getStudentWhoTookTheSecondPlace());
                    listOfStudents.replace(olympiad.getStudentWhoTookTheSecondPlace(), x + 1);
                }

                if (!listOfStudents.containsKey(olympiad.getStudentWhoTookTheThirdPlace())) {
                    listOfStudents.put(olympiad.getStudentWhoTookTheThirdPlace(), 1);
                } else {

                    int x = listOfStudents.get(olympiad.getStudentWhoTookTheThirdPlace());
                    listOfStudents.replace(olympiad.getStudentWhoTookTheThirdPlace(), x + 1);
                }
            }
        }

        int max = 0;

        for (Integer number : listOfStudents.values()) {
            if (max < number) {
                max = number;
            }
        }

        for (String people : listOfStudents.keySet()) {
            if (listOfStudents.get(people) == max) {
                listAnswer.add(people);
            }
        }

        return listAnswer;
    }
}