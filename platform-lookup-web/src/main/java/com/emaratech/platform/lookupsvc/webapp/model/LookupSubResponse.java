package com.emaratech.platform.lookupsvc.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Holds the lookup response.
 */
public class LookupSubResponse {

    private long id;

    private String nameEn;

    private String nameAr;

    /**
     * Copy constructor for setting up the values.
     *
     * @param id the id
     */
    public LookupSubResponse(Long id) {
        this.id = id;
    }
}
