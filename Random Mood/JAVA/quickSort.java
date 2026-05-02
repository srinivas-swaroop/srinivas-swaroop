class quickSort{
    static void quickSortPart(int arr[], int left, int right){
        if(left < right){
            int index = partition(arr, left, right);
            quickSortPart(arr, left, index-1);
            quickSortPart(arr, index+1, right);
        }
    }

    static int partition(int arr[], int left, int right){
        int pivot = arr[right];

        int i = left-1;

        for(int j=left; j<right; j++){
            if(pivot >= arr[j]){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[right];
        arr[right] = arr[i];
        arr[i] = temp;

        return i;
    }
    public static void main(String args[]){
        int arr[] = {5,6,2,3,41,7,8};
        quickSortPart(arr, 0, arr.length-1);

        for(int i : arr) System.out.print(i+" ");
    }
}