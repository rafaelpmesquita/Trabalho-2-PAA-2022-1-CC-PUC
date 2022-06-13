
class QuickSort {

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static int particionar(int[] array, int esq, int dir) {
        int pivot = array[dir];

        int i = (esq - 1);

        for (int j = esq; j <= dir - 1; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, dir);
        return (i + 1);
    }

    static void quickSort(int[] array, int esq, int dir) {
        if (esq < dir) {

            int particao = particionar(array, esq, dir);
            quickSort(array, esq, particao - 1);
            quickSort(array, particao + 1, dir);
        }
    }

    public static void main(String[] args) {
        int[] array = { 11,12,31,30, 7, 8, 9, 1, 5 };
        int n = array.length;

        quickSort(array, 0, n - 1);
        System.out.println("Array ordenado: ");
        for (int i = 0; i < n; i++)
            System.out.print(array[i] + " ");

    }
}
