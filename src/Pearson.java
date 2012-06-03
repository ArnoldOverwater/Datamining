
public class Pearson {
	
	public static double pearson(UserPreferences user1, UserPreferences user2) {
		int[] items = intersection(user1.getItemIds(), user2.getItemIds());
		double[] ratings1 = new double[items.length], ratings2 = new double[items.length];
		double total1 = 0, total2 = 0;
		for (int i = 0; i < items.length; i++) {
			ratings1[i] = user1.getRatingForItem(items[i]);
			ratings2[i] = user2.getRatingForItem(items[i]);
			total1 += ratings1[i];
			total2 += ratings2[i];
		}
		double average1 = total1 / items.length, average2 = total2 / items.length;
		double numerator = 0, denominator1 = 0, denominator2 = 0;
		for (int i = 0; i < items.length; i++) {
			double calc1 = ratings1[i] - average1, calc2 = ratings2[i] - average2;
			numerator += calc1 * calc2;
			denominator1 += calc1 * calc1;
			denominator2 += calc2 * calc2;
		}
		return numerator / Math.sqrt(denominator1 * denominator2);
	}

	private static int[] intersection(int[] itemIds1, int[] itemIds2) {
		int[] intersection = new int[itemIds1.length + itemIds2.length];
		int index1 = 0, index2 = 0, indexResult = 0;
		while (index1 < itemIds1.length && index2 < itemIds2.length) {
			if (itemIds1[index1] < itemIds2[index2]) {
				index1++;
			} else if (itemIds1[index1] > itemIds2[index2]) {
				index2++;
			} else if (itemIds1[index1] == itemIds2[index2]) {
				intersection[indexResult++] = itemIds1[index1++];
				index2++;
			}
		}
		int[] result = new int[indexResult];
		System.arraycopy(intersection, 0, result, 0, indexResult);
		return result;
	}

}
