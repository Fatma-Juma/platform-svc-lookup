package com.emaratech.platform.lookupsvc.webapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * Holds the lookup data.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookupDTO extends LookupSubResponse {

    private String code;

    private Long parentId;

    /**
     * Copy constructor for setting up the values.
     *
     * @param id the id
     * @param nameEn the nameEn
     * @param nameAr the nameAr
     * @param code the code
     */
    public LookupDTO(Long id, String nameEn, String nameAr, String code) {
        super(id, nameEn, nameAr);
        this.code = code;
    }

    /**
     * Copy constructor for setting up the values.
     *
     * @param id the id
     * @param nameEn the nameEn
     * @param nameAr the nameAr
     * @param parentId the parentId
     */
    public LookupDTO(Long id, String nameEn, String nameAr, Long parentId) {
        super(id, nameEn, nameAr);
        this.parentId = parentId;
    }

    /**
     * Copy constructor for setting up the values.
     *
     * @param id the id
     * @param code the code
     */
    public LookupDTO(Long id, String code) {
        super(id);
        this.code = code;
    }

    /**
     * Default constructor.
     */
    public LookupDTO() {
        super();

    }

    /**
     * Copy constructor for setting up the values.
     *
     * @param id the id
     * @param nameEn the nameEn
     * @param nameAr the nameAr
     */
    public LookupDTO(Long id, String nameEn, String nameAr) {
        super(id, nameEn, nameAr);
    }
}
