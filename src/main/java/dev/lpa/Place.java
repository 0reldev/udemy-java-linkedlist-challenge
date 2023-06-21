package dev.lpa;

public record Place(String name, int distance) {

    @Override
    public String toString() {
        return String.format("%s (%d km)", name, distance);
    }
}
