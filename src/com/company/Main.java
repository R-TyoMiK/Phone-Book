package com.company;

import java.io.*;
import java.util.Scanner;

/**
 * Created by st on 16.02.2016.
 */
public class Main {
    public static int MAX = 1000;
    public static String NAMES = "names.rt";
    public static String NUMBERS = "numbers.rt";
    public static String POSITIONS = "positions.rt";
    public static String YEARS = "years.rt";
    public static String MONTHS = "months.rt";
    public static String DAYS = "days.rt";

    public static void main(String[] args) throws IOException, NullPointerException, ClassNotFoundException {
        String[] names = readStringFromFile(NAMES);
        Scanner in = new Scanner(System.in);
        String[] numbers = readStringFromFile(NUMBERS);
        boolean[] ids = readBooleanFromFile(POSITIONS);
        int[] years = readIntFromFile(YEARS);
        int[] months = readIntFromFile(MONTHS);
        int[] days = readIntFromFile(DAYS);


        System.out.println("Здравствуйте! Вас приветствует телефонный справочник.");
        System.out.println("Выберите нужный пункт в меню для дальнейших действий: ");
        while (true) {


            System.out.println("1 - Добавить контакт");
            System.out.println("2 - Удалить контакт");
            System.out.println("3 - Обновить");
            System.out.println("4 - Вывести все контакты");
            System.out.println("5 - Поиск");
            System.out.println("6 - Сортировка");
            System.out.println("7 - Редактирование");
            System.out.println("0 - Выход");
            String menu = in.nextLine();
            if ("0".equals(menu)) {
                saveStringToFile(names, NAMES);
                saveStringToFile(numbers, NUMBERS);
                saveBooleanToFile(ids, POSITIONS);
                saveIntToFile(years, YEARS);
                saveIntToFile(months, MONTHS);
                saveIntToFile(days, DAYS);
                readStringFromFile(NAMES);
                readStringFromFile(NUMBERS);
                readBooleanFromFile(POSITIONS);
                readIntFromFile(YEARS);
                readIntFromFile(MONTHS);
                readIntFromFile(DAYS);
                break;
            }
            if ("1".equals(menu)) {
                addNewContact(ids, names, numbers, years, months, days);
            }
            if ("2".equals(menu)) {
                removeContact(ids);
            }
            if ("4".equals(menu)) {
                printOutContacts(ids, names, numbers, years, months, days);
            }
            if ("5".equals(menu)) {
                searchContactInPhoneBook(ids, names, numbers, years, months, days);
            }
            if ("6".equals(menu)) {
                contactSorting(ids, names, numbers, years, months, days);
            }
            if ("7".equals(menu)) {
                editContact(ids, names, numbers, years, months, days);
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

    public static void addNewContact(boolean[] ids, String[] names, String[] numbers, int[] years, int[] months, int[] days) {
        Scanner in = new Scanner(System.in);
        System.out.println("Добавление...");
        System.out.println("Введите имя:");
        String name = in.nextLine();
        System.out.println("Введите телефон абонента " + name + ":");
        String number = in.nextLine();
        System.out.println("Год рождения:");
        int year = in.nextInt();
        System.out.println("Месяц рождения:");
        int month = in.nextInt();
        System.out.println("День рождения:");
        int day = in.nextInt();

        int id = findId(ids);
        names[id] = name;
        numbers[id] = number;
        years[id] = year;
        months[id] = month;
        days[id] = day;
        ids[id] = true;
    }

    public static void printOutContacts(boolean[] ids, String[] names, String[] numbers, int[] years, int[] months, int[] days) {
        System.out.println("Список всех:");
        System.out.println("_____________________________________________");
        System.out.println("|" + "ID" + "|" + "   NAME   " + "|" + "   NUMBER   " + "|" + "   BIRTH DATE   " + "|");
        System.out.println("-------------------------------");
        for (int i = 1; i < ids.length; i++) {
            if (ids[i]) {
                System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|" + " " + "|" + " " + years[i] + "." + months[i] + "." + days[i] + " " + "|");
                System.out.println("------------------------------------");
            }
        }
    }

    public static void searchContactInPhoneBook(boolean[] ids, String[] names, String[] numbers, int[] years, int[] months, int[] days) {
        System.out.println("Поиск...");
        System.out.println("Выберите метод поиска: ");
        System.out.println("1 - по ID");
        System.out.println("2 - по имени");
        System.out.println("3 - по номеру телефона");
        Scanner in = new Scanner(System.in);
        String search = in.nextLine();
        if ("1".equals(search)) {
            System.out.println("Введите ID абонента:");
            int searchID = Integer.valueOf(in.nextLine());
            for (int i = 1; i < ids.length; i++) {
                if (searchID == i) {
                    System.out.println("________________________________________________");
                    System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|" + " " + "|" + " " + years[i] + "." + months[i] + "." + days[i] + " " + "|");
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
                        System.out.println("________________________________________________");
                        System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|" + " " + "|" + " " + years[i] + "." + months[i] + "." + days[i] + " " + "|");
                        System.out.println("----------------------------------");
                    } else {
                        System.out.println("Записи с таким именем не обнаружено");
                    }

                }
            }
        }
        if ("3".equals(search)) {
            boolean okay = false;
            System.out.println("Введите номер телефона абонента: ");
            String searchNumber = in.nextLine();
            for (int i = 1; i < ids.length; i++) {
                if (searchNumber != null && numbers[i] != null && searchNumber.equals(numbers[i])) {
                    okay = true;
                    if (okay) {
                        System.out.println("________________________________________________");
                        System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|" + " " + "|" + " " + years[i] + "." + months[i] + "." + days[i] + " " + "|");
                        System.out.println("----------------------------------");
                    } else {
                        System.out.println("Запись с таким номером не найдена");
                    }
                }
            }
        }
    }

    public static void removeContact(boolean[] ids) {
        System.out.println("Удаление...");
        System.out.println("Введите номер абонента (ID) для удаления: ");
        Scanner in = new Scanner(System.in);
        int searchID = Integer.valueOf(in.nextLine());
        for (int i = 1; i < ids.length; i++) {
            if (searchID == i) {
                ids[i] = false;
            }
        }
    }

    public static void contactSorting(boolean[] ids, String[] names, String[] numbers, int[] years, int[] months, int[] days) throws IOException {
        System.out.println("Сортировка...");
        String[] nameSearch = new String[ids.length];
        int minI = 1;
        int k = 1;
        for (int i = 1; i < ids.length; i++) {
            if (ids[i]) {
                for (int i1 = 1; i1 < ids.length; i1++) {
                    if (ids[i1] && !findIn(names[i1], nameSearch)) {
                        minI = i1;
                        break;
                    }
                }
                for (int j = 1; j < ids.length; j++) {
                    if (ids[j] && !findIn(names[j], nameSearch) && names[j].compareTo(names[minI]) <= 0) {
                        minI = j;
                    }
                }
                nameSearch[k] = names[minI];
                k++;
                System.out.println(minI + " " + names[minI] + " " + numbers[minI] + " " + "|" + " " + years[i] + "." + months[i] + "." + days[i] + " " + "|");


            }
        }
        for (int i = 1; i < ids.length; i++) {
            if (ids[i]) {
                for (int i1 = 1; i1 < ids.length; i1++) {
                    if (ids[i1] && !findIn(dateToString(years[i1], months[i1], days[i1]), nameSearch)) {
                        minI = i1;
                        break;
                    }
                }
                for (int j = 1; j < ids.length; j++) {
                    if (ids[j] && !findIn(dateToString(years[j], months[j], days[j]), nameSearch) &&
                            dateToString(years[j], months[j], days[j]).
                                    compareTo(dateToString(years[minI], months[minI], days[minI])) <= 0) {
                        minI = j;
                    }
                }
                nameSearch[k] = dateToString(years[minI], months[minI], days[minI]);
                k++;
                System.out.println(minI + " " + names[minI] + " " + dateToString(years[minI], months[minI], days[minI]));


            }

        }
    }


    public static boolean findIn(String s, String[] m) {
        for (int i = 1; i < m.length; i++) {
            if (s.equals(m[i])) {
                return true;
            }
        }
        return false;
    }

    public static String dateToString(int year, int month, int date) {
        String s = year + "-";
        if (String.valueOf(month).length() == 1) {
            s += "0";
        }
        s += month + "-";
        if (date < 10) {
            s += "0";
        }
        s += date;
        return s;
    }


    public static void editContact(boolean[] ids, String[] names, String[] numbers, int[] years, int[] months, int[] days) {
        System.out.println("Редактирование...");
        System.out.println("Введите ID котакта для редактирования");
        Scanner in = new Scanner(System.in);
        int searchID = Integer.valueOf(in.nextLine());
        for (int i = 1; i < ids.length; i++) {
            if (searchID == i) {
                System.out.println("________________________________________________");
                System.out.println("|" + (i) + " " + "|" + " " + names[i] + "|" + " " + numbers[i] + " " + "|" + " " + years[i] + "." + months[i] + "." + days[i] + " " + "|");
                System.out.println("----------------------------------");
            }
        }
        System.out.println("Введите новое имя:");
        String name = in.nextLine();
        names[searchID] = name;
        System.out.println("Введите новый телефон абонента:");
        String number = in.nextLine();
        numbers[searchID] = number;
        System.out.println("Год рождения:");
        int year = Integer.valueOf(in.nextLine());
        years[searchID] = year;
        System.out.println("Месяц рождения:");
        int month = Integer.valueOf(in.nextLine());
        months[searchID] = month;
        System.out.println("День рождения:");
        int day = Integer.valueOf(in.nextLine());
        days[searchID] = day;

    }


    public static void saveStringToFile(String[] m, String fileName) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutputStream.writeObject(m);
        objectOutputStream.close();
    }

    public static void saveBooleanToFile(boolean[] m, String fileName) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutputStream.writeObject(m);
        objectOutputStream.close();
    }

    public static void saveIntToFile(int[] m, String fileName) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutputStream.writeObject(m);
        objectOutputStream.close();
    }

    public static String[] readStringFromFile(String filename) throws IOException, ClassNotFoundException {
        String[] mass;
        File file = new File(filename);
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            mass = (String[]) objectInputStream.readObject();
        } else {
            mass = new String[MAX];

        }
        return mass;

    }

    public static boolean[] readBooleanFromFile(String filename) throws IOException, ClassNotFoundException {
        boolean[] mass;
        File file = new File(String.valueOf(filename));
        if(file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(String.valueOf(filename)));
            mass = (boolean[]) objectInputStream.readObject();
        } else {
            mass = new boolean[MAX];
        }
        return mass;
    }

    public static int[] readIntFromFile(String filename) throws IOException, ClassNotFoundException {
        int[] mass;
        File file = new File(String.valueOf(filename));
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(String.valueOf(filename)));
            mass = (int[]) objectInputStream.readObject();
        } else {
            mass = new int[MAX];
        }
        return mass;
    }

}





