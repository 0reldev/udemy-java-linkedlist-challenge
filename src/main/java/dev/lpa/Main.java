package dev.lpa;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        LinkedList<Place> placesToVisit = new LinkedList<>();

        addPlace(placesToVisit, new Place("Adelaide", 1374));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Darwin", 3972));
        addPlace(placesToVisit, new Place("Melbourne", 877));
        addPlace(placesToVisit, new Place("Perth", 3923));
        placesToVisit.addFirst(new Place("Sydney", 0));
        System.out.println(placesToVisit);

        var iterator = placesToVisit.listIterator();
        boolean quitLoop = false;
        boolean forward = true;

        printMenu();

        while (!quitLoop) {
            if (!iterator.hasPrevious()) {
                System.out.println("Originating: "  + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("Final: "  + iterator.previous());
                forward = false;
            }

            System.out.println("Enter value: ");
            String menuItem = scanner.nextLine().substring(0, 1).toUpperCase();
            switch (menuItem) {
                case "F" -> {
                    System.out.println("User wants to go forward.");
                    if (!forward) {     // reversing direction
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();    // adjust position forward
                        }
                    }
                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
                case "B" -> {
                    System.out.println("User wants to go backward.");
                    if (forward) {     // reversing direction
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();    // adjust position forward
                        }
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                }
                case "L" -> listPlaces(placesToVisit);
                case "M" -> printMenu();
                default -> quitLoop = true;
            }
        }

    }

    private static void addPlace(LinkedList<Place> list, Place place) {
        if (list.contains(place)) {
            System.out.println("Found duplicate: " +  place);
            return;
        }
        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found duplicate: " +  place);
                return;
            }
        }
        int matchedIndex = 0;
        for (var listPlace : list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }
        list.add(place);
    }

    public static void printMenu() {
        System.out.println("""
                    Available actions (select word or letter):
                    (F)orward
                    (B)ackward
                    (L)ist Places
                    (M)enu
                    (Q)uit"""
        );
    }
    public static void listPlaces(LinkedList<Place> cities) {
        System.out.println(cities);
    }
}
