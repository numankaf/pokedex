package Day4.MapExample;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Map<Integer , String> map = new HashMap<>();
        map.put(1,"David");
        map.put(2,"Lucy");
        map.put(3, "Doc");

        Set<Integer> set = map.keySet();
        for(Iterator<Integer> iterator = set.iterator(); iterator.hasNext();){
            int key = iterator.next();
            String value = map.get(key);
            System.out.println(value);
        }
        for (String s: map.values()) {
            System.out.println(s);
        }
    }
}
