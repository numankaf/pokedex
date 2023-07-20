package Day4.SortExample;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String[] names = {"Itadori","Nobara","Megumi" ,"GOjo","Sukuna"};
        System.out.println(names);
        Arrays.sort(names);
        System.out.println(names);
        for(String name: names){
            System.out.println(name);
        }

        List<String> arList = Arrays.asList(names);
        for (int i = 0; i < arList.size(); i++) {
            System.out.println(arList.get(i));
        }
    }
}
