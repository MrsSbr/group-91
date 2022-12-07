
public class Month{
    public String name;

    public int number;
    public double averageTemp;

    public Month(int number) {
        this.number = number;
        int lowest = -100;
        int highest = 100;
        switch (number) {
            case 1:
                name = "Январь";
                lowest = -10;
                highest = -4;
                break;
            case 2:
                name = "Февраль";
                lowest = -14;
                highest = -2;
                break;
            case 3:
                name = "Март";
                lowest = -8;
                highest = 8;
                break;
            case 4:
                name = "Апрель";
                lowest = 4;
                highest = 14;
                break;
            case 5:
                name = "Май";
                lowest = 10;
                highest = 23;
                break;
            case 6:
                name = "Июнь";
                lowest = 13;
                highest = 28;
                break;
            case 7:
                name = "Июль";
                lowest = 15;
                highest = 33;
                break;
            case 8:
                name = "Август";
                lowest = 14;
                highest = 31;
                break;
            case 9:
                name = "Сентябрь";
                lowest = 12;
                highest = 28;
                break;
            case 10:
                name = "Октябрь";
                lowest = 2;
                highest = 12;
                break;
            case 11:
                name = "Ноябрь";
                lowest = -3;
                highest = 6;
                break;
            case 12:
                name = "Декабрь";
                lowest = -12;
                highest = -2;
                break;
        }
        this.averageTemp = Math.random() * (highest - lowest) + lowest;;
    }

    public double getAVG() {
        return averageTemp;
    }
}