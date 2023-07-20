package Day4.FunctionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static  <T> void filterList(List<T> items, ListFilter<T> filter){
        for (T t :items){
            if(filter.satisfiedCondition(t)){
                System.out.println(t);
            }
        }
    }
    public static void main(String[] args){
        List<String> strings  = new ArrayList<String>(Arrays.asList("sf", "asfasf", "fs","afasf","gh", "asfas", "asfasf", "gsdfg"));
        List<Integer> ex = new ArrayList<>(Arrays.asList(0,1,0,0,15,21,45));
        ListFilter<String> filter1 = (s)->(s.contains("a"));
        ListFilter<String> filter2 = (s)->(s.length()==2);
        ListFilter<String> filter3 = (s)->(s.startsWith("a"));
        ListFilter<Integer> filter4 = (s)->(s==0);
        ListFilter<Integer> filter5 = (s)->(s>18);
        System.out.println("First Filter");
        filterList(strings,filter1);
        System.out.println("Second Filter");
        filterList(strings,filter2);
        System.out.println("Third Filter");
        filterList(strings,filter3);
        System.out.println("Fourth Filter");
        filterList(ex, filter4);
        System.out.println("Fifth Filter");
        filterList(ex, filter5);

    }
}
