package luju.common.util;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;

import java.util.*;

public class ListToSet {

    ComparatorSet comparatorSet = new ComparatorSet();

    public Set<DcPlanInfo> comparatorSet(List<DcPlanInfo> list4jt1,
                                         List<DcPlanInfo> list4jt2,
                                         List<DcPlanInfo> list4zm1,
                                         List<DcPlanInfo> list4zm2,
                                         List<DcPlanInfo> list4zc,
                                         List<DcPlanInfo> list4tc) {

        SortedSet<DcPlanInfo> set = new TreeSet<DcPlanInfo>(comparatorSet);

        set.addAll(list4jt1);
        set.addAll(list4jt2);
        set.addAll(list4zm1);
        set.addAll(list4zm2);
        set.addAll(list4zc);
        set.addAll(list4tc);

        return set;
    }

    public Set<JcPlanInfo> comparatorSet4Jc(List<JcPlanInfo> list4jt1,
                                         List<JcPlanInfo> list4jt) {

        SortedSet<JcPlanInfo> set = new TreeSet<JcPlanInfo>();

        set.addAll(list4jt1);

        return set;
    }
}

