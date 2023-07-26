package Entity;

public class Contact {
    private int id;
    private String name;
    private String gsm;

    public Contact(String name, String gsm){
        this.name = name;
        this.gsm = gsm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }
}
