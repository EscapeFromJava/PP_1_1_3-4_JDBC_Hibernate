package jm.task.core.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Random;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Byte age;

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return id + " : " + name + " : " + lastName + " : " + age;
    }

    public static User getRandomUser() {
        User newUser = new User();
        newUser.setName(Names.values()[new Random().nextInt(Names.values().length)].name());
        newUser.setLastName(LastNames.values()[new Random().nextInt(LastNames.values().length)].name());
        newUser.setAge((byte) new Random().nextInt(18, 50));
        return newUser;
    }

    enum Names {
        Oleg, Irina, Ivan, Elena, Artem, Sergey, Nikolay, Maria, Danil, Stepan, Evgeniy, Aleksandr
    }

    enum LastNames {
        Stepanenko, Eremenko, Drozd, Poroh, Temechko, Boroda, Stvol, Topor, Ogonek, Mohovik, Smerenko, Tokhno
    }
}

