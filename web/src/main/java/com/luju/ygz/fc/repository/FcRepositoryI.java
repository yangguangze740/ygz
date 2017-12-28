package com.luju.ygz.fc.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.FcPlanInfo;

import java.util.List;

public interface FcRepositoryI {

    List<FcPlanInfo> selectFcPlanFromOra();

    int deletePlanCopy();

    boolean insertFcData(final List<FcPlanInfo> fcPlanInfos);

    List<DcPlanInfo> sfbzList();

    List<DcPlanInfo> sftcList();

    List<DcPlanInfo> sfzcList();

    List<DcPlanInfo> sfzmList();

}
