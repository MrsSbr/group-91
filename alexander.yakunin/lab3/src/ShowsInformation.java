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
    public ShowsInformation(List<Integer> tickets) {
        countOfShows = Collections.max(tickets) + 1;
        this.tickets = tickets;
        ticketsCount = new ArrayList<>(Collections.nCopies(countOfShows, 0));
        purchasedTickets = new HashSet<>();
        popularShows = new HashSet<>();
    }
    private int countOfShows;
    private List<Integer> tickets;
    private List<Integer> ticketsCount;
    private HashSet<Integer> popularShows;
    private HashSet<Integer> purchasedTickets;


    private boolean isCachedTicketsCount = false;
    private boolean isCachedPopular = false;
    private boolean isCachePurchased = false;

    public void setTickets(List<Integer> tickets) {
        this.tickets = tickets;
        this.countOfShows = Collections.max(tickets) + 1;
        this.isCachedPopular = this.isCachedTicketsCount = this.isCachePurchased = false;
    }

    public List<Integer> getAllTicketsCount() {
        if (isCachedTicketsCount) {
            return ticketsCount;
        }

        Collections.fill(ticketsCount, 0);// заполняем нулями

        for (int i : tickets) {
            ticketsCount.set(i, ticketsCount.get(i) + 1);
        }

        isCachedTicketsCount = true;
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
        if (isCachedPopular) {
            return popularShows;
        }

        if (isCachedTicketsCount) {
            calculatePopularShows();
            isCachedPopular = true;
            return popularShows;
        }

        getAllTicketsCount();
        calculatePopularShows();
        isCachedPopular = true;
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
        if (isCachePurchased) {
            return purchasedTickets;
        }
        if (isCachedTicketsCount) {
            calculatePurchasedTickets();
            isCachePurchased = true;
            return purchasedTickets;
        }

        getAllTicketsCount();
        calculatePurchasedTickets();
        isCachePurchased = true;
        return purchasedTickets;
    }

    public void testMethod() {
        isCachedTicketsCount = false;
        getAllTicketsCount();
        isCachedTicketsCount = isCachePurchased = false;
        getPurchasedTickets();
        isCachedTicketsCount = isCachedPopular = false;
        getPopularShows();
    }
}