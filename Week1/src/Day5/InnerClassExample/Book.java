package Day5.InnerClassExample;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private Publisher publisher;
    private List<Chapter> chapterList = new ArrayList<>();

    class Chapter{
        private String title;
        private int chapterNumber;

        @Override
        public String toString() {
            int totalChapters= chapterList.size();
            return "title='" + title + '\'' +
                    ", chapterNumber=" + chapterNumber +" in "+ totalChapters;
        }
    }
    static class Publisher{
        String name;
        String country;
    }
}
