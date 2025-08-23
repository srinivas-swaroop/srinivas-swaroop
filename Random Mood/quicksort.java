public class quicksort {
    static void qSort(int arr[], int left, int right){
        if(right >= left){
        int pivot = sort(arr, left, right);
            qSort(arr, left, pivot - 1);
            qSort(arr, pivot+1, right);

        }
    }

    static int sort(int arr[], int left, int right){
        int pivot = arr[right];
        int i = left - 1;

        for(int j=left; j<right; j++){
            if(arr[j] < pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        i++;
        arr[right] = arr[i];
        arr[i] = pivot;

        return i;

    }

    static void mergeDivide(int arr[], int left, int right){
        int mid = left+(right-left)/2;

        if(right > left){
            mergeDivide(arr, left, mid);
            mergeDivide(arr, mid+1, right);
            merge(arr, left, right);
        }
    }

    static void merge(int arr[], int left, int right){
        int mid = left+(right-left)/2;
        int arr1[] = new int[mid-left+1];
        int arr2[] = new int[right-mid];

        for (int i = 0; i < mid-left+1; i++)
        arr1[i] = arr[left + i];

    for (int j = 0; j < right-mid; j++)
        arr2[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = left;

    while (i < mid-left+1 && j < right-mid) {
        if (arr1[i] <= arr2[j]) {
            arr[k++] = arr1[i++];
        } else {
            arr[k++] = arr2[j++];
        }
    }

    while (i < mid-left+1) {
        arr[k++] = arr1[i++];
    }

    while (j < right-mid) {
        arr[k++] = arr2[j++];
    }

    }
    public static void main(String[] args) {
        int arr[] = {10,2,5,0};
        qSort(arr, 0, arr.length-1);

        for(int val : arr){
            System.out.print(val+" ");
        }
    }
}
