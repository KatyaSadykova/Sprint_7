package org.example;

import java.util.Random;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    // Конструктор для логина, пароля и имени
    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // Конструктор для логина и пароля
    public Courier(String login, String password) {
        this(login, password, null); // Используйте конструктор выше
    }

    // Конструктор для логина
    public Courier(String login) {
        this(login, "defaultPassword", null);
    }

    // Пустой конструктор
    public Courier() {
    }

    // Фабричный метод для создания объекта на основе входных данных
    public static Courier from(Courier requestBody) {
        return new Courier(requestBody.getLogin(), requestBody.getPassword(), requestBody.getFirstName());
    }

    // Генерация стандартного курьера
    public static Courier generic() {
        return new Courier("test", "1234");
    }

    // Генерация случайного курьера
    public static Courier random() {
        Random random = new Random();
        return new Courier("test" + random.nextInt(1000), "pass" + random.nextInt(1000));
    }

    // Геттеры и сеттеры
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}