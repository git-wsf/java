package com.yangliuxin.vo;

import java.util.ArrayList;
import java.util.List;



public class DealerScoreQueryResp extends VivoResponse {

    private static final long serialVersionUID = -9094304196797804321L;

    private List<DealerScore> objectResult;

    public static class DealerScore{
        private String firstLevelAgentId;//不可空	一级代理
        private String firstLevelAgentName;//一代名称
        private String secondLevelAgentId;//可空	二级代理
        private String storeType;//可空	门店分类（A类门店、B类门店）
        private String storeCode;//不可空	零售门店编码
        private String storeAddress;//不可空	零售门店地址
        private String storeName;//不可空	零售门店官网名称

        private List<ScoreItem> scoreItems;
        private List<NationalRankingItem> nationalRankingItems;
        private List<PeriodRankingItem> periodRankingItems;

        private String beatPercentage;//可空	 累计得分击败全国门店百分比

        public void addScoreItem(ScoreItem scoreItem){
            if(this.scoreItems==null){
                this.scoreItems=new ArrayList<ScoreItem>();
            }
            this.scoreItems.add(scoreItem);
        }
        public void addNationalRankingItem(NationalRankingItem nationalRankingItem){
            if(this.nationalRankingItems==null){
                this.nationalRankingItems=new ArrayList<NationalRankingItem>();
            }
            this.nationalRankingItems.add(nationalRankingItem);
        }
        public void addPeriodRankingItem(PeriodRankingItem periodRankingItem){
            if(this.periodRankingItems==null){
                this.periodRankingItems=new ArrayList<PeriodRankingItem>();
            }
            this.periodRankingItems.add(periodRankingItem);
        }

        public String getFirstLevelAgentName() {
            return firstLevelAgentName;
        }
        public void setFirstLevelAgentName(String firstLevelAgentName) {
            this.firstLevelAgentName = firstLevelAgentName;
        }
        public String getFirstLevelAgentId() {
            return firstLevelAgentId;
        }

        public void setFirstLevelAgentId(String firstLevelAgentId) {
            this.firstLevelAgentId = firstLevelAgentId;
        }

        public String getSecondLevelAgentId() {
            return secondLevelAgentId;
        }

        public void setSecondLevelAgentId(String secondLevelAgentId) {
            this.secondLevelAgentId = secondLevelAgentId;
        }

        public String getStoreType() {
            return storeType;
        }

        public void setStoreType(String storeType) {
            this.storeType = storeType;
        }

        public String getStoreCode() {
            return storeCode;
        }

        public void setStoreCode(String storeCode) {
            this.storeCode = storeCode;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public List<ScoreItem> getScoreItems() {
            return scoreItems;
        }

        public void setScoreItems(List<ScoreItem> scoreItems) {
            this.scoreItems = scoreItems;
        }

        public List<NationalRankingItem> getNationalRankingItems() {
            return nationalRankingItems;
        }

        public void setNationalRankingItems(List<NationalRankingItem> nationalRankingItems) {
            this.nationalRankingItems = nationalRankingItems;
        }

        public List<PeriodRankingItem> getPeriodRankingItems() {
            return periodRankingItems;
        }

        public void setPeriodRankingItems(List<PeriodRankingItem> periodRankingItems) {
            this.periodRankingItems = periodRankingItems;
        }

        public String getBeatPercentage() {
            return beatPercentage;
        }

        public void setBeatPercentage(String beatPercentage) {
            this.beatPercentage = beatPercentage;
        }

    }

    public static class ScoreItem{
        private String totalScore;//不可空	门店累计得分
        private String periodScore;//不可空	门店阶段得分
        private String yesterdayScore;//不可空	门店昨日得分
        public String getTotalScore() {
            return totalScore;
        }
        public void setTotalScore(String totalScore) {
            this.totalScore = totalScore;
        }
        public String getPeriodScore() {
            return periodScore;
        }
        public void setPeriodScore(String periodScore) {
            this.periodScore = periodScore;
        }
        public String getYesterdayScore() {
            return yesterdayScore;
        }
        public void setYesterdayScore(String yesterdayScore) {
            this.yesterdayScore = yesterdayScore;
        }
    }

    public static class NationalRankingItem{
        private String totalScoreRanking;//可空	门店累计得分全国排名
        private String periodScoreRanking;//可空	门店阶段得分全国排名
        private String yesterdayScoreRanking;//可空	门店昨日得分全国排名
        public String getTotalScoreRanking() {
            return totalScoreRanking;
        }
        public void setTotalScoreRanking(String totalScoreRanking) {
            this.totalScoreRanking = totalScoreRanking;
        }
        public String getPeriodScoreRanking() {
            return periodScoreRanking;
        }
        public void setPeriodScoreRanking(String periodScoreRanking) {
            this.periodScoreRanking = periodScoreRanking;
        }
        public String getYesterdayScoreRanking() {
            return yesterdayScoreRanking;
        }
        public void setYesterdayScoreRanking(String yesterdayScoreRanking) {
            this.yesterdayScoreRanking = yesterdayScoreRanking;
        }
    }

    public static class PeriodRankingItem{
        private String totalScoreRanking;//可空	门店累计得分一代排名
        private String periodScoreRanking;//可空	门店阶段得分一代排名
        private String yesterdayScoreRanking;//可空	门店昨日得分一代排名
        public String getTotalScoreRanking() {
            return totalScoreRanking;
        }
        public void setTotalScoreRanking(String totalScoreRanking) {
            this.totalScoreRanking = totalScoreRanking;
        }
        public String getPeriodScoreRanking() {
            return periodScoreRanking;
        }
        public void setPeriodScoreRanking(String periodScoreRanking) {
            this.periodScoreRanking = periodScoreRanking;
        }
        public String getYesterdayScoreRanking() {
            return yesterdayScoreRanking;
        }
        public void setYesterdayScoreRanking(String yesterdayScoreRanking) {
            this.yesterdayScoreRanking = yesterdayScoreRanking;
        }
    }

    public List<DealerScore> getObjectResult() {
        return objectResult;
    }

    public void setObjectResult(List<DealerScore> objectResult) {
        this.objectResult = objectResult;
    }

}
