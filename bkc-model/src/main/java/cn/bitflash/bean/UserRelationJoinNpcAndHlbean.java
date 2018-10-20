package cn.bitflash.bean;

public class UserRelationJoinNpcAndHlbean {

    //用户uid
    private String uid;

    //用户关系图谱左边界值
    private Integer lft;

    //用户关系图谱右边界值
    private Integer rgt;

    //用户父亲邀请码
    private String fatherCode;

    //用户关系图谱区域表示 L:左区(太阳线1线) R:右区(太阳线2线)
    private String area;

    //用户在图谱树深度
    private String layer;

    //用户姓名
    private String realname;

    //身证号
    private String idNumber;

    //是否实名
    private String isAuth;

    //是否有邀请码
    private String isInvited;

    //实际邀请人数
    private Integer realUpgradeNum;

    //实际算力
    private Float currentPower;

    //hlb总收益
    private Float hlbTotelAssets;

    //hlb冻结数量
    private Float hlbFrozenAssets;

    //hlb每天释放
    private Float hlbDailyRelease;

    //hlb总释放量
    private Float hlbTotelRelease;

    //hlb可用值
    private Float hlbAvailableAssets;

    //当前vip等级释放量
    private Float regulateRelease;

    //npc总购买
    private Float npcPurchase;

    //npc总收益
    private Float npcTotelAssets;

    //npc冻结数量
    private Float npcFrozenAssets;

    //npc每天释放
    private Float npcDailyRelease;

    //npc总释放量
    private Float npcTotelRelease;

    //npc可用值
    private Float npcAvailableAssets;

    //npc购买价格
    private Float npcPrice;

    //当前算力
    private Float power;

    //当前算力对应的邀请人数
    private Integer upgradeNum;

    //当前vip现金条件
    private Float vipCash;

    //当前vip释放额度
    private Float vipHlbAmount;

    //当前npc的释放比例
    private Float npcReleaseRate;

    //当前hlb的释放比例
    private Float hlbReleaseRate;

    //当前档位最小算力
    private Float minPower;

    //当前档位最大算力
    private Float maxPower;

    //左业绩
    private Float lftAchievement;

    //右业绩
    private Float rgtAchievement;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }

    public String getFatherCode() {
        return fatherCode;
    }

    public void setFatherCode(String fatherCode) {
        this.fatherCode = fatherCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(String isInvited) {
        this.isInvited = isInvited;
    }

    public Integer getRealUpgradeNum() {
        return realUpgradeNum;
    }

    public void setRealUpgradeNum(Integer realUpgradeNum) {
        this.realUpgradeNum = realUpgradeNum;
    }

    public Float getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(Float currentPower) {
        this.currentPower = currentPower;
    }

    public Float getHlbTotelAssets() {
        return hlbTotelAssets;
    }

    public void setHlbTotelAssets(Float hlbTotelAssets) {
        this.hlbTotelAssets = hlbTotelAssets;
    }

    public Float getHlbFrozenAssets() {
        return hlbFrozenAssets;
    }

    public void setHlbFrozenAssets(Float hlbFrozenAssets) {
        this.hlbFrozenAssets = hlbFrozenAssets;
    }

    public Float getHlbDailyRelease() {
        return hlbDailyRelease;
    }

    public void setHlbDailyRelease(Float hlbDailyRelease) {
        this.hlbDailyRelease = hlbDailyRelease;
    }

    public Float getHlbTotelRelease() {
        return hlbTotelRelease;
    }

    public void setHlbTotelRelease(Float hlbTotelRelease) {
        this.hlbTotelRelease = hlbTotelRelease;
    }

    public Float getHlbAvailableAssets() {
        return hlbAvailableAssets;
    }

    public void setHlbAvailableAssets(Float hlbAvailableAssets) {
        this.hlbAvailableAssets = hlbAvailableAssets;
    }

    public Float getRegulateRelease() {
        return regulateRelease;
    }

    public void setRegulateRelease(Float regulateRelease) {
        this.regulateRelease = regulateRelease;
    }

    public Float getNpcPurchase() {
        return npcPurchase;
    }

    public void setNpcPurchase(Float npcPurchase) {
        this.npcPurchase = npcPurchase;
    }

    public Float getNpcTotelAssets() {
        return npcTotelAssets;
    }

    public void setNpcTotelAssets(Float npcTotelAssets) {
        this.npcTotelAssets = npcTotelAssets;
    }

    public Float getNpcFrozenAssets() {
        return npcFrozenAssets;
    }

    public void setNpcFrozenAssets(Float npcFrozenAssets) {
        this.npcFrozenAssets = npcFrozenAssets;
    }

    public Float getNpcDailyRelease() {
        return npcDailyRelease;
    }

    public void setNpcDailyRelease(Float npcDailyRelease) {
        this.npcDailyRelease = npcDailyRelease;
    }

    public Float getNpcTotelRelease() {
        return npcTotelRelease;
    }

    public void setNpcTotelRelease(Float npcTotelRelease) {
        this.npcTotelRelease = npcTotelRelease;
    }

    public Float getNpcAvailableAssets() {
        return npcAvailableAssets;
    }

    public void setNpcAvailableAssets(Float npcAvailableAssets) {
        this.npcAvailableAssets = npcAvailableAssets;
    }

    public Float getNpcPrice() {
        return npcPrice;
    }

    public void setNpcPrice(Float npcPrice) {
        this.npcPrice = npcPrice;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public Integer getUpgradeNum() {
        return upgradeNum;
    }

    public void setUpgradeNum(Integer upgradeNum) {
        this.upgradeNum = upgradeNum;
    }

    public Float getVipCash() {
        return vipCash;
    }

    public void setVipCash(Float vipCash) {
        this.vipCash = vipCash;
    }

    public Float getVipHlbAmount() {
        return vipHlbAmount;
    }

    public void setVipHlbAmount(Float vipHlbAmount) {
        this.vipHlbAmount = vipHlbAmount;
    }

    public Float getNpcReleaseRate() {
        return npcReleaseRate;
    }

    public void setNpcReleaseRate(Float npcReleaseRate) {
        this.npcReleaseRate = npcReleaseRate;
    }

    public Float getHlbReleaseRate() {
        return hlbReleaseRate;
    }

    public void setHlbReleaseRate(Float hlbReleaseRate) {
        this.hlbReleaseRate = hlbReleaseRate;
    }

    public Float getMinPower() {
        return minPower;
    }

    public void setMinPower(Float minPower) {
        this.minPower = minPower;
    }

    public Float getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Float maxPower) {
        this.maxPower = maxPower;
    }

    public Float getLftAchievement() {
        return lftAchievement;
    }

    public void setLftAchievement(Float lftAchievement) {
        this.lftAchievement = lftAchievement;
    }

    public Float getRgtAchievement() {
        return rgtAchievement;
    }

    public void setRgtAchievement(Float rgtAchievement) {
        this.rgtAchievement = rgtAchievement;
    }
}
