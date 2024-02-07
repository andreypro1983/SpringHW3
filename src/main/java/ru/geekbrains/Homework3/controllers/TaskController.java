package ru.geekbrains.Homework3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.Homework3.model.User;
import ru.geekbrains.Homework3.services.DataProcessingService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService service;

    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
    }

    //Сортировка пользователей по возрасту
    @GetMapping("/sort")//localhost:8080/tasks/sort
    public List<User> sortUsersByAge() {
        return service.sortUsersByAge(service.getRepository().getUsers());
    }

    //Метод фильтрации по возрасту filterUsersByAge
    @GetMapping("/filter/{age}")//localhost:8080/tasks/filter/{id}
    public List<User> filterUsersByAge(@PathVariable int age) {
        return service.filterUsersByAge(service.getRepository().getUsers(), age);
    }

    //Метод вычисления среднего возраста всех пользователей calculateAverageAge
    @GetMapping("/calc")//localhost:8080/tasks/calc
    public double calculateAverageAge() {
        return service.calculateAverageAge(service.getRepository().getUsers());
    }

}
