package luju.common.util;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import com.luju.pojo.ResultInfo;

import java.util.*;

public class ListToSet {

    ComparatorSet comparatorSet = new ComparatorSet();

    public Set<ResultInfo> comparatorSet(List<ResultInfo> jcList,
                                         List<ResultInfo> bwjList,
                                         List<ResultInfo> jt1List,
                                         List<ResultInfo> jt2List,
                                         List<ResultInfo> zm1List,
                                         List<ResultInfo> zm2List,
                                         List<ResultInfo> zcList,
                                         List<ResultInfo> tcList) {

        SortedSet<ResultInfo> set = new TreeSet<ResultInfo>(comparatorSet);

        set.addAll(jcList);
        set.addAll(bwjList);
        set.addAll(jt1List);
        set.addAll(jt2List);
        set.addAll(zm1List);
        set.addAll(zm2List);
        set.addAll(zcList);
        set.addAll(tcList);

        return set;
    }

}

