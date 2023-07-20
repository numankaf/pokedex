package Day4.WordOccurenceExample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class Word implements Comparable {
    private String content;
    private int count;

    Word(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        return this.content.equals(((Word) o).content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public int compareTo(Object o) {
        return this.content.compareTo(((Word) o).content);
    }

    @Override
    public String toString() {
        return "Word{" +
                "content='" + content + '\'' +
                ", count=" + count +
                '}';
    }
}


public class Main {
    public static void main(String[] args) {
        String s = "";
        try (FileReader fr = new FileReader("Week1/src/Day4/WordOccurenceExample/string.txt");
             BufferedReader br = new BufferedReader(fr)) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            s = sb.toString();
            s = s.replaceAll("\\p{Punct}", "");

        } catch (Exception e) {
            System.out.println("error");
        }

        Map<Character, Integer> map = new HashMap<>();
        Map<String, Integer> wordsMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (wordsMap.containsKey(arr[i])) {
                wordsMap.put(arr[i], wordsMap.get(arr[i]) + 1);
            } else {
                wordsMap.put(arr[i], 1);
            }
        }


        List<Character> set = new ArrayList<>(map.keySet());
        List<String> wordsSet = new ArrayList<>(wordsMap.keySet());
        Collections.sort(set);
        Collections.sort(wordsSet);

//        for (Character c : set) {
//            System.out.printf("Char is : %s, Count is : %d \n", c, map.get(c));
//        }

//        for (String ss:wordsSet){
//            System.out.printf("Word is : %s, Count is : %d \n", ss,wordsMap.get(ss));
//        }

        SortedSet<Word> newSet = new TreeSet<Word>();

        for (int i = 0; i < arr.length; i++) {
            Word w = new Word(arr[i]);
            boolean check = false;
            for (Word obj : newSet) {
                if (obj.equals(w)) {
                    obj.setCount(obj.getCount() + 1);
                    check  = true;
                }
            }
            if(!check){
                w.setCount(1);
                newSet.add(w);
            }

        }
        for (Word ss : newSet) {
            System.out.println(ss);
        }
    }
}
