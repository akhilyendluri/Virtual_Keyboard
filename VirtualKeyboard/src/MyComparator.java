import java.util.Comparator;
import java.util.HashMap;

public class MyComparator implements Comparator<String> {

	HashMap<String, Integer> map = new HashMap<>();
	Dictionary d;
	
	public MyComparator(HashMap<String, Integer>map) {
		this.map.putAll(map);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compare(String o1, String o2) {		
		// TODO Auto-generated method stub
		System.out.println("o1 = "+o1+" o2 = "+o2);
		if(d.loadDictionary.get(o1) < d.loadDictionary.get(o2)) {
			return 1;
		}
		return 0;
	}
	
	

}
