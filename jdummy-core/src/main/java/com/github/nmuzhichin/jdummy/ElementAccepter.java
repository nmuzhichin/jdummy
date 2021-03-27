package com.github.nmuzhichin.jdummy;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

/**
 * Operations for accepting elements and calling visitors
 *
 * @author nmuzhichin
 * @since 24.02.2021
 */
public interface ElementAccepter {

    /**
     * Accept element to be created by a visitor
     *
     * @param p   Parameter
     * @param <T> Type
     * @return Value of type T
     */
    <T> T accept(Parameter p);

    /**
     * Accept element to be created by a visitor
     *
     * @param f   Field
     * @param <T> Type
     * @return Value of type T
     */
    <T> T accept(Field f);

    /**
     * Accept element to be created by a visitor
     *
     * @param c   Class
     * @param <T> Type
     * @return Value of type T
     */
    <T> T accept(Class<T> c);
}
