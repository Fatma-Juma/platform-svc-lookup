package com.emaratech.platform.lookupsvc.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Holds the request.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LookupSubRequest extends LookupRequest {

    private String Id;

}
