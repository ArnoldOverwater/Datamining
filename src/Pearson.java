import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


public class Pearson {
	
	public int userId1;
	
	public int userId2;
	
	public int[] itemIds;
	
	public double[] ratings1;
	
	public double[] ratings2;
	
	public double pearson;
	
	public Pearson(UserPreferences user1, UserPreferences user2) {
		intersection(user1, user2);
		if (itemIds.length == user1.getItemIds().length && itemIds.length == user2.getItemIds().length) {
			return;
		}
		pearson = calculatePearson();
	}

	private void intersection(UserPreferences user1, UserPreferences user2) {
		int[] originalItemIds1 = user1.getItemIds(), originalItemIds2 = user2.getItemIds();
		double[] originalRatings1 = user1.getRatings(), originalRatings2 = user2.getRatings();
		int[] intersectItemIds;
		double[] intersectRatings1, intersectRatings2;
		if (originalItemIds1.length < originalItemIds2.length) {
			intersectItemIds = new int[originalItemIds1.length];
			intersectRatings1 = new double[originalItemIds1.length];
			intersectRatings2 = new double[originalItemIds1.length];
		} else {
			intersectItemIds = new int[originalItemIds2.length];
			intersectRatings1 = new double[originalItemIds2.length];
			intersectRatings2 = new double[originalItemIds2.length];
		}
		int index1 = 0, index2 = 0, indexResult = 0;
		while (index1 < originalItemIds1.length && index2 < originalItemIds2.length) {
			if (originalItemIds1[index1] < originalItemIds2[index2]) {
				index1++;
			} else if (originalItemIds1[index1] > originalItemIds2[index2]) {
				index2++;
			} else {
				intersectItemIds[indexResult] = originalItemIds1[index1];
				intersectRatings1[indexResult] = originalRatings1[index1++];
				intersectRatings2[indexResult] = originalRatings2[index2++];
				indexResult++;
			}
		}
		itemIds = new int[indexResult];
		ratings1 = new double[indexResult];
		ratings2 = new double[indexResult];
		System.arraycopy(intersectItemIds, 0, itemIds, 0, indexResult);
		System.arraycopy(intersectRatings1, 0, ratings1, 0, indexResult);
		System.arraycopy(intersectRatings2, 0, ratings2, 0, indexResult);
	}
	
	private double calculatePearson() {
		double total1 = 0, total2 = 0;
		for (int i = 0; i < itemIds.length; i++) {
			total1 += ratings1[i];
			total2 += ratings2[i];
		}
		double average1 = total1 / itemIds.length, average2 = total2 / itemIds.length;
		double numerator = 0, denominator1 = 0, denominator2 = 0;
		for (int i = 0; i < itemIds.length; i++) {
			double calc1 = ratings1[i] - average1, calc2 = ratings2[i] - average2;
			numerator += calc1 * calc2;
			denominator1 += calc1 * calc1;
			denominator2 += calc2 * calc2;
		}
		double denominator = Math.sqrt(denominator1 * denominator2);
		if (denominator == 0.0)
			return 0;
		else
			return numerator / denominator;
	}
	
	private static int[] exclusion(int[] itemIds1, int[] itemIds2) {
		int[] exclusion = new int[itemIds1.length < itemIds2.length ? itemIds2.length : itemIds1.length];
		int index1 = 0, index2 = 0, indexResult = 0;
		while (index1 < itemIds1.length && index2 < itemIds2.length) {
			if (itemIds1[index1] < itemIds2[index2]) {
				index1++;
			} else if (itemIds1[index1] > itemIds2[index2]) {
				exclusion[indexResult++] = itemIds2[index2++];
			} else {
				index1++;
				index2++;
			}
		}
		while (index2 < itemIds2.length) {
			exclusion[indexResult++] = itemIds2[index2++];
		}
		int[] result = new int[indexResult];
		System.arraycopy(exclusion, 0, result, 0, indexResult);
		return result;
	}
	
	public static Pearson[] getAllPearson(UserPreferences[] UP) {
		
	}

}
