package com.medolia.demo.jvm;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public sealed class SealDemo {
    final class InnerDemo extends SealDemo {
    }

    abstract sealed class Color {
        final class Red extends Color {
        }

        final class Blue extends Color {
        }

        sealed class White extends Color {
            final class LightWhite extends White {
            }

            final class HeavyWhite extends White {
            }
        }
    }
}
