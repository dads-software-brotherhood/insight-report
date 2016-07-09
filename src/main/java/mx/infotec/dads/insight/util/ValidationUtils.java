/*
 *
 * Licencia: Usted puede utilizar libremente este código para copiarlo, distribuirlo o modificarlo
 * total o parcialmente siempre y cuando mantenga este aviso y reconozca la autorÃ­a del cÃ³digo al
 * no modificar los datos establecidos en la menciÃ³n de "AUTOR".
 *
 * Proyecto: skeleton Paquete: mx.com.infotec.dadt.arq.core.support Módulo: Convert Tipo: Clase
 * 
 * @author: Daniel Cortes Pichardo Versión: 1.5.10
 */
package mx.infotec.dads.insight.util;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * <p>
 * Title: ValidationUtils.java
 * </p>
 * 
 * @author Daniel Cortes Pichardo
 * @version 1.0
 */
public class ValidationUtils implements Serializable {

    /**
     * Representa el valor inicial de la versiÃ³n del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Instancia del log de sistema.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

    private ValidationUtils() {
    }

    /**
     * Check not null.
     *
     * @param <T>
     *            the generic type
     * @param object
     *            the object
     * @param message
     *            the message
     * @return the t
     */
    public static <T> T checkNotNull(T object, String message) {
	return Preconditions.checkNotNull(object, message);
    }

    /**
     * Check argument.
     * <p>
     * Asegura que una expresiÃ³n sea verdad
     *
     * @param expression
     *            una expresiÃ³n de tipo boolean
     * @param errorMessage
     *            el mensaje de la excepciÃ³n que se utilizarÃ¡ si la
     *            verificaciÃ³n falla; el mensaje se convertirÃ¡ a String
     *            utilizando la expresiÃ³n String.valueOf(Object)
     * @throws IllegalArgumentException
     *             si la expresiÃ³n es falsa
     */
    public static void checkArgument(boolean expression, Object errorMessage) {
	Preconditions.checkArgument(expression, errorMessage);
    }

    /**
     * Checks if is null or empty.
     *
     * Regresa true si el string es null o la cadena vacÃ­a.
     *
     *
     * @param string
     *            la cadena que se desea verificar
     * @return true, si es null o cadena vacÃ­a
     */
    public static boolean isNullOrEmpty(String string) {
	LOGGER.debug("isNullOrEmpty( {} )", string);
	return Strings.isNullOrEmpty(string);
    }

    public static boolean isEmpty(Object[] array) {
	return array == null || array.length == 0;
    }

    public static <T extends List<?>> boolean isEmpty(T collection) {
	return collection == null || collection.isEmpty();
    }

    /**
     * Check not null.
     *
     * @param <T>
     *            the generic type
     * @param object
     *            the object
     * @param message
     *            the message
     * @return the t
     */
    public static void checkCondition(boolean check, String message) {
	if (!check) {
	    throw new IllegalArgumentException(message);
	}
    }
}
