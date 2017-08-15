package luju.common.util;

import com.luju.pojo.ResultInfo;

import java.util.*;

public class ComparatorSet implements Comparator<ResultInfo>{

    @Override
    public int compare(ResultInfo o1, ResultInfo o2) {

        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}
