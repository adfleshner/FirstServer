package hello.objects;

/**
 * Created by aaronfleshner on 3/2/17.
 */
public class Pet {
    public long id;

    public String name;
    public String type;
    public String age;
    public String imageUrl;

    public Pet() {

    }

    public Pet(long id,String name, String type, String age, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.imageUrl = imageUrl;
    }


}
