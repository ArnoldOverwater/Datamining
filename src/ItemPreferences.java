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
	
	public void sort(){
		quickSort(0, arrayPointer);
	}
	
	private void quickSort(int startIndex, int endIndex){
		int size = endIndex - startIndex;
		if (size <= 1){
			return;
		}
		int[] smallerUserIds = new int[size], largerUserIds = new int[size];
		double[] smallerRatings = new double[size], largerRatings = new double[size];
		int pivotUserId = userIds[endIndex - 1];
		double pivotRating = ratings[endIndex - 1];
		int smallerPointer = 0, largerPointer = 0;
		for(int i = startIndex; i < endIndex - 1; i++){
			if(userIds[i] < pivotUserId){
				smallerUserIds[smallerPointer] = userIds[i];
				smallerRatings[smallerPointer] = ratings[i];
				smallerPointer++;
			}else if(userIds[i] > pivotUserId){
				largerUserIds[largerPointer] = userIds[i];
				largerRatings[largerPointer] = ratings[i];
				largerPointer++;
			}else if(ratings[i] < pivotRating){
				smallerUserIds[smallerPointer] = userIds[i];
				smallerRatings[smallerPointer] = ratings[i];
				smallerPointer++;
			}else{
				largerUserIds[largerPointer] = userIds[i];
				largerRatings[largerPointer] = ratings[i];
				largerPointer++;
			}
		}
		int newArrayPointer = startIndex;
		for(int i = 0; i < smallerPointer; i++){
			userIds[newArrayPointer] = smallerUserIds[i];
			ratings[newArrayPointer] = smallerRatings[i];
			newArrayPointer++;
		}
		quickSort(startIndex, startIndex + smallerPointer);
		userIds[newArrayPointer] = pivotUserId;
		ratings[newArrayPointer] = pivotRating;
		newArrayPointer++;
		for(int i = 0; i < largerPointer; i++){
			userIds[newArrayPointer] = largerUserIds[i];
			ratings[newArrayPointer] = largerRatings[i];
			newArrayPointer++;
		}
		quickSort(endIndex - largerPointer, endIndex);
	}
	
	public String toString(){
		
		StringBuilder builder = new StringBuilder(Integer.toString(itemId));
		
		for(int i = 0; i < arrayPointer; i++){
			builder.append("(").append(userIds[i]).append(",").append(ratings[i]).append(")");
		}
		
		return builder.toString(); 
	}
}
