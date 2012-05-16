


public class ItemPreferences {
	
	public int itemId;
	public int[] userIds;
	public double[] ratings;
	
	public int arrayPointer;
	
	public ItemPreferences(int itemId){
		this.itemId = itemId;
		userIds = new int[0];
		ratings = new double[0];
	}
	
	public void setElement(int userId, double rating){
		int beginIndex = 0, halfIndex = 0, endIndex = userIds.length;
		if(userIds.length > 0){
			while(true){
				halfIndex = (endIndex - beginIndex) / 2 + beginIndex;
				if(userId < userIds[halfIndex]){
					if(endIndex - beginIndex <= 1){
						break;
					}
					endIndex = halfIndex;	
				}else if(userId > userIds[halfIndex]){
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
		int[] newUserIds = new int[userIds.length + 1];
		double[] newRatings = new double[ratings.length + 1];
		System.arraycopy(userIds, 0, newUserIds, 0, userIds.length);
		newUserIds[halfIndex] = userId;
		System.arraycopy(userIds, halfIndex, newUserIds, halfIndex + 1, userIds.length - halfIndex);
		System.arraycopy(ratings, 0, newRatings, 0, ratings.length);
		newRatings[halfIndex] = rating;
		System.arraycopy(ratings, halfIndex, newRatings, halfIndex + 1, ratings.length - halfIndex);
		userIds = newUserIds;
		ratings = newRatings;
	}
	
	public double getRatingForUser(int userId){
		int beginIndex = 0, halfIndex, endIndex = userIds.length;
		if(userIds.length > 0){
			while(true){
				halfIndex = (endIndex - beginIndex) / 2 + beginIndex;
				if(userId < userIds[halfIndex]){
					if(endIndex - beginIndex <= 1){
						return -1.0;
					}
					endIndex = halfIndex;
				}else if(userId > userIds[halfIndex]){
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
	
	public boolean hasItem(int userId){
		return getRatingForUser(userId) >= 0.0;
	}
	
	public int getItemId(){
		return itemId;
	}
	
	public int[] getUserIds(){
		return userIds;
	}
	
	public double[] getRatings(){
		return ratings;
	}
	
	public String toString(){
		
		StringBuilder builder = new StringBuilder(Integer.toString(itemId));
		
		for(int i = 0; i < userIds.length; i++){
			builder.append("(").append(userIds[i]).append(",").append(ratings[i]).append(")");
		}
		
		return builder.toString(); 
	}
}
