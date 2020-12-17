package bard.liyang.bardserverproject;

import java.util.Random;

public class RNGesus {
	
	public static RNGesus rng = new RNGesus();
	private Random rand;
	
	public RNGesus()
	{
		rand = new Random(System.currentTimeMillis()); // seeding the random
	}
	
	public float getRandom() // return random float
	{
		return rand.nextFloat();
	}
	
	public int getRandom(int bound) // returns 0 (inclusive) - bound (exclusive)
	{
		return rand.nextInt(bound); 
	}
	
	public void shuffleArray(int[] ar)
	{
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    for (int i = ar.length - 1; i > 0; i--)
	    {
			int index = rand.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
	    }
	}
}
