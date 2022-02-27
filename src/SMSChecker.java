import database.Database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMSChecker {

    final private static Logger logger = Logger.getLogger(Main.class.getName());

    public static int smsChecker(Map<String, String> smsToCheck, Integer step) {
        LocalDateTime timeSent = LocalDateTime.now();

        SMS newSMS = new SMS(smsToCheck.get("sender"), smsToCheck.get("message"), smsToCheck.get("recipient"), timeSent);

        String selectQueryPromo = "SELECT promoCode FROM tbl_promo WHERE shortCode='" + smsToCheck.get("recipient") + "';";
        ArrayList<String> promoList = Database.retrievePromoList(selectQueryPromo);
        if (promoList.size() == 1) {
            String selectQueryPromoStartDate = "SELECT startDate FROM tbl_promo WHERE shortCode='" + smsToCheck.get("recipient") + "';";
            String selectQueryPromoEndDate = "SELECT endDate FROM tbl_promo WHERE shortCode='" + smsToCheck.get("recipient") + "';";

            ArrayList<String> startDateList = Database.retrievePromoList(selectQueryPromoStartDate);
            ArrayList<String> endDateList = Database.retrievePromoList(selectQueryPromoEndDate);

            LocalDateTime startDate = LocalDateTime.parse(startDateList.get(0), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime endDate = LocalDateTime.parse(endDateList.get(0), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        } else {
            newSMS.setSTATUS("FAILED");
            newSMS.insertSMS();
        }
        return step;
    }

    protected static void systemSMS(String sender, String recipient, Integer code){
        LocalDateTime timeSent = LocalDateTime.now();
        HashMap<Integer, String> static_SMS = new HashMap<>();
        static_SMS.put(1, "[SMS SUCCESSFUL] Congratulations! You are now registered to this promo.");

        SMS sysSMS = new SMS(sender, static_SMS.get(code), recipient, timeSent);
        sysSMS.setSTATUS("SUCCESSFUL");
        sysSMS.insertSMS();
        logger.log(Level.INFO, static_SMS.get(code) + "\n");

    }
}




