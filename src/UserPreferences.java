import java.util.Arrays;


public class UserPreferences {
	
	public int userId;	
	public int[] itemIds;
	public double[] ratings;
	
	public int arrayPointer;
	
	public UserPreferences(){
		itemIds = new int[1000];
		ratings = new double[1000];
		arrayPointer = 0;
	}
	
	public void setItem(int itemId, double rating){
		int search = Arrays.binarySearch(itemIds, itemId);
		if(search < 0){
			itemIds[arrayPointer] = itemId;
			ratings[arrayPointer] = rating;
			arrayPointer++;
		}else{
			ratings[search] = rating;		
		}
	}
	
	public String toString(){
		
		String list = ""+userId;
		
		for(int i = 0; i< arrayPointer;i++){
			list += "("+itemIds[i]+","+ratings[i]+")";
		}
		
		return list; 
	}
}
