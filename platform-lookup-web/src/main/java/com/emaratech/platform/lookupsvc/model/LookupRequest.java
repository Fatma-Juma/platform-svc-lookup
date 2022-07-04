package com.emaratech.platform.lookupsvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 
 */
public class LookupRequest {
    private String lookupType;

    private String jsonString;
}
