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
 * Profession class for holding the faith data.
 */
public class Profession implements Serializable {

    @ExcelCellName("PROFESSION_ID")
    private Long professionId;

    @ExcelCellName("PROFESSION_NAME_AR")
    private String professionNameAr;

    @ExcelCellName("PROFESSION_NAME_EN")
    private String professionNameEn;

    @ExcelCellName("IS_CHANGE_STATUS_ALLOWED")
    private Integer isChangeStatusAllowed;

    @ExcelCellName("IS_ARCHIVED")
    private Integer isArchived;
}
