import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class App {


	public static void main(String[] args) throws FileNotFoundException {
		UserPreferences[] UP = new UserPreferences[1000];
		ItemPreferences[] IP = new ItemPreferences[1000];
		int upArrayPointer = 0, ipArrayPointer = 0;
		upArrayPointer = readFile(UP, upArrayPointer);
		for(int i = 0; i < upArrayPointer; i++){
			System.out.println(UP[i]);
		}
		ipArrayPointer = convertUPtoIP(UP, upArrayPointer, IP, ipArrayPointer);
		quickSortIP(IP, 0, ipArrayPointer);
		for(int i = 0; i < ipArrayPointer; i++){
			IP[i].sort();
			System.out.println(IP[i]);
		}
	}
	
	
	
	public static int readFile(UserPreferences[] UP, int arrayPointer) throws FileNotFoundException{
		File file  = new File("data.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()){
			StringTokenizer st = new StringTokenizer(sc.nextLine(), ",");
			UserPreferences up = new UserPreferences(Integer.parseInt(st.nextToken()));
			int search = Arrays.binarySearch(UP, 0, arrayPointer, up, new Comparator<UserPreferences>(){
				@Override
				public int compare(UserPreferences arg0, UserPreferences arg1) {
					if(arg0.userId < arg1.userId)
						return -1;
					else if (arg0.userId > arg1.userId)
						return 1;
					else
						return 0;
				}
			});
			if (search < 0){
				UP[arrayPointer++] = up;
			}else{
				up = UP[search];
			}
			up.setElement(Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		return arrayPointer;
	}
	
	
	
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

}
