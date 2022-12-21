package classes;

import enums.ListOfSubjects;

import java.util.*;

public class ArchiveOfOlympiads {
    private final Map<Integer, Olympiad> olympiadsList;

    public ArchiveOfOlympiads(Map<Integer, Olympiad> olympiadsList) {
        this.olympiadsList = olympiadsList;
    }

    public List<String> creatingListOfStudentsWhoHaveTakenPlacesEachYearOfTheirStudiesAtTheSchool() {
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

        List<String> listAnswer = new LinkedList<>();

        listOfStudents.keySet().forEach(people -> {
            if (listOfStudents.get(people) == 11) {
                listAnswer.add(people);
            }
        });

        return listAnswer;
    }

    public List<String> creatingListOfStudentsWhoHaveOccupiedPlacesInTheLast10Years(int year) {
        List<String> listOfStudents = new LinkedList<>();

        olympiadsList.values().forEach(olympiad -> {
            if (olympiad.getYearInWhichTheOlympiadWasHeld() > 2012 && Objects.equals(ListOfSubjects.getById(year),
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
        });

        return listOfStudents;
    }

    public List<String> creatingListOfStudentsWhoParticipatedInLargeNumberOfOlympiads(int year) {

        Map<String, Integer> listOfStudents = new HashMap<>();
        List<String> listAnswer = new LinkedList<>();

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

        listOfStudents.keySet().forEach(people -> {
            if (listOfStudents.get(people) == max[0]) {
                listAnswer.add(people);
            }
        });

        return listAnswer;
    }
}