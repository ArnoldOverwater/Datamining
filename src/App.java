import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class App {


	public static void main(String[] args) throws FileNotFoundException {
		UserPreferences[] UP = new UserPreferences[1000];
		int arrayPointer = 0;
		readFile(UP, arrayPointer);
		for(UserPreferences up: UP){
			System.out.println(up);
		}
	}
	
	
	
	public static void readFile(UserPreferences[] UP, int arrayPointer) throws FileNotFoundException{
		File file  = new File("data.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()){
			StringTokenizer st = new StringTokenizer(sc.nextLine(), ",");
			UserPreferences up = new UserPreferences();
			up.userId = Integer.parseInt(st.nextToken());
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
			up.setItem(Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
	}

}
