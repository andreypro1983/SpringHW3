package ru.geekbrains.Homework3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Homework3.model.User;
import ru.geekbrains.Homework3.services.RegistrationService;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private RegistrationService service;

    //Получение списка пользователей из бд
    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getRepository().getUsers();
    }

    //Создание пользователя через отправку в теле запроса
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        service.getDataProcessingService().addUserToDb(user);
        return "User added from body!";
    }

    //Создание пользователя через параметры
    //Пример строки http://localhost:8080/users/param?name=Andrey&age=15&email=ya@ya.ru
    @PostMapping("/param")
    public String userAddFromParam(@RequestParam(value = "name", defaultValue = "John Doe") String name,
                                   @RequestParam(value = "age", defaultValue = "18") int age,
                                   @RequestParam(value = "email", defaultValue = "doe@yandex.ru") String email) {
        service.processRegistration(name, age, email);
        return "User added by param!";
    }

}