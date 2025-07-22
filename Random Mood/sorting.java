class sorting{
    void mergeSort(int[] arr){
        if(arr.length <= 1) return;

        int mid = arr.length/2;

        int leftArr[] = new int[mid];
        int rightArr[] = new int[arr.length - mid];

        int leftIdx = 0;
        int rightIdx = 0;

        for(int i=0; i<arr.length; i++){
            if(i < mid){
                leftArr[leftIdx] = arr[i];
                leftIdx++;
            } else{
                rightArr[rightIdx] =arr[i];
                rightIdx++;
            }
        }

        mergeSort(leftArr);
        mergeSort(rightArr);

        merge(arr, leftArr, rightArr);

        return;
    }

    void merge(int[]arr, int[]leftArr, int[]rightArr){
        int leftIdx=0;
        int rightIdx = 0;
        int arrIdx = 0;

        while(leftIdx < leftArr.length && rightIdx < rightArr.length){
            if(rightArr[rightIdx]>= leftArr[leftIdx]){
                arr[arrIdx] = leftArr[leftIdx];
                arrIdx++;
                leftIdx++;
            }else{
                arr[arrIdx] = rightArr[rightIdx];
                arrIdx++;
                rightIdx++;
            }
        }

        while(leftIdx < leftArr.length){
            arr[arrIdx] = leftArr[leftIdx];
            arrIdx++;
            leftIdx++;
        }

          while(rightIdx < rightArr.length){
            arr[arrIdx] = rightArr[rightIdx];
            arrIdx++;
            rightIdx++;
        }
    }

    void show(int arr[]){
        int limit = 0;

        while(limit < arr.length){
            System.out.print(arr[limit]+ " ");
            limit++;
        }


    }

    void insertionSort(int arr[]){
        for(int i=1; i<arr.length; i++){
            int key = arr[i];
            int idx = i-1;
            while(idx >= 0){
                if(arr[idx]>key){
                    swap(arr, idx, i-1);
                    idx--;
                }
            }
        }
    }

    void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {10,4,5,2,1,5};

        sorting obj = new sorting();
        obj.insertionSort(arr);
        obj.show(arr);
    }
}