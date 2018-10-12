package cn.bitflash.bean;

public class UserRelationJoinNpcAndHlbean {

    //用户uid
    private String uid;

    //用户关系图谱左边界值
    private int lft;

    //用户关系图谱右边界值
    private int rgt;

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
    private int realUpgradeNum;

    //hlb总收益
    private float hlbTotelAssets;

    //hlb冻结数量
    private float hlbFrozenAssets;

    //hlb每天释放
    private float hlbDailyRelease;

    //hlb总释放量
    private float hlbTotelRelease;

    //hlb可用值
    private float hlbAvailableAssets;

    //当前档位的hlb释放量
    private float vipReleaseCash;

    //npc总购买
    private float npcPurchase;

    //npc总收益
    private float npcTotelAssets;

    //npc冻结数量
    private float npcFrozenAssets;

    //npc每天释放
    private float npcDailyRelease;

    //npc总释放量
    private float npcTotelRelease;

    //npc可用值
    private float npcAvailableAssets;

    //npc购买价格
    private float npcPrice;

    //当前算力
    private float power;

    //当前算力对应的邀请人数
    private int upgradeNum;

    //当前vip现金条件
    private float vipCash;

    //当前vip释放额度
    private float vipHlbAmount;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
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

    public int getRealUpgradeNum() {
        return realUpgradeNum;
    }

    public void setRealUpgradeNum(int realUpgradeNum) {
        this.realUpgradeNum = realUpgradeNum;
    }

    public float getHlbTotelAssets() {
        return hlbTotelAssets;
    }

    public void setHlbTotelAssets(float hlbTotelAssets) {
        this.hlbTotelAssets = hlbTotelAssets;
    }

    public float getHlbFrozenAssets() {
        return hlbFrozenAssets;
    }

    public void setHlbFrozenAssets(float hlbFrozenAssets) {
        this.hlbFrozenAssets = hlbFrozenAssets;
    }

    public float getHlbDailyRelease() {
        return hlbDailyRelease;
    }

    public void setHlbDailyRelease(float hlbDailyRelease) {
        this.hlbDailyRelease = hlbDailyRelease;
    }

    public float getHlbTotelRelease() {
        return hlbTotelRelease;
    }

    public void setHlbTotelRelease(float hlbTotelRelease) {
        this.hlbTotelRelease = hlbTotelRelease;
    }

    public float getHlbAvailableAssets() {
        return hlbAvailableAssets;
    }

    public void setHlbAvailableAssets(float hlbAvailableAssets) {
        this.hlbAvailableAssets = hlbAvailableAssets;
    }

    public float getVipReleaseCash() {
        return vipReleaseCash;
    }

    public void setVipReleaseCash(float vipReleaseCash) {
        this.vipReleaseCash = vipReleaseCash;
    }

    public float getNpcPurchase() {
        return npcPurchase;
    }

    public void setNpcPurchase(float npcPurchase) {
        this.npcPurchase = npcPurchase;
    }

    public float getNpcTotelAssets() {
        return npcTotelAssets;
    }

    public void setNpcTotelAssets(float npcTotelAssets) {
        this.npcTotelAssets = npcTotelAssets;
    }

    public float getNpcFrozenAssets() {
        return npcFrozenAssets;
    }

    public void setNpcFrozenAssets(float npcFrozenAssets) {
        this.npcFrozenAssets = npcFrozenAssets;
    }

    public float getNpcDailyRelease() {
        return npcDailyRelease;
    }

    public void setNpcDailyRelease(float npcDailyRelease) {
        this.npcDailyRelease = npcDailyRelease;
    }

    public float getNpcTotelRelease() {
        return npcTotelRelease;
    }

    public void setNpcTotelRelease(float npcTotelRelease) {
        this.npcTotelRelease = npcTotelRelease;
    }

    public float getNpcAvailableAssets() {
        return npcAvailableAssets;
    }

    public void setNpcAvailableAssets(float npcAvailableAssets) {
        this.npcAvailableAssets = npcAvailableAssets;
    }

    public float getNpcPrice() {
        return npcPrice;
    }

    public void setNpcPrice(float npcPrice) {
        this.npcPrice = npcPrice;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public int getUpgradeNum() {
        return upgradeNum;
    }

    public void setUpgradeNum(int upgradeNum) {
        this.upgradeNum = upgradeNum;
    }

    public float getVipCash() {
        return vipCash;
    }

    public void setVipCash(float vipCash) {
        this.vipCash = vipCash;
    }

    public float getVipHlbAmount() {
        return vipHlbAmount;
    }

    public void setVipHlbAmount(float vipHlbAmount) {
        this.vipHlbAmount = vipHlbAmount;
    }
}
