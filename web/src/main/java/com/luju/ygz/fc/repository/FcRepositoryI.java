package com.luju.ygz.fc.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.FcPlanInfo;

import java.util.List;

public interface FcRepositoryI {

    List<FcPlanInfo> selectFcPlanFromOra();

    int deletePlanCopy();

    boolean insertFcData(final List<FcPlanInfo> fcPlanInfos);

    List<DcPlanInfo> sfbzNumberList();

    List<DcPlanInfo> sfbz(String number,String source);

    List<DcPlanInfo> sftcNumberList();

    List<DcPlanInfo> sftc(String number,String source);

    List<DcPlanInfo> sfzmList();

    List<DcPlanInfo> sfzcList();

    List<DcPlanInfo> fcMsjList();

    List<DcPlanInfo> fcDcList();

    List<DcPlanInfo> fcYhList();

    List<DcPlanInfo> fcZwqList();

    boolean insertDcData(final List<DcPlanInfo> dcPlanInfos);

}
