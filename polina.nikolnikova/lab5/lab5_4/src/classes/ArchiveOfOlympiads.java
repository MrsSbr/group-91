package classes;

import enums.ListOfSubjects;

import java.util.*;

public record ArchiveOfOlympiads(Map<Integer, Olympiad> olympiadsList) {

    public Set<String> creatingListOfStudentsWhoHaveTakenPlacesEachYearOfTheirStudiesAtTheSchool() {
        Map<String, Integer> listOfStudents = new HashMap<>();

        int[] i = {1};
        while (i[0] != 12) {
            olympiadsList.values().forEach(olympiad -> {
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
                            listOfStudents.get(olympiad.getStudentWhoTookTheFirstPlace()) == i[0] - 1) {
                        listOfStudents.replace(olympiad.getStudentWhoTookTheFirstPlace(), i[0]);
                    }

                    if (listOfStudents.containsKey(olympiad.getStudentWhoTookTheSecondPlace()) &&
                            listOfStudents.get(olympiad.getStudentWhoTookTheSecondPlace()) == i[0] - 1) {
                        listOfStudents.replace(olympiad.getStudentWhoTookTheSecondPlace(), i[0]);
                    }

                    if (listOfStudents.containsKey(olympiad.getStudentWhoTookTheThirdPlace()) &&
                            listOfStudents.get(olympiad.getStudentWhoTookTheThirdPlace()) == i[0] - 1) {
                        listOfStudents.replace(olympiad.getStudentWhoTookTheThirdPlace(), i[0]);
                    }
                }
            });

            i[0]++;
        }

        Set<String> listAnswer = new HashSet<>();

        listOfStudents.keySet().forEach(people -> {
            if (listOfStudents.get(people) == 11) {
                listAnswer.add(people);
            }
        });

        return listAnswer;
    }

    public Set<String> creatingListOfStudentsWhoHaveOccupiedPlacesInTheLast10Years(int year) {
        Set<String> listOfStudents = new HashSet<>();

        olympiadsList.values().forEach(olympiad -> {
            if (olympiad.getYearInWhichTheOlympiadWasHeld() > 2012 && Objects.equals(ListOfSubjects.getById(year),
                    olympiad.getSubjectOnWhichTheOlympiadWasHeld())) {

                listOfStudents.add(olympiad.getStudentWhoTookTheFirstPlace());
                listOfStudents.add(olympiad.getStudentWhoTookTheSecondPlace());
                listOfStudents.add(olympiad.getStudentWhoTookTheThirdPlace());
            }
        });

        return listOfStudents;
    }

    public Set<String> creatingListOfStudentsWhoParticipatedInLargeNumberOfOlympiads(int year) {

        Map<String, Integer> listOfStudents = new HashMap<>();

        olympiadsList.values().forEach(olympiad -> {
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
        });

        int[] max = {0};

        listOfStudents.values().forEach(number -> {
            if (max[0] < number) {
                max[0] = number;
            }
        });

        Set<String> listAnswer = new HashSet<>();

        listOfStudents.keySet().forEach(people -> {
            if (listOfStudents.get(people) == max[0]) {
                listAnswer.add(people);
            }
        });

        return listAnswer;
    }
}