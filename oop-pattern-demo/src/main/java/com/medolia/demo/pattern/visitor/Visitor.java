package com.medolia.demo.pattern.visitor;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public interface Visitor {

    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);

}
