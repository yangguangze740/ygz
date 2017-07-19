package luju.common.util;

import org.joda.time.DateTime;

public class BillNumUtils {

    public static String createBillNum() {
        String dateString = new DateTime().toString("YYYYMMddHHmmssSS");
        return dateString;
    }
}
