import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Recommendation {
	
	
	public static void getRecommendation(int userId, Map<Integer, Map<Integer, Pearson>> pearsons) {
		

		List<Pearson> userList = Pearson.getPearsonsFor(userId, pearsons);
		
		Map<Integer, Double> totalPearson = new TreeMap<Integer, Double>();
		Map<Integer, Double> totalWeight = new TreeMap<Integer, Double>();
		
		for(Pearson pearson: userList){
			
			for(int i =0; i < pearson.itemIds.length; i++){
				int itemId = pearson.itemIds[i];
				if (pearson.userId1 == userId) {
					if (totalPearson.containsKey(itemId)) {
						totalPearson.put(itemId, totalPearson.get(itemId) + pearson.ratings2[i]);
						totalWeight.put(itemId, totalWeight.get(itemId) + pearson.weightsUserId2[i]);
					} else {
						totalPearson.put(itemId, pearson.ratings2[i]);
						totalWeight.put(itemId, pearson.weightsUserId2[i]);
					}
				} else {
					if (totalPearson.containsKey(itemId)) {
						totalPearson.put(itemId, totalPearson.get(itemId) + pearson.ratings1[i]);
						totalWeight.put(itemId, totalWeight.get(itemId) + pearson.weightsUserId1[i]);
					} else {
						totalPearson.put(itemId, pearson.ratings1[i]);
						totalWeight.put(itemId, pearson.weightsUserId1[i]);
					}
				}
			}
		}
		
		Map<Integer, Double> totals = new TreeMap<Integer, Double>();
		
		for (Integer itemId : totalPearson.keySet()) {
			double sum = totalWeight.get(itemId) / totalPearson.get(itemId);
			totals.put(itemId, sum);
			System.out.println(itemId+" - "+sum);
		}
		
		/*
		int[] recommendations = new int[3];
		
	
		

		for(Pearson pearson: userList){
			System.out.println(pearson.userId1+" - "+pearson.userId2+ " -> " + pearson.pearson);
			for(int i = 0; i < pearson.itemIds.length; i++){
				System.out.print(pearson.weightsUserId1[i]+",");

			}
		}*/
	}

}
