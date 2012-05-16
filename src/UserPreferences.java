


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
		int beginIndex = 0, halfIndex = 0, endIndex = itemIds.length;
		if(itemIds.length > 0) {
			while(true){
				halfIndex = (endIndex - beginIndex) / 2 + beginIndex;
				if(itemId < itemIds[halfIndex]){
					if(endIndex - beginIndex <= 1){
						break;
					}
					endIndex = halfIndex;
				}else if(itemId > itemIds[halfIndex]){
					if(endIndex - beginIndex <= 1){
						halfIndex++;
						break;
					}
					beginIndex = halfIndex;
				}else{
					ratings[halfIndex] = rating;
					return;
				}
			}
		}
		int[] newItemIds = new int[itemIds.length + 1];
		double[] newRatings = new double[ratings.length + 1];
		System.arraycopy(itemIds, 0, newItemIds, 0, halfIndex);
		newItemIds[halfIndex] = itemId;
		System.arraycopy(itemIds, halfIndex, newItemIds, halfIndex + 1, itemIds.length - halfIndex);
		System.arraycopy(ratings, 0, newRatings, 0, halfIndex);
		newRatings[halfIndex] = rating;
		System.arraycopy(ratings, halfIndex, newRatings, halfIndex + 1, ratings.length - halfIndex);
		itemIds = newItemIds;
		ratings = newRatings;
	}
	
	public double getRatingForItem(int itemId) {
		int beginIndex = 0, halfIndex, endIndex = itemIds.length;
		if(itemIds.length > 0) {
			while(true){
				halfIndex = (endIndex - beginIndex) / 2 + beginIndex;
				if(itemId < itemIds[halfIndex]){
					if(endIndex - beginIndex <= 1){
						return -1.0;
					}
					endIndex = halfIndex;
				}else if(itemId > itemIds[halfIndex]){
					if(endIndex - beginIndex <= 1){
						return -1.0;
					}
					beginIndex = halfIndex;
				}else{
					return ratings[halfIndex];
				}
			}
		}else{
			return -1.0;
		}
	}
	
	public boolean hasItem(int itemId) {
		return getRatingForItem(itemId) >= 0.0;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public int[] getItemIds(){
		return itemIds;
	}
	
	public double[] getRatings(){
		return ratings;
	}
	
	public String toString(){
		
		StringBuilder builder = new StringBuilder(Integer.toString(userId));
		
		for(int i = 0; i < itemIds.length; i++){
			builder.append("(").append(itemIds[i]).append(",").append(ratings[i]).append(")");
		}
		
		return builder.toString(); 
	}
}
