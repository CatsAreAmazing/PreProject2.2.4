package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

//В db.properties изменены логин и пароль

public class MainApp {
    //2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
    //Судя по заданию, предполагается, что у конкретного пользователя будет ссылка только на конкретную машину,
    //но это странно, обычно же наоборот...
    //Хотя с учетом требования использовать именно 1-to-1 логично, но всё равно странно

    //В общем, сделал максимально по заданию, но уверен, что это можно сделать в разы лучше и адекватнее
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//можно конечно отдельно делать машины, добавлять их в юзеров через setCar, но это много лишних строк, так что оставим так

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("1", 1)));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("2", 2)));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("3", 3)));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("4", 4)));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
//toString переопределил
        System.out.println(userService.getUserByCar("2", 2));

        context.close();
    }
}
