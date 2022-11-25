public class Main {
    public static void main(String[] args) {


        int size = CorrectInput.getIntInRange(1, 1000);
        int[] array = CorrectInput.getIntArray(size);

        RemoveOneElementToMakeArrayStrictlyIncreasing.printArray(array);
        boolean IsPossible = RemoveOneElementToMakeArrayStrictlyIncreasing.IsPossible(array, size);
        if (IsPossible) {
            System.out.println("Массив можно сделать строго возрастающим при помощи удаления одного элемента\n");
        } else
            System.out.println("Массив нельзя сделать строго возрастающим при помощи удаления одного элемента\n");


    }
}