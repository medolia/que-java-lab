package com.medolia.demo.jvm;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class RecordDemo {

    static record Coord(int x, int y) {

        public boolean isCenter() {
            return x() == 0 && y() == 0;
        }

        static boolean isCenter(Coord coord) {
            return coord.isCenter();
        }
    }

    public static void main(String[] args) {
        Coord coord = new Coord(10, -281);
        Class<? extends Coord> aClass = coord.getClass();
        System.out.printf("super class: %s%n", aClass.getGenericSuperclass().getTypeName());

        System.out.println("hash: " + coord.hashCode());
        System.out.println("system hash: " + System.identityHashCode(coord));
        System.out.println("toString: " + coord);
        System.out.printf("is center: %s%n", coord.isCenter());
    }

}
