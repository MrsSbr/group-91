package models;

import java.time.LocalDate;

public class Fight {
    private final LocalDate date;
    private final int tournamentNumber;
    private final String memberFirst;
    private final String memberSecond;
    private final String winner;
    private final int isFatality;

    public Fight(String date, String number, String memberFirst, String memberSecond, String winner, String fatality) {
        this.date = LocalDate.parse(date);
        this.tournamentNumber = Integer.parseInt(number);
        this.memberFirst = memberFirst;
        this.memberSecond = memberSecond;
        this.winner = winner;
        this.isFatality = Integer.parseInt(fatality);
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTournamentNumber() {
        return tournamentNumber;
    }

    public String getMemberFirst() {
        return memberFirst;
    }

    public String getMemberSecond() {
        return memberSecond;
    }

    public String getWinner() {
        return winner;
    }

    public int getIsFatality() {
        return isFatality;
    }

    @Override
    public String toString() {
        String info = "Дата проведения: "
                + date + "\nНомер турнира: "
                + tournamentNumber + "\nУчастник 1:"
                + memberFirst + "\nУчастник 2: "
                + memberSecond + "\nПобедитель: "
                + winner + "\nФаталити: ";
        return isFatality == 0 ? info + "Нет" : info + "Да";
    }
}
