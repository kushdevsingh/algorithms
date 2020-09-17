package com.luhach.algorithms.solutions;

import org.jline.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SearchAndSort {

    private static final Logger LOG = LoggerFactory.getLogger(SearchAndSort.class);
    private int [] quickSortInput;
    
    public Number binarySearch(int arr[],int find ){
        int found;
        int low=0;
        int high=arr.length;
        int mid;
        while(low<=high){
            mid=(high+low)/2;
            LOG.info("MId:{}",mid);
            found=arr[mid];
            if (found==find){return mid;}
            if (found<find){low=mid+1;}else{
                high=mid-1;
            }                
        }
        return null;
    }
    
    public int[] quickSort(int arr[],int low,int high){
//        this.quickSortInput=arr;
//        LOG.info("Recursion Call:{}",start);
        if(arr.length<2){
            return arr;
        }
        if(low < high) {
            int partitionIndex = partitionStart(arr, low, high);
//            LOG.info("Array:{}", Arrays.toString(arr));

            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
        return arr;
    }
//qs 8 10 9 15 5 45 12 3 7 1
    public int partition(int arr[], int low,int high){
//        LOG.info("Start:{} End:{}",low,high);
        int pivot=arr[high];
        int i=(low-1);
        for(int j=low;j<high;j++){
            if(arr[j]<=pivot){
                i++;
                int swap=arr[i];
                arr[i]=arr[j];
                arr[j]=swap;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }


    public int partitionStart(int arr[], int start,int end){
//        LOG.info("Start:{} End:{}",start,end);
        int pivot=arr[start];
        int left=(start+1);
        int right=end;
        int temp;
        while(left <=right){
            while(arr[left]<pivot && left <end){left++;}
            while(arr[right]>pivot){right--;}
            if(left<=right){
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right --;
            }
        }
        arr[start] = arr[right];
        arr[right] = pivot;
        return right;
    }
}
