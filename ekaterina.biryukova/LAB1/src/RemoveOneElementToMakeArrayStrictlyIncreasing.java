public class RemoveOneElementToMakeArrayStrictlyIncreasing {
    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static boolean IsIncreasing(int[] array, int start, int end) {
        for (int index = start; index < end - 1; index++) {
            if (array[index] >= array[index + 1])
                return false;
        }
        return true;
    }

    public static boolean IsPossible(int[] array, int size) {

        for (int index = 0; index < size - 1; index++) {
            if (array[index] >= array[index + 1]) {
                boolean isIncr = IsIncreasing(array, index + 1, size);
                if (!isIncr) return false;
                else {
                    if (index == 0) return true;
                    else if (array[index - 1] >= array[index + 1]) {
                        return array[index] < array[index + 2];
                    } else if (array[index - 1] < array[index + 1])
                        return true;
                }
            }
        }
        return true;
    }
}

