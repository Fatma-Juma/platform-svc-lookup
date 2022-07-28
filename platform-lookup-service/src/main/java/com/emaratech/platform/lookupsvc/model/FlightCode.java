package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;

import com.poiji.annotation.ExcelCellName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * FlightCode class for holding the faith data.
 */
public class FlightCode implements Serializable {

    @ExcelCellName("FLIGHT_CODE")
    private String flightCode;

    @ExcelCellName("DESC_AR")
    private String descAr;

    @ExcelCellName("DESC_EN")
    private String descEn;

    @ExcelCellName("FLIGHT_CODE_ID")
    private Long flightCodeId;

    @ExcelCellName("IS_ARCHIVED")
    private Integer isArchived;

    @ExcelCellName("FLIGHT_NUMBER")
    private String flightNumber;
}
