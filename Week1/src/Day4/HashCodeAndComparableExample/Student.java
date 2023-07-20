package Day4.HashCodeAndComparableExample;

public class Student implements Comparable{
    int id;
    Student(int id){
        this.id = id;
    }


    @Override
    public int compareTo(Object o) {
        if(this.id ==((Student) o).id){
            return 0;
        }
        else if(this.id >((Student) o).id){
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(this.id ==((Student) o).id){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String
    toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}
