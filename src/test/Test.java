// EBike class file is missing from corruption

//package test;
//
//import java.util.Arrays;
//
//public class Test {
//
//	public static void main(String[] args) {
//
////		String s = "hello";
////		s = reverse(s);
////		System.out.println(s);
////		int test[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
////		System.out.println(Arrays.toString(test));
////		int testPrime[] = isPrime(test);
////		System.out.println(Arrays.toString(testPrime));
//		
//		EBike b = new EBike(4);
//		
//	}
//
//	public static String reverse(String s) {
//		String rv = "";
//		for (int i = s.length(); i > 0; i--)
//			rv += s.substring(i - 1, i);
//		return rv;
//	}
//
//	public static int[] isPrime(int[] nums) {
//		int[] primes = new int[nums.length]; // Memory allocation will change
//		int primeCount = 0;
//		boolean isPrime = true;
//		for (int i = 0; i < nums.length; i++) { // Iterating through array
//			for (int j = 2; j < nums[i]; j++) { // J is incrementing up to the number
//				if (nums[i] % j == 0) // Checks if j is divisible or if num[i] is not prime
//					isPrime = false;	
//			}
//			if(nums[i] == 0 || nums[i] == 1)
//				continue;
//			if(isPrime) {
//				primes[i] = nums[i]; // is prime
//				primeCount++; // Saving total number of primes for creating array
//				System.out.println("Reached");
//			}
//			isPrime = true;
//		}
//		int[] rv = new int[primeCount]; // new array to be returned
//		int index = 0; // keeps track of spot in primes array
//		for(int i = 0; i < primes.length; i++) {
//			if(primes[i] != 0) {
//				rv[index] = primes[i];
//				index++;
//			}
//		}
//		return rv;
//	}
//
//}
