public class merge {
    static void split(int arr[], int left, int right){
        int mid = left+(right-left)/2;

        if(left <= right){
            split(arr, left, mid);
            split(arr, mid+1, right);

            merge(arr, mid, left, right);
        }
    }

    static void merge(int arr[], int mid, int left, int right){
        //{2,12,3,45,12,4,7}
        //left = 0;
        //right = 6;
        //mid = 3;

        int n1 = mid - left;
        int n2 = right-mid+1;
        int leftArr[] = new int[n1];
        int rightArr[] = new int[n2];

        for(int i=0; i<n1; i++){
            leftArr[i] = arr[i];
        }

        for(int i=n2; i<=right; i++){
            rightArr[i] = arr[i];
        }

        int i=0;
        int j=0;
        int k=0;

        while(i < n1 && j < n2){
            if(leftArr[i] > rightArr[j]){
                arr[k] = rightArr[j];
                j++;
            }else{
                arr[k] = leftArr[i];
                i++;
            }

            k++;
        }

        while(i < n1){
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while(j < n2){
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        
    }
}
