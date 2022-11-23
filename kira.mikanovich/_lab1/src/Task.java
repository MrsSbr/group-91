public class Task {
    public static int getMountain(int[] arr, int size) {
        int pos = -1;
        for (int i = 1; i < size; i++) {
            if (arr[i] == arr[i - 1]) {
                System.out.println("Значения рядом равны");
                return -1;
            }
            if (arr[i] > arr[i - 1]) {
                pos = i;
            }
        }
        return pos;
    }
}
