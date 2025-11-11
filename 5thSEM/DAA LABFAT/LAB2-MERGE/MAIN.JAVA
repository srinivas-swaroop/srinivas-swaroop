// Single File Programming Question
// Problem Statement



// Jack is building a number analyzer tool that identifies numbers based on the sum of their digits. He is given a list of integers and wants to find the smallest number in the list whose digit sum equals a target value d.



// To do this efficiently, Jack first sorts the list based on each number’s digit sum and then applies binary search to find a match. Write a program to ensure that Jack can determine the correct number using this approach. If no number in the list matches the digit sum d, print -1.

// Input format :
// The first line of input consists of an integer n, representing the number of elements in the list.

// The second line contains n space-separated integers, representing the sorted list.

// The third line contains an integer d, representing the target digit sum.

// Output format :
// The output prints a single integer representing the smallest number from the list whose digit sum equals d, based on sorted digit sum order.

// If no such number exists, print -1.



// Refer to the sample output for formatting specifications.

// Code constraints :
// The given test cases fall under the following constraints:

// 2 ≤ n ≤ 20

// 1 ≤ list elements ≤ 100

// Sample test cases :
// Input 1 :
// 5
// 12 23 30 48 56
// 3
// Output 1 :
// 12
// Input 2 :
// 6
// 23 31 45 69 71 96
// 15
// Output 2 :
// 69
// Input 3 :
// 5
// 23 45 67 67 95
// 17
// Output 3 :
// -1



// You are using Java
import java.util.*;

class Main{
    static int sum(int num){
        num = Math.abs(num);
        int sum = 0;
        
        while(num > 0){
            sum+=num%10;
            num = num/10;
        }
        
        return sum;
        
    }
    
    void binarySearch(int target, int left, int right, int arr[]){
        int mid = left+right/2;
        
        while(right > left) {
        if(arr[mid]==target){
            break;
        } else if(arr[mid] > target){
            binarySearch(target, 0, mid - 1, arr);
        } else {
            binarySearch(target, mid+1, arr.length-1, arr);
        }
        }
        
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        int len = sc.nextInt();
        int arrO[] = new int[len];
        int arrS[] = new int[len];
        
        for(int i=0; i<len; i++){
            arrO[i] = sc.nextInt();
        }
        
        Arrays.sort(arrO);
        
        for(int i=0; i<len; i++){
            int num = sum(arrO[i]);
            arrS[i] = num;
        }
        
        int res = sc.nextInt();
        int idx = -1;
        
        for(int i=0; i < len; i++){
            if(arrS[i] == res){
                idx = i;
                break;
            }
        }
        
        if(idx == -1){
            System.out.println(-1);
        }else {
        
        System.out.println(arrO[idx]);
        }
        
        
    }
}



// ---------

// Example

// Input:

// 6

// 789 123 456 876 234 567



// Output:

// The sorted array is: 123 234 456 567 789 876 

// The integer with the highest digit sum is: 789



// Explanation

// Sorted Array Output: 123 234 456 567 789 876

// The digit sum of an integer is the sum of its individual digits.

// In this case, the integer 789 has the highest digit sum (7 + 8 + 9 = 24).

// Input format :
// The first line of input consists of an integer N, representing the number of elements in the array.

// The second line consists of N space-separated integers, representing the array elements.

// Output format :
// The first line prints "The sorted array is: " followed by the sorted array in ascending order.

// The second line prints "The integer with the highest digit sum is: " followed by the integer with the highest-digit sum.



// Refer to the sample output for formatting specifications.

// Code constraints :
// In this scenario, the test cases fall under the following constraints:

// 1 ≤ N ≤ 10

// 1 ≤ Each array element ≤ 1000

// Sample test cases :
// Input 1 :
// 5
// 123 456 789 321 654
// Output 1 :
// The sorted array is: 123 321 456 654 789 
// The integer with the highest digit sum is: 789
// Input 2 :
// 6
// 789 123 456 876 234 567
// Output 2 :
// The sorted array is: 123 234 456 567 789 876 
// The integer with the highest digit sum is: 789


// You are using Java
import java.util.*;

class Main{
    static int calculateDigitSum(int n){
        int sum = 0;
        while(n > 0){
            sum+= (n%10);
            n/=10;
        }
        
        return sum;
    }
    
    static void merge(int arr[], int l, int m, int r){
        int n1 = m-l+1;
        int n2 = r - m;
        
        int L[] = new int[n1];
        int R[] = new int[n2];
        
        for(int i=0; i<n1; i++){
            L[i] = arr[l+i];
        }
        
        for(int j=0; j<n2; j++){
            R[j] = arr[m+1+j];
        }
        
        int i=0;
        int j=0;
        int k = l;
        
        while(i < n1 && j < n2){
            if(L[i] <= R[j]){
                arr[k] = L[i];
                i++;
            } else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        while(i < n1){
            arr[k] = L[i];
            i++;
            k++;
        }
        
        while(j < n2){
            arr[k] = R[j];
            j++;
            k++;
        }
        
        
    }
    
    static void mergeSort(int[] arr, int l, int r){
            if(l < r) {
                int m = (l+r)/2;
                
                mergeSort(arr, l, m);
                mergeSort(arr, m+1, r);
                merge(arr, l, m, r);
            }
        }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        
        int arr[] = new int[len];
        
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }
        
        mergeSort(arr, 0, len-1);
        
        System.out.print("The sorted array is: ");
        
        for(int i=0; i<len; i++){
            System.out.print(arr[i]+" ");
        }
        
        System.out.println();
        
        int maxDigitSum = -1;
        int numWithMax = -1;
        
        for(int num : arr){
            int current = calculateDigitSum(num);
            
            if(current > maxDigitSum){
                maxDigitSum = current;
                numWithMax = num;
            }
        }
        
        System.out.println("The integer with the highest digit sum is: "+ numWithMax);
    }
}