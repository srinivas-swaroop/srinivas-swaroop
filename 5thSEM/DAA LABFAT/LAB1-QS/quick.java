// Input 1 :
// 4
// 1 2 3 1
// Output 1 :
// The element 1 occurs at the indices 0 and 1
// Input 2 :
// 7
// 7 4 6 4 4 6 1
// Output 2 :
// The element 4 occurs at the indices 1 and 2
// Input 3 :
// 5
// 9 8 7 4 6
// Output 3 :
// All the values are Distinct

// You are using Java

import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int len = sc.nextInt();
        
        int arr[] = new int[len];
        
        for(int i=0; i<len;i++){
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);
        
        boolean found = false;
        
        int first = -1;
        int second = -1;
        int target = -1;
        
        for(int i=0; i<len-1; i++){
            if(arr[i] == arr[i+1]){
                first = i;
                second = i+1;
                target = arr[i];
                found = true;
                break;
            }
        }
        
        if(found){
            System.out.println("The element "+target+" occurs at the indices "+first+" and "+second);
        } else{
            System.out.println("All the values are Distinct");
        }
    }
}

------------

// Input 1 :
// 5
// 80 40 20 50 30
// Output 1 :
// 20 30 40 50 80 
// Input 2 :
// 5
// 19 3 67 54 23
// Output 2 :
// 3 19 23 54 67 


// You are using Java
import java.util.*;

class Main{
    public static void quickSort(int arr[], int low, int high){
        if(low < high){
            int pivotIndex = partition(arr, low, high);
            
            quickSort(arr, low, pivotIndex -1);
            quickSort(arr, pivotIndex+1, high);
        }
    }
    
    public static int partition(int arr[], int low, int high){
        int pivot = arr[high];
        int i = low - 1;
        
        for(int j=low; j<high; j++){
            if(arr[j] < pivot){
                i++;
                
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        
        return i+1;
    }
    
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int arr[] = new int[n];
        
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        
        quickSort(arr, 0 ,n-1);
        
        for(int num : arr){
            System.out.print(num + " ");
        }
    }
}