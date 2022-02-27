import database.Database;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class Promo {

        protected String        PromoCode;
        protected String        Details;
        protected String        ShortCode;
        protected LocalDateTime StartDate;
        protected LocalDateTime EndDate;

    public Promo (String PromoCode, String Details, String ShortCode, LocalDateTime StartDate, LocalDateTime EndDate) {
        this.PromoCode          = PromoCode;
        this.Details            = Details;
        this.ShortCode          = ShortCode;
        this.StartDate          = StartDate;
        this.EndDate            = EndDate;
    }

    public String getPromoCode() {return PromoCode;}

    public void setPromoCode(String promoCode) {PromoCode = promoCode; }

    public String getDetails() {return Details;}

    public void setDetails(String details) {Details = details;    }

    public String getShortCode() {return ShortCode;    }

    public void setShortCode(String shortCode) {ShortCode = shortCode;    }

    public LocalDateTime getStartDate() {return StartDate;    }

    public void setStartDate(LocalDateTime startDate) {StartDate = startDate;    }

    public LocalDateTime getEndDate() {return EndDate;}

    public void setEndDate(LocalDateTime endDate) {EndDate = endDate;}

    // Promo Data Population Insert Function
    public void insertPromo() {
        String insertQuery = "INSERT INTO tbl_promo (promoCode, details, shortCode, startDate, endDate) " +
                "VALUES ('" +
                getPromoCode() + "', '" +
                getDetails() + "', '" +
                getShortCode() + "', '" +
                getStartDate() + "', '" +
                getEndDate() + "');";

        Database.insertData(insertQuery);
    }



}

