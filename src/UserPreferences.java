import java.util.Arrays;


public class UserPreferences {
	
	private int userId;	
	private int[] itemIds;
	private double[] ratings;
	
//	public int arrayPointer;
	
	public UserPreferences(int userId){
		this.userId = userId;
		itemIds = new int[0];
		ratings = new double[0];
//		arrayPointer = 0;
	}
	
	public void setElement(int itemId, double rating){
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
		
		StringBuilder builder = new StringBuilder(Integer.toString(userId));
		
		for(int i = 0; i < arrayPointer; i++){
			builder.append("(").append(itemIds[i]).append(",").append(ratings[i]).append(")");
		}
		
		return builder.toString(); 
	}
}
