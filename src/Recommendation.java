import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		
		ValueComparotar valueComparator = new ValueComparotar();
		Map<Integer, Double> totals = new TreeMap<Integer, Double>(valueComparator);
		valueComparator.map = totals;
		
		for (Integer itemId : totalPearson.keySet()) {
			double sum = totalWeight.get(itemId) / totalPearson.get(itemId);
			totals.put(itemId, sum);
		}
		
		
		int counter = 0;
		for(Integer entry: totals.keySet()){
			
			System.out.println(entry +" - " + totals.get(entry));
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
	
	static class ValueComparotar implements Comparator<Integer>{

		Map<Integer, Double> map;
		
		@Override
		public int compare(Integer index1, Integer index2) {
			// TODO Auto-generated method stub
			if(map.get(index1) < map.get(index2)){
				return 1;
			}else if(map.get(index1) == map.get(index2)){
				return 0;
			}else{
				return -1;
			}
		}
		
	}

}
