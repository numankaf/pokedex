package Day3.SerilazeExample;

import java.io.Serializable;

public class AnimeCharacter implements Serializable {
    transient private String name;
    private String anime;
    private int age;

    AnimeCharacter() {

    }

    AnimeCharacter(String name, String anime, int age) {
        this.name = name;
        this.anime = anime;
        this.age = age;
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

    @Override
    public String toString() {
        return "AnimeCharacter{" +
                "name='" + name + '\'' +
                ", anime='" + anime + '\'' +
                ", age=" + age +
                '}';
    }
}
