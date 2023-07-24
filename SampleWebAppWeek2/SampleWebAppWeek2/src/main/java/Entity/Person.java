package Entity;

public class Person {
    private String imgUrl;
    private String name;
    private String anime;
    private int age;

    public Person(String imgUrl, String name, String anime, int age){
        this.imgUrl = imgUrl;
        this.name =name;
        this.anime = anime;
        this.age = age;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
