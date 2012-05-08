import java.util.Arrays;


public class ItemPreferences {
	
	public int itemId;
	public int[] userIds;
	public double[] ratings;
	
	public int arrayPointer;
	
	public ItemPreferences(int itemId){
		this.itemId = itemId;
		userIds = new int[1000];
		ratings = new double[1000];
		arrayPointer = 0;
	}
	
	public void setElement(int userId, double rating){
		int search = Arrays.binarySearch(userIds, itemId);
		if(search < 0){
			userIds[arrayPointer] = userId;
			ratings[arrayPointer] = rating;
			arrayPointer++;
		}else{
			ratings[search] = rating;		
		}
	}
	
	public String toString(){
		
		StringBuilder builder = new StringBuilder(Integer.toString(itemId));
		
		for(int i = 0; i < arrayPointer; i++){
			builder.append("(").append(userIds[i]).append(",").append(ratings[i]).append(")");
		}
		
		return builder.toString(); 
	}
}
