package luju.common.util;

import com.luju.pojo.DcPlanInfo;

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

        System.out.println("-------------");
        System.out.println(set);

        for(DcPlanInfo dcPlanInfo : set) {
            System.out.println(dcPlanInfo.getDcType());
            System.out.println(dcPlanInfo.getDcStartTime());
        }

        return set;
    }

}

