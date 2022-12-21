//Напишите класс, который принимает с клавиатуры информацию о тех спектаклях, на которые решил приобрести билет каждый
// (подумайте, как можно смоделировать ввод с помощью случайных чисел), и затем выводит на экран следующую информацию:
//  • количество билетов, заказанных на каждый спектакль;
//  • самый популярный спектакль (следует учесть вариант, что может быть несколько таких спектаклей);
//  • спектакль (спектакли), на который решили приобрести билеты

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class ShowsInformation {
    private final List<Integer> tickets;
    private final List<Integer> ticketsCount;
    private final HashSet<Integer> popularShows;
    private final HashSet<Integer> purchasedTickets;

    public ShowsInformation(List<Integer> tickets) {
        int countOfShows = Collections.max(tickets) + 1;
        this.tickets = tickets;
        ticketsCount = new ArrayList<>(Collections.nCopies(countOfShows, 0));
        purchasedTickets = new HashSet<>();
        popularShows = new HashSet<>();
    }

    public List<Integer> getAllTicketsCount(boolean enableCaching) {
        if (enableCaching && !ticketsCount.isEmpty()) {
            return ticketsCount;
        }

        Collections.fill(ticketsCount, 0);// заполняем нулями

        tickets.forEach(i -> ticketsCount.set(i, ticketsCount.get(i) + 1));

        return ticketsCount;
    }

    private void calculatePopularShows() {
        int max = Collections.max(ticketsCount);
        popularShows.clear();

        IntStream
                .range(0, ticketsCount.size())
                .filter(i -> ticketsCount.get(i) == max)
                .forEach(popularShows::add);
    }

    public HashSet<Integer> getPopularShows(boolean enableCaching) {
        if (enableCaching) {
            if (!popularShows.isEmpty()) {
                return popularShows;
            }

            if (!ticketsCount.isEmpty()) {
                calculatePopularShows();
                return popularShows;
            }
        }

        getAllTicketsCount(enableCaching);
        calculatePopularShows();
        return popularShows;
    }

    private void calculatePurchasedTickets() {
        purchasedTickets.clear();  // сет - множество

        IntStream
                .range(0, ticketsCount.size())
                .filter(i -> ticketsCount.get(i) != 0)
                .forEach(purchasedTickets::add);
    }

    public HashSet<Integer> getPurchasedTickets(boolean enableCaching) {
        if (enableCaching) {
            if (!purchasedTickets.isEmpty()) {
                return purchasedTickets;
            }

            if (!ticketsCount.isEmpty()) {
                calculatePurchasedTickets();
                return purchasedTickets;
            }
        }

        getAllTicketsCount(enableCaching);
        calculatePurchasedTickets();
        return purchasedTickets;
    }


    public void testMethod() {
        getAllTicketsCount(false);
        getPurchasedTickets(false);
        getPopularShows(false);
    }
}