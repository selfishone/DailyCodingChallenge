/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */

//Required time complexity is O(log(m+n))
/**
    1. Divide the array into 2 parts 
    2. The median will be the centermost number 
    3. Problem will be how to divide array into two equal parts with 
        a. Numbers on left are <= Numbers on Right 
        b. Rightmost number on left secction is greatest among them and lest than any right no
    4. Use binary search on the smallest of two arrays to partition it into 2 parts (left/right)
*/
var findMedianSortedArrays = function(nums1, nums2) {
    
    let m = nums1.length;
    let n = nums2.length;
    
    if(m>n){
        return findMedianSortedArrays(nums2, nums1);
    }
    
    let left_half_size = Math.floor((m+n+1)/2);
    
    //Number of elements in the left partition for nums1

    let left = 0;
    let right = m; 
    
    while( left <= right){
        
        let partition_size_nums1 = Math.floor((left+right)/2);
        let partition_size_nums2 = left_half_size - partition_size_nums1;
        
        let left_max_nums1 = (partition_size_nums1===0)?Number.NEGATIVE_INFINITY:nums1[partition_size_nums1-1];
        let right_min_nums1 = (partition_size_nums1===m)?Number.POSITIVE_INFINITY:nums1[partition_size_nums1];
        let left_max_nums2 = (partition_size_nums2===0)?Number.NEGATIVE_INFINITY:nums2[partition_size_nums2-1];
        let right_min_nums2 = (partition_size_nums2===n)?Number.POSITIVE_INFINITY:nums2[partition_size_nums2];
        
        if(left_max_nums1 <= right_min_nums2 &&  right_min_nums1 >=left_max_nums2){
            
            let left_max = Math.max(left_max_nums1,left_max_nums2);
            let right_min =  Math.min(right_min_nums1,right_min_nums2);
            
            let result = ((m+n)%2==1)?left_max:(left_max+right_min)/2;
            return result;
        }else if(left_max_nums1 >right_min_nums2){
            right--;
        }else{
            left++;
        }
    }
}
    

//Time complexity for this method is O(m+n)
var findMedianSortedArrays2 = function(nums1, nums2) {
  
    let combined_array = [];
    
    while(nums2.length>0 && nums1.length > 0){
        
        if(nums1[0]==nums2[0]){
            combined_array.push(nums1.shift());
            combined_array.push(nums2.shift());
        }else if(nums1[0]>nums2[0]){
            combined_array.push(nums2.shift());
        }else{
             combined_array.push(nums1.shift());
        }
    }
    
    let median = function(array){
        let size = array.length;
        if(size%2 == 1){
            return array[Math.floor(size/2)];
        }else{
            return (array[Math.floor(size/2)-1]+ array[Math.floor(size/2)])/2
        }
    }
    
    console.log(combined_array);
    
    if(nums1.length == 0 && nums2.length ==0){
        return median(combined_array);
    }else if(nums1.length > 0){
        return median(combined_array.concat(nums1));
    }else if(nums2.length > 0){
        return median(combined_array.concat(nums2));
    }
   
};
