package com.company;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by st on 16.02.2016.
 */
public class Main {
    public static int MAX = 1000;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String[] names = new String[MAX];
        String[] numbers = new String[MAX];
        boolean[] ids = new boolean[MAX];

        System.out.println("Здравствуйте! Вас приветствует телефонный справочник.");
        System.out.println("Выберите нужный пункт в меню для дальнейших действий: ");
        while (true) {


            System.out.println("1 - Добавить абонента");
            System.out.println("2 - Удалить абонента");
            System.out.println("3 - Обновить");
            System.out.println("4 - Вывести все");
            System.out.println("5 - Поиск");
            System.out.println("6 - Сортировка");
            System.out.println("0 - Выход");
            String menu = in.nextLine();
            if ("0".equals(menu)) {
                break;
            }
            if ("1".equals(menu)) {
                addNew(ids, names, numbers);
            }
            if ("4".equals(menu)) {
                printOut(ids, names, numbers);
            }
            if ("5".equals(menu)) {
                searchInPhoneBook(ids, names, numbers);
            } else {
                System.out.println("Неверный пункт меню");
            }
        }

    }


    public static int findId(boolean[] ids) {
        for (int i = 1; i < ids.length; i++) {
            if (!ids[i]) {
                return i;
            }
        }
        System.out.println("ERROR");
        return -1;
    }

    public static void addNew(boolean[] ids, String[] names, String[] numbers) {
        Scanner in = new Scanner(System.in);
        System.out.println("Добавление...");
        System.out.println("Введите имя:");
        String name = in.nextLine();
        System.out.println("Введите телефон абонента " + name + ":");
        String number = in.nextLine();
        int id = findId(ids);
        names[id] = name;
        numbers[id] = number;
        ids[id] = true;
    }

    public static void printOut(boolean[] ids, String[] names, String[] numbers) {
        System.out.println("Список всех:");
        System.out.println("_______________________________");
        System.out.println("|" + "ID" + "|" + "   NAME   " + "|" + "   NUMBER   " + "|");
        System.out.println("-------------------------------");
        for (int i = 1; i < ids.length; i++) {
            if (ids[i]) {
                System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|");
                System.out.println("------------------------------------");
            }
        }
    }

    public static void searchInPhoneBook(boolean[] ids, String[] names, String[] numbers) {
        System.out.println("Выберите метод поиска: ");
        System.out.println("1 - по ID");
        System.out.println("2 - по имени");
        System.out.println("3 - по номеру телефона");
        Scanner in = new Scanner(System.in);
        String search = in.nextLine();
        if ("1".equals(search)) {
            System.out.println("Введите ID абонента:");
            int searchID = in.nextInt();
            for (int i = 1; i < ids.length; i++) {
                if (searchID == i) {
                    System.out.println("__________________________________");
                    System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|");
                    System.out.println("----------------------------------");
                }
            }
        }
        if ("2".equals(search)) {
            boolean ok = false;
            System.out.println("Введите имя абонента: ");
            String searchName = in.nextLine();
            for (int i = 1; i < ids.length; i++) {
                if (searchName != null && names[i] != null && searchName.equals(names[i])) {
                    ok = true;
                    if (ok) {
                        System.out.println("__________________________________");
                        System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|");
                        System.out.println("----------------------------------");
                    } else {
                        System.out.println("Запись с таким именем не найдена");

                    }
                }
            }
        }
        if ("3".equals(search)) {
            boolean ok = false;
            System.out.println("Введите номер телефона абонента: ");
            String searchNumber = in.nextLine();
            for (int i = 1; i < ids.length; i++) {
                if (searchNumber != null && numbers[i] != null && searchNumber.equals(numbers[i])) {
                    ok = true;
                    if (ok) {
                        System.out.println("__________________________________");
                        System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|");
                        System.out.println("----------------------------------");
                    } else {
                        System.out.println("Запись с таким номером не найдена");
                    }
                }
            }
        }
    }
}


