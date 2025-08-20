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

        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;

        return i;

    }

    static void mergeDivide(int arr[], int left, int right){
        int mid = left+(right-left)/2;

        if(right >= left){
            mergeDivide(arr, left, mid-1);
            mergeDivide(arr, mid+1, right);
            merge(arr, left, right);
        }
    }

    static void merge(int arr[], int left, int right){
        int mid = left+(right-left)/2;
        int arr1[] = new int[mid];
        int arr2[] = new int[right-mid+1];

        for(int i=left; i<=mid; i++){
            arr1[i] = arr[i];
        }

        for(int i=mid+1; i<right; i++){
            arr2[i] = arr[i];
        }

        int i = 0;
        int j=0;
        int k = 0;

        while(j < arr1.length && k <arr2.length){
            if(arr1[j] > arr2[k]){
                arr[i] = arr2[k];
                k++;
            } else{
                arr[i] = arr1[j];
                j++;
            }
            i++;
        }

        while(j < arr1.length){
            arr[i] = arr1[j];
            i++;
            j++;
        }

        while(k < arr1.length){
            arr[i] = arr1[k];
            i++;
            k++;
        }

    }
    public static void main(String[] args) {
        int arr[] = {2,5,1,70,99,9};
        qSort(arr, 0, arr.length-1);

        for(int val : arr){
            System.out.print(val+" ");
        }
    }
}
