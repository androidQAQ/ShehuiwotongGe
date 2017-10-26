package com.bw.csh_item.bean;

/**
 * Created by 曹少航 on 2017/10/17.
 */

public class LogBean {

    /**
     * code : 200
     * datas : {"username":"13621059509","userid":"13","key":"0c6cd78e519bc7fea18d749a3dbba3ad"}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * username : 13621059509
         * userid : 13
         * key : 0c6cd78e519bc7fea18d749a3dbba3ad
         */

        private String username;
        private String userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
