package com.luju.ygz.fc.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.FcPlanInfo;

import java.util.List;

public interface FcRepositoryI {

    List<FcPlanInfo> selectFcPlanFromOra();

    int deletePlanCopy();

    boolean insertFcData(final List<FcPlanInfo> fcPlanInfos);

    List<DcPlanInfo> sfbzNumberList();

    List<DcPlanInfo> sfbz(String number);

    List<DcPlanInfo> sftcNumberList();

    List<DcPlanInfo> sftc(String source);

    List<DcPlanInfo> sfzmList();

    List<DcPlanInfo> sfzcList();

    boolean insertDcData(final List<DcPlanInfo> dcPlanInfos);

}
