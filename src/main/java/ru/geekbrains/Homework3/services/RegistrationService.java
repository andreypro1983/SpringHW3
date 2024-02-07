package ru.geekbrains.Homework3.services;


import org.springframework.stereotype.Service;
import ru.geekbrains.Homework3.model.User;

@Service
public class RegistrationService {

    private UserService userService;

    private NotificationService notificationService;

    private DataProcessingService dataProcessingService;

    public RegistrationService(UserService userService, NotificationService notificationService, DataProcessingService dataProcessingService) {
        this.userService = userService;
        this.notificationService = notificationService;
        this.dataProcessingService = dataProcessingService;
    }

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    public void processRegistration(String name, int age, String email) {

        //Создание нового пользователя
        User newUser = userService.createUser(name, age, email);

        //Добавление пользователя в репозиторий

        //Для репозитория List
//        dataProcessingService.addUserToList(newUser);

        //Для репозитория бд
        dataProcessingService.addUserToDb(newUser);

        //Выводим сообщение в консоль через notificationService
        notificationService.sendNotification("User registration " + newUser.getName() + " was successful");

    }
}