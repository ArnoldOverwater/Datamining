import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Recommendation {
	
	public static int[] users;
	public static int[] items;
	
	
	
	public static void getRecommendation(Map<Integer, UserPreferences> userList){
		
		int userPairs = getPermutaties(userList.size(), 2);
		
		users = new int[userPairs];
		items = new int[userPairs];
		
		for(int x = 1; x <= 1/*userPairs;*/; x++){
			for(int y = x+1; y <= userPairs; y++){
				UserPreferences userX = userList.get(x);
				UserPreferences userY = userList.get(y);
				System.out.println(getPearson(userX.getItemIds(), userX.getRatings(), userY.getItemIds(), userY.getRatings()));
			}
		}


	}
	
	
	public static int getPermutaties(int n, int k){
		return fac(n)/(fac(k)*fac(n-k));
	}
	
	public static int fac(int size){
		
		if(size == 0) return 1;
		else return size * fac(size-1);
	}
	
	public static double getPearson(int[] itemsx, double[] ratingsX, int[] itemsy, double[] ratingsY){
		//binnenkomst
		//int[] itemsx = {101,102,103,104,105,106};
		//int[] itemsy = {101,102,104,106};
		//double[] userX = {2.5, 3.5, 3, 3.5, 2.5, 3};
		//double[] userY = {2.5, 3, 3.5, 4};
		
		double sumRatingX = 0.0, xAverage = 0.0;
		double sumRatingY = 0.0, yAverage = 0.0;
		
		//gefilterd
		int[] items;
		double[] x;
		double[] y;
		
		
		int indexN = 0;
		
		if(itemsx.length < itemsy.length){
			items = new int[itemsx.length];
			x = new double[itemsx.length];
			y = new double[itemsx.length];
			for(int i = 0; i < itemsx.length; i++){
				int index = Arrays.binarySearch(itemsy, itemsx[i]);
				if(index > -1){
					items[indexN] = itemsy[index];
					sumRatingY += y[indexN] = ratingsY[index];
					sumRatingX += x[i] = ratingsX[i];
					indexN++;
				}
			}
		}else{
			items = new int[itemsy.length];
			x = new double[itemsy.length];
			y = new double[itemsy.length];
			for(int i = 0; i < itemsy.length; i++){
				int index = Arrays.binarySearch(itemsx, itemsy[i]);
				if(index > -1){
					items[indexN] = itemsx[index];
					sumRatingX += x[indexN] = ratingsX[index];
					sumRatingY += y[i] = ratingsY[i];
					indexN++;
					}
				}
			}
		
		xAverage = sumRatingX/indexN;
		yAverage = sumRatingY/indexN;
		
		for(int i = 0; i < items.length; i++){
			if(items[i] != 0){
				System.out.println("item = "+items[i]+" rating = "+x[i]);
				System.out.println("item = "+items[i]+" rating = "+y[i]);	
			}
		}
		
		
		double teller = 0.0;
		double subNoemer1 = 0.0;
		double subNoemer2 = 0.0;
		double noemer = 0.0;
		
		for(int i = 0; i < items.length; i++){
			
			teller += (x[i] - xAverage) * (y[i]-yAverage);
			subNoemer1 += Math.pow((x[i] - xAverage), 2);
			subNoemer2 += Math.pow((y[i] - yAverage), 2);
		}
		
		noemer = Math.sqrt(subNoemer1*subNoemer2);
		
		return (teller/noemer);
	}
}
