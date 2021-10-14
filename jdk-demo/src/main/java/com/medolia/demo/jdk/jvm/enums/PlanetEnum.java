package com.medolia.demo.jdk.jvm.enums;

/**
 * @author lb@li@trip.com
 * @date 2021/7/12
 */
public enum PlanetEnum {
    /**
     * 水星
     * 土星
     * 地球
     * 火星
     * ...
     */
    MERCURY (3.303e+23, 2.4397e6),
    VENUS   (4.869e+24, 6.0518e6),
    EARTH   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    JUPITER (1.9e+27,   7.1492e7),
    SATURN  (5.688e+26, 6.0268e7),
    URANUS  (8.686e+25, 2.5559e7),
    NEPTUNE (1.024e+26, 2.4746e7);

    private final double mass;
    private final double radius;

    PlanetEnum(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public static final double G = 6.67300E-11;

    double surfaceGravity() {
        return G * mass / (radius * radius);
    }
    double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }

    public static void main(String[] args) {
        int weight = 175;
        double earthWeight = 1.0 * weight;
        double mass = earthWeight / EARTH.surfaceGravity();
        for (PlanetEnum planet : PlanetEnum.values()) {
            System.out.printf("your weight on %7s is %10f\n", planet,
                    planet.surfaceWeight(mass));
        }
    }
}
