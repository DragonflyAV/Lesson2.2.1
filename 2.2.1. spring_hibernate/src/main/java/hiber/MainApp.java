package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Andrei", "Avseenko", "andrei@mail.ru");
      Car car1 = new Car("Lada", 189);
      user1.setCar(car1);

      User user2 = new User("Alexander", "Vasiliev", "alexander@mail.ru");
      Car car2 = new Car("Mercedes", 235);
      user2.setCar(car2);

      User user3 = new User("Alex", "Peresynko", "alex@mail.ru");
      Car car3 = new Car("Honda", 348);
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
      }

      userService.getUser("Mercedes", 235);

      context.close();
   }
}
