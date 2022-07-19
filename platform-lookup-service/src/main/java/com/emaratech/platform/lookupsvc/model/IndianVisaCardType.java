package com.emaratech.platform.lookupsvc.model;

/**
 *
 */
public class IndianVisaCardType {

    /**
     *
     *
     * @param identifier
     * @return
     */
    public static Name getVisaIndianCardType(long identifier) {
        if(identifier == Name.GREEN_CARD.getId()) {
            return Name.GREEN_CARD;
        }else if (identifier == Name.USA_VISA.getId()) {
            return Name.USA_VISA;
        }else if (identifier == Name.UK_EU_VISA.getId()) {
            return Name.UK_EU_VISA;
        }
        return null;
    }

    /**
     *
     */
    public enum Name {

        GREEN_CARD(1, "بطاقة إقامة في الولايات المتحدة","Green Card"),
        USA_VISA(2, "تأشيرة الولايات المتحدة", "USA Visa"),
        UK_EU_VISA(3, "تاشيرات بريطانيا و الاتحاد الاوروبي", "UK EU Visa");

        Name(long identifier, String ar, String en) {
            this.id = identifier;
            this.descAr = ar;
            this.descEn = en;
        };

        private long id;
        private String descEn;
        private String descAr;

        public long getId() {
            return id;
        }

        public String getDescEn() {
            return descEn;
        }

        public String getDescAr() {
            return descAr;
        }
    }

}
