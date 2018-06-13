package searching;

import java.util.Scanner;

/**
 * 
 * @author inyamar
 * Searching algorithms : Linear, Binary & Jump Search has been identified. 
 */

public class Searching {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		//int[] input = {2, 16, 49, 62, 71, 74, 86, 100, 110, 111, 116, 127, 128, 129, 138, 140, 148, 161, 167, 174, 177, 179, 180, 184, 215, 220, 229, 230, 236, 241, 252, 270, 303, 314, 322, 328, 331};
		int[] input = {5,4,3,2,1};
		int option;
		//		System.out.println("Insert integer key to search");
		//		int key = sc.nextInt();

		while(true){

			System.out.println("Insert integer key to search");
			int key = sc.nextInt();
			System.out.println("Please select options below: ");
			System.out.println();
			System.out.println("********\t1. LinearSearch \t 2. Binary Search \t 3. Jump Search \t 4. Interpolation Search *********");
			option = sc.nextInt();
			switch (option){
			case 1: 
					if(linearSearch(input,key)==0)
						System.out.println("Key doesn't exists!!");
					break;
			case 2: 
					sortInIncreasingOrderUsingBubbleSort(input); 
					if(binarySearch(input,0, input.length-1,key)==1)
						System.out.println("Value found!!");
					else System.out.println("Sorry!!");
					break;
			case 3: 
					if(jumpSearch(input,key)==0)
						System.out.println("Key not found");
					break;
			case 4: 
					sortInIncreasingOrderUsingBubbleSort(input);
					if(interpolationSearch(input, key)==0)
						System.out.println("Sorry!! Coud not found the key");
					else System.out.println("Value Found!!");
					break;
			default: 
					System.out.println("Enter correct option");
					break;
			}
		}

	}
	
	public static int linearSearch(int[] input, int key){
		for(int i : input){
			if(i==key){
				System.out.println("Value Exists here!!");
				return 1;
			}
		}
		return 0;
		
	}

	/**
	 * Sorting an array in Increasing order using Bubble sort
	 */
	public static int binarySearch(int[] input,int start, int last, int key){
		if(last>=start){
			int  mid=(start+last)/2;
			if( key == input[mid])
				return 1;
			else if(key > input[mid])
				return binarySearch(input, mid+1, last, key);
			else if(key< input[mid])
				return binarySearch(input, start, mid-1, key);
		}
		return 0;
	}
	
	//Sorting an array
	private static void sortInIncreasingOrderUsingBubbleSort(int[] input) {
		for(int i =0;i<input.length;i++){
			for(int j=i;j<input.length;j++){
				if(input[i]>input[j]){
					int temp=input[i];
					input[i]=input[j];
					input[j]=temp;
				}
			}
		}
		
	}


	/**
	 * Jump Search uses block size to find the range in which element can be present. 
	 * Pre-requisite : Array needs to be sorted.
	 */
	public static int jumpSearch(int[] input, int key){
 
		sortInIncreasingOrderUsingBubbleSort(input);
		int blockSize= (int) Math.floor(Math.sqrt(input.length));
		int m=1;
		while(m*blockSize <=input.length && key>=input[m*blockSize] ){
			if(key==input[m*blockSize]){
				System.out.println("value exists");
				return 1;
			}	
			m++;
		}
		for(int i=(m-1)*blockSize; i< ( m*blockSize > input.length ? input.length : m*blockSize); i++)
			if(input[i]==key){
				System.out.println("value exists");
				return 1;
			}
		return 0;
	}
	
	/***
	 * Interpolation Search is modified binary search. 
	 * Used in evenly distributed list.
	 * Formula to calculate index: pos = lo + ( ( x - arr[lo] ) * ( hi-lo ) / ( arr[hi]-arr[lo] ) )
	 * Best Case O(log log N)  
	 * Worst Case O(N)
	 * 
	 */
	public static int interpolationSearch(int[] input, int key){
		int start = 0, last = input.length-1;
		int index=0;
		while(last>=start && key<=input[last] && key >=input[start]){
			index = start + (int)( (key-input[start]) * (last-start) / (input[last] - input[start]) );
			
			if(input[index]==key)
				return 1;
			else if(key>input[index])
				start = start+1;
			else if(key<input[index])
				last = last-1;
		}
		return 0;
	}

}
