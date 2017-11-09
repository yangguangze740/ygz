package com.luju.ygz.dc.service;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.pojo.TextareaInfo;
import luju.common.util.DataProcess;

import java.util.List;
import java.util.Map;

public interface DcServiceI {

    /* 从ora数据库中取调车数据 通过判断是 增加 修改 删除 （需修改） */
    void selectPlanDataFromOra(DataProcess dataProcess);

    /* 从ora数据库中取调车数据  删除源数据*/
    void selectPlanDataFromOraWithDelete(DataProcess dataProcess);

    /* 1次处理挑车数据 存入挑车1表*/
    void processTcDataNew(DataProcess dataProcess);

    /* 2次处理挑车数据 存入挑车2表*/
    boolean processDcData(DataProcess dataProcess);

    /* 查询调车数据 */
    List<DcPlanInfo> selectDcData();

    /* 进路冲突判定算法 （需优化）*/
    Map<String,List<DcPlanInfo>> selectDcPath();

    /* 处理禁峰超限数据，从源数据表中查询到 禁峰、超限 车辆存入新表中 */
    void processJFCX();

    /* 查询禁峰车数据 */
    List<DcPlanInfo> selectJF();

    /* 查询超限车数据 */
    List<DcPlanInfo> selectCX();

    /* 源下拉框选择 */
    int updateSource(DcPlanInfo dcPlanInfo);

    /* 目的下拉框选择 */
    int updateDestination(DcPlanInfo dcPlanInfo);

    /* 删除展示数据 */
    int deleteShowData();

    /* 删除挑车数据1 */
    int deleteTcData();

    /* 删除挑车数据2 */
    int deleteTcDataSix();

    /* 删除禁峰超限 */
    int deleteJFCX();

    /* 提交至场调站调 */
    int insertTextarea(String s);

    /* 弹窗提示内容*/
    List<TextareaInfo> selectTextarea(TextareaInfo info);

    /* 弹窗内点击确定后，修改为1，标明不在提示 */
    int updateTextareaIsSelected(int isS);

    /* 统计功能  */
    List<StatisticsInfo> selectStatisticsInfoWithTime(String time);

    /* 分区判断 */
    List<DcPlanInfo> select4Partition();
}
