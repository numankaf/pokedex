package Day4.SerilazeExample2;

import java.io.Serializable;

public class Course implements Serializable {
    String name;

    Course(){

    }
    Course(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
