package hello.controllers;

import com.google.gson.Gson;
import hello.objects.Person;
import hello.objects.Pet;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Beginning to learn a little bit about web services
 * Created by Aaron Fleshner on 3/2/17.
 */
@RestController()
public class PeopleController {


    private ArrayList<Person> people = createPeople();


    @RequestMapping(value = "/people",method = RequestMethod.GET)
    public ArrayList<Person> getPeople(@RequestParam(value = "name", required = false, defaultValue = "") String personsName,
                                       @RequestParam(value = "hasAnimals", required = false, defaultValue = "") String hasAnimal) throws Exception {

        Set<Person> somePeeps = new HashSet<>();
        //nothing is given
        if (personsName.isEmpty() && hasAnimal.isEmpty()) {
            return people;
        }
        //both are give
        if (!personsName.isEmpty() && !hasAnimal.isEmpty()) {
            String regex = ".*\\b" + personsName.toLowerCase() + "\\b.*";
            boolean animals = Boolean.parseBoolean(hasAnimal.toLowerCase());
            for (Person p : people) {
                Pattern pa = Pattern.compile(regex);
                Matcher m = pa.matcher(p.name.toLowerCase());
                if (m.find()) {
                    if (animals) {
                        if (p.pets != null && p.pets.size() != 0) {
                            somePeeps.add(p);
                        }
                    } else {
                        if (p.pets == null || p.pets.size() == 0) {
                            somePeeps.add(p);
                        }
                    }

                }
            }
            return new ArrayList<>(somePeeps);
        }
        //if only name is given
        if (!personsName.isEmpty() && hasAnimal.isEmpty()) {
            String regex = ".*\\b" + personsName.toLowerCase() + "\\b.*";
            for (Person p : people) {
                Pattern pa = Pattern.compile(regex);
                Matcher m = pa.matcher(p.name.toLowerCase());
                if (m.find()) {
                    somePeeps.add(p);
                }
            }
            return new ArrayList<>(somePeeps);
        }
        //only animals are give
        if (personsName.isEmpty() && !hasAnimal.isEmpty()) {
            try {
                boolean animals = Boolean.parseBoolean(hasAnimal.toLowerCase());
                    for (Person p : people) {
                        if (animals) {
                            if (p.pets != null && p.pets.size() != 0) {
                                somePeeps.add(p);
                            }
                        } else {
                            if (p.pets == null || p.pets.size() == 0) {
                                somePeeps.add(p);
                            }
                        }
                    }
            } catch (Exception e) {
                throw new Exception("hasAnimals must be a boolean true or false");
            }
        }
        return new ArrayList<>(somePeeps);
    }


    @RequestMapping(value = "/person/add" ,method = RequestMethod.POST)
    public void addPerson(@RequestBody String json){
        Person p = new Gson().fromJson(json,Person.class);
        if(people==null){
            people = new ArrayList<>();
        }
        people.add(p);
    }

    //Helper function for people
    private ArrayList<Person> createPeople() {
        people = new ArrayList<>();
        ArrayList<Pet> aaronsPets = new ArrayList<>();
        aaronsPets.add(new Pet("Leo", "dog", "1", "https://pbs.twimg.com/media/CqzDcgmWIAENtKZ.jpg"));
        people.add(new Person("Aaron Fleshner", 28, "Android Developer", "https://scontent-atl3-1.xx.fbcdn.net/v/t31.0-8/15625686_10104673950792215_5342352333733494293_o.jpg?oh=f73010f2c5cd9fe9e0d5851c4496baca&oe=5940C2E2", aaronsPets));
        people.add(new Person("Stephanie Fleshner", 29, "Engineer", "https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/1607010_10152308063223093_1211762252163110600_n.jpg?oh=419e5525851e2323278754778a6c4cc8&oe=5970E1BA", aaronsPets));
        people.add(new Person("Nik", 25, "iOS Developer", "https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/14440605_10210019071449623_6966637672293943891_n.jpg?oh=b821c09abbea3a6f6bdf4512854bc756&oe=5935D7AA", null));
        ArrayList<Pet> mattsPets = new ArrayList<>();
        mattsPets.add(new Pet("Charlie", "dog", "unknown", null));
        mattsPets.add(new Pet("Cat 1", "cat", "unknown", null));
        mattsPets.add(new Pet("Cat 2", "cat", "unknown", null));
        people.add(new Person("Matt", 28, "iOS Developer", "https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/10850013_10103022458226555_6499987095450488961_n.jpg?oh=48b35ae7c11d0bdee18bee39c89c14ac&oe=59305294", mattsPets));
        ArrayList<Pet> milesPets = new ArrayList<>();
        milesPets.add(new Pet("Cat 1", "cat", "unknown", null));
        milesPets.add(new Pet("Cat 2", "cat", "unknown", null));
        milesPets.add(new Pet("Cat 3", "cat", "unknown", null));
        milesPets.add(new Pet("Cat 4", "cat", "unknown", null));
        people.add(new Person("Miles", 25, "Web/Backend Developer", "https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/11136726_10206535496372149_1629908853633600818_n.jpg?oh=6d6de0faf974870764d709876b02c09c&oe=593ACD68", milesPets));
        people.add(new Person("Grayson", 21, "Student Developer", "https://scontent-atl3-1.xx.fbcdn.net/v/t31.0-8/12006460_10207784971928850_7921779417938810012_o.jpg?oh=77d03b65e127f8216058b897c3a1bc1d&oe=592A165F", null));
        people.add(new Person("Ryan", 19, "Student Developer", "https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/15697488_1530676503613507_3051232458661846654_n.jpg?oh=dd94cacc9f0a0ec9f6793da4b0dd1cb5&oe=593F5FD7", null));
        people.add(new Person("Anthony, Fleshner",26,"Game Developer",null,null));
        return people;
    }

}
