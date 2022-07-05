package com.emaratech.platform.lookupsvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 
 */
public class LookupRequest {
   // @NotNull(message = "Lookup type can not be null.")
    @NotEmpty(message = "Lookup type can not be empty or null.")
    private String lookupType;

    @NotEmpty(message = "Lookup data can not be empty.")
    private String lookupData;
}
