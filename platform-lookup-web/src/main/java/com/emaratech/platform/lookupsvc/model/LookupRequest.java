package com.emaratech.platform.lookupsvc.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * Holds the lookup request data for save.
 */
public class LookupRequest {

    @NotEmpty(message = "Lookup type can not be empty or null.")
    private String lookupType;

    @NotEmpty(message = "Lookup data can not be empty.")
    private String lookupData;
}
