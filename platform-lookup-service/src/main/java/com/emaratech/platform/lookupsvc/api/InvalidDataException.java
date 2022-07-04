package com.emaratech.platform.lookupsvc.api;

/**
 * Represents an exception that occurs while trying to insert/fetch/delete the
 * lookup data.
 */
public class InvalidDataException extends Exception {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new
     * {@link com.emaratech.platform.lookupsvc.api.InvalidDataException}.
     *
     * @param message associated with this exception
     */
    public InvalidDataException(String message) {
        super(message);
    }

    /**
     * Creates a new
     * {@link com.emaratech.platform.lookupsvc.api.InvalidDataException}.
     *
     * @param message associated with this exception
     * @param cause throwable wrapped exception thrown below
     */
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new
     * {@link com.emaratech.platform.lookupsvc.api.InvalidDataException}.
     *
     * @param cause throwable wrapped exception thrown below
     */
    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}
