package Repository.Help;

public class DataChecker {
    private DataChecker(){

    }

    public static boolean isCorrectNumber(int number){
         return number >= 1 && number <= 22;
    }
}
