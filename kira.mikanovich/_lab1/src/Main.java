public class Main {
    public static void main(String[] args) {
        System.out.println("Введите размер массива");
        int size = Input.getIntInRange(3, 105);
        int[] arr = Input.getIntArr(size);

        Input.printArr(arr);
        int posMountain = Task.getMountain(arr, size);
        if (posMountain == -1){
            System.out.println("Гора не найдена");
        }
        else{
            System.out.println("Вершина: " + posMountain);
        }


    }
}
