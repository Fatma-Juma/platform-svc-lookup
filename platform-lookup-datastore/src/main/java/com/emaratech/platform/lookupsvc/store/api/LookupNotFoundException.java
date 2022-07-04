package com.emaratech.platform.lookupsvc.store.api;

/**
 * Represents an exception that occurs while trying to insert/fetch/delete the
 * lookup data.
 */
public class LookupNotFoundException extends Exception {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new {@link LookupNotFoundException}.
     *
     * @param message associated with this exception
     */
    public LookupNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a new {@link LookupNotFoundException}.
     *
     * @param message associated with this exception
     * @param cause throwable wrapped exception thrown below
     */
    public LookupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new {@link LookupNotFoundException}.
     *
     * @param cause throwable wrapped exception thrown below
     */
    public LookupNotFoundException(Throwable cause) {
        super(cause);
    }
}
