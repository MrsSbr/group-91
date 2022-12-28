package loger;

import java.time.DayOfWeek;
import java.util.*;

public class Messages {

    private final Map<String, List<Message>> messages;

    public Messages() {

        messages = new HashMap<>();

    }


    public String mostLenghtMessage() {

        String personMax = "";
        int maxLengthWord = 0;

        for (var messagesPerson : messages.entrySet()) {

            for (var message : messagesPerson.getValue()) {

                if(message.getText().length()>maxLengthWord) {

                    maxLengthWord = message.getText().length();
                    personMax = messagesPerson.getKey();

                }

            }

        }

        return personMax;
    }


    public String mostPopularDayForWord(String word) {

        Map<DayOfWeek,Integer> dayForWord = new HashMap<>();

        for (var messagesPerson : messages.entrySet()) {

            for (var message : messagesPerson.getValue()) {

                if(message.getText().contains(word)) {

                    if (!dayForWord.containsKey(message.getDate().getDayOfWeek())) {

                        dayForWord.put(message.getDate().getDayOfWeek(), 0);

                    }
                    else {

                        dayForWord.put(message.getDate().getDayOfWeek(),
                                dayForWord.get(message.getDate().getDayOfWeek())+1);

                    }


                }

            }

        }

        List<Map.Entry<DayOfWeek,Integer>> dayForWordList = new ArrayList<>(dayForWord.entrySet());
        dayForWordList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return dayForWordList.get(0).getKey().toString();
    }
    public String worstPopularEmodgi() {

        Map<String,Integer> countEmodgi = new HashMap<>();
        countEmodgi.put(")",0);
        countEmodgi.put("(",0);

        for (var messagesPerson : messages.entrySet()) {

            for (var message : messagesPerson.getValue()) {

                countEmodgi.put(")", countEmodgi.get(")") + message.getText().length()
                        - message.getText().replace(String.valueOf(")"), "").length());
                countEmodgi.put("(", countEmodgi.get("(") + message.getText().length()
                        - message.getText().replace(String.valueOf("("), "").length());

            }

        }

        List<Map.Entry<String,Integer>> countEmodgiList = new ArrayList<>(countEmodgi.entrySet());
        countEmodgiList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return countEmodgiList.get(0).getKey().toString();
    }




    public void add(String line) {

        String[] elem = line.split(";");
        add(elem[0], elem[1], elem[2]);

    }

    public void add(String date, String recipient, String text) {

        if (!messages.containsKey(recipient)) {

            List<Message> tmp = new ArrayList<>();
            tmp.add(new Message(date, text));
            messages.put(recipient, tmp);

        }
        else {

            messages.get(recipient).add(new Message(date, text));

        }



    }

}

