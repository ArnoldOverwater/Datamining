import java.util.Arrays;
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
}
