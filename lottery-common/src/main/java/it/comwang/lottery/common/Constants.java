package it.comwang.lottery.common;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:25  12:15
 */
public class Constants {
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001","未知失败"),
        ILLEGAL_PARAMETER("0002","非法参数"),
        INDEX_DUP("0003","主键冲突"),
        NO_UPDATE("0004", "SQL 操作无更新"),
        LOSING_DRAW("0005", "未中奖")

        ;

        private String code;
        private String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }


    public enum StrategyMode {

        SINGLE(1, "单项概率"),

        ENTIRETY(2, "总体概率");


        private Integer code;

        private String info;




        StrategyMode(Integer code, String info){
            this.code = code;
            this.info = info;
        }

        public String getInfo() {
            return info;
        }


        public Integer getCode() {
            return code;
        }


        public void setCode(Integer code) {
            this.code = code;
        }


        public void setInfo(String info) {
            this.info = info;
        }
    }


    public enum DrawState{

        FAIL(0, "未中奖"),

        SUCCESS(1, "已中奖"),

        Cover(2,"兜底奖")
        ;


        private Integer code;

        private String info;

        DrawState(Integer code, String info){
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }

    public enum AwardState{


        WAIT(0, "等待发奖"),

        SUCCESS(1, "发放成功"),

        FAILURE(2, "发奖失败")

        ;

        private Integer code;

        private String info;

        AwardState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public enum AwardType{

        DESC(1, "文字描述"),

        RedeemCodeGoods(2, "兑换码"),

        CouponGoods(3, "优惠卷"),

        PhysicalGoods(4, "实物奖品")

        ;


        private Integer code;
        private String info;

        AwardType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public enum ActivityState{

        /** 1：编辑 */
        EDIT(1, "编辑"),
        /** 2：提审 */
        ARRAIGNMENT(2, "提审"),
        /** 3：撤审 */
        REVOKE(3, "撤审"),
        /** 4：通过 */
        PASS(4, "通过"),
        /** 5：运行(活动中) */
        DOING(5, "运行(活动中)"),
        /** 6：拒绝 */
        REFUSE(6, "拒绝"),
        /** 7：关闭 */
        CLOSE(7, "关闭"),
        /** 8：开启 */
        OPEN(8, "开启");

        private Integer code;

        private String info;

        ActivityState(Integer code, String info){
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * Ids 生成策略
     */
    public enum Ids{
        SnowFlake,
        ShortCode,
        RandomNumberic
        ;
    }

    public enum TaskState {

        NO_USED(0, "未使用"),
        USED(1, "已使用");

        private Integer code;
        private String info;

        TaskState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 发奖状态 0初始、1完成、2失败
     */
    public enum GrantState{

        INIT(0, "初始"),
        COMPLETE(1, "完成"),
        FAIL(2, "失败");

        private Integer code;
        private String info;

        GrantState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
