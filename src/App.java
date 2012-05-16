import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class App {


	public static void main(String[] args) throws FileNotFoundException {
		
		Map<Integer, UserPreferences> treeMap = new TreeMap<Integer, UserPreferences>();

		readFile(treeMap);
		for(UserPreferences up : treeMap.values()){
			System.out.println(up);
		}
		for(UserPreferences up : treeMap.values()){
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
	
	
	
	public static void readFile(Map<Integer, UserPreferences> map) throws FileNotFoundException{
		File file  = new File("data.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()){
			StringTokenizer st = new StringTokenizer(sc.nextLine(), ",");
			int userId = Integer.parseInt(st.nextToken());
			UserPreferences up = map.get(userId);
			if(up == null){
				up = new UserPreferences(userId);
				map.put(userId, up);
			}
			up.setElement(Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
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
