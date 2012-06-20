import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class App {


	public static void main(String[] args) throws FileNotFoundException {
		
		Map<Integer, UserPreferences> treeMapUP = new TreeMap<Integer, UserPreferences>();
		Map<Integer, ItemPreferences> treeMapIP = new TreeMap<Integer, ItemPreferences>(); 

		readFile(treeMapUP, treeMapIP);
		System.out.println("Op basis van users\n");
		
		UserPreferences[] UP = new UserPreferences[treeMapUP.size()];  
		
		int index = 0;
		for(UserPreferences up : treeMapUP.values()){
			UP[index++] = up;
		}
		
		/*
		System.out.println("\nOp basis van items\n");
		for(ItemPreferences ip : treeMapIP.values()){
			System.out.println(ip);
		}
		*/
		
		Map<Integer, Map<Integer, Pearson>> pearsons = Pearson.getAllPearson(UP, 0.60);

		
		Recommendation.getRecommendation(1, pearsons);
		
		/*
		System.out.println("\nRecommendations voor user 1\n");
		System.out.println(Pearson.getRecommendations(treeMapUP.get(1), treeMapUP));
		
		/*
		for(UserPreferences up : treeMapUP.values()){
			testUserHasItem(up, 101);
			testUserHasItem(up, 103);
			testUserHasItem(up, 106);
		}
		/*
		ipArrayPointer = convertUPtoIP(UP, upArrayPointer, IP, ipArrayPointer);
		quickSortIP(IP, 0, ipArrayPointer);
		for(int i = 0; i < ipArrayPointer; i++){
			IP[i].sort();
			System.out.println(IP[i]);
		}
		*/
	}
	
	
	
	public static void readFile(Map<Integer, UserPreferences> mapUP ,Map<Integer, ItemPreferences> mapIT ) throws FileNotFoundException{
		File file  = new File("u.data");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()){
			StringTokenizer st = new StringTokenizer(sc.nextLine(), "\t");
			int userId = Integer.parseInt(st.nextToken());
			int itemId = Integer.parseInt(st.nextToken());
			double rating = Double.parseDouble(st.nextToken());
			UserPreferences up = mapUP.get(userId);
			ItemPreferences it = mapIT.get(itemId);
			if(up == null){
				up = new UserPreferences(userId);
				mapUP.put(userId, up);
			}if(it == null){
				it = new ItemPreferences(itemId);
				mapIT.put(itemId, it);
			}
			up.setElement(itemId, rating);
			it.setElement(userId, rating);
		}
	}
	
	
	public static void testUserHasItem(UserPreferences up, int itemId) {
		System.out.printf("Has user %d item %d: %b\n", up.getUserId(), itemId, up.hasItem(itemId));
	}
	
	/*
	public static int convertUPtoIP(UserPreferences[] UP, int upArrayPointer, ItemPreferences[] IP, int ipArrayPointer){
		for(int i = 0; i < upArrayPointer; i++){
			UserPreferences up = UP[i];
			for(int j = 0; j < up.arrayPointer; j++){
				ItemPreferences ip = new ItemPreferences(up.itemIds[j]);
				int search = Arrays.binarySearch(IP, 0, ipArrayPointer, ip, new Comparator<ItemPreferences>(){
					@Override
					public int compare(ItemPreferences arg0, ItemPreferences arg1) {
						if(arg0.itemId < arg1.itemId)
							return -1;
						else if (arg0.itemId > arg1.itemId)
							return 1;
						else
							return 0;
					}
				});
				if (search < 0){
					IP[ipArrayPointer++] = ip;
				}else{
					ip = IP[search];
				}
				ip.setElement(up.userId, up.ratings[j]);
			}
		}
		return ipArrayPointer;
	}
	
	
	
	public static void quickSortIP(ItemPreferences[] IP, int startIndex, int endIndex){
		int size = endIndex - startIndex;
		if(size <= 1){
			return;
		}
		ItemPreferences[] smaller = new ItemPreferences[size], larger = new ItemPreferences[size];
		ItemPreferences pivot = IP[endIndex - 1];
		int smallerPointer = 0, largerPointer = 0;
		for(int i = startIndex; i < endIndex; i++){
			if(IP[i].itemId < pivot.itemId){
				smaller[smallerPointer++] = IP[i];
			}else{
				larger[largerPointer++] = IP[i];
			}
		}
		int arrayPointer = startIndex;
		for(int i = 0; i < smallerPointer; i++){
			IP[arrayPointer++] = smaller[i];
		}
		quickSortIP(IP, startIndex, startIndex + smallerPointer);
		IP[arrayPointer++] = pivot;
		for(int i = 0; i < largerPointer; i++){
			IP[arrayPointer++] = larger[i];
		}
		quickSortIP(IP, endIndex - largerPointer, endIndex);
	}
*/
}
