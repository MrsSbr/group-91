//Напишите класс, который принимает с клавиатуры информацию о тех спектаклях, на которые решил приобрести билет каждый
// (подумайте, как можно смоделировать ввод с помощью случайных чисел), и затем выводит на экран следующую информацию:
//  • количество билетов, заказанных на каждый спектакль;
//  • самый популярный спектакль (следует учесть вариант, что может быть несколько таких спектаклей);
//  • спектакль (спектакли), на который решили приобрести билеты

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ShowsInformation {
    private int countOfShows;
    private List<Integer> tickets;
    private final List<Integer> ticketsCount;
    private final HashSet<Integer> popularShows;
    private final HashSet<Integer> purchasedTickets;

    public ShowsInformation(List<Integer> tickets) {
        countOfShows = Collections.max(tickets) + 1;
        this.tickets = tickets;
        ticketsCount = new ArrayList<>(Collections.nCopies(countOfShows, 0));
        purchasedTickets = new HashSet<>();
        popularShows = new HashSet<>();
    }

    public void setTickets(List<Integer> tickets) {
        this.tickets = tickets;
        this.countOfShows = Collections.max(tickets) + 1;
    }

    public List<Integer> getAllTicketsCount() {
        if (!ticketsCount.isEmpty()) {
            return ticketsCount;
        }

        Collections.fill(ticketsCount, 0);// заполняем нулями

        for (int i : tickets) {
            ticketsCount.set(i, ticketsCount.get(i) + 1);
        }

        return ticketsCount;
    }

    private void calculatePopularShows() {
        int max = Collections.max(ticketsCount);
        popularShows.clear();

        for(int i = 0; i < ticketsCount.size(); ++i) {
            if(ticketsCount.get(i) == max) {
                popularShows.add(i);
            }
        }
    }

    public HashSet<Integer> getPopularShows() {
        if (!popularShows.isEmpty()) {
            return popularShows;
        }

        if (!ticketsCount.isEmpty()) {
            calculatePopularShows();
            return popularShows;
        }

        getAllTicketsCount();
        calculatePopularShows();
        return popularShows;
    }

    private void calculatePurchasedTickets() {
        purchasedTickets.clear();  // сет - множество

        for (int i = 0; i < ticketsCount.size(); ++i) {
            if (ticketsCount.get(i) != 0) {
                purchasedTickets.add(i);
            }
        }
    }

    public HashSet<Integer> getPurchasedTickets() {
        if (!purchasedTickets.isEmpty()) {
            return purchasedTickets;
        }
        if (!ticketsCount.isEmpty()) {
            calculatePurchasedTickets();
            return purchasedTickets;
        }

        getAllTicketsCount();
        calculatePurchasedTickets();
        return purchasedTickets;
    }

    public void testMethod() {
        getAllTicketsCount();
        getPurchasedTickets();
        getPopularShows();
    }
}