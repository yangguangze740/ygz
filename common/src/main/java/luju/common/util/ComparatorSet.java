package luju.common.util;

import com.luju.pojo.DcPlanInfo;

import java.text.Collator;
import java.util.*;

public class ComparatorSet implements Comparator<DcPlanInfo>{

    Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

    @Override
    public int compare(DcPlanInfo o1, DcPlanInfo o2) {

        return o1.getDcStartTime().compareTo(o2.getDcStartTime());
    }
}
