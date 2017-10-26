package com.bw.csh_item.bean;

import java.util.List;

/**
 * Created by dell-pc on 2017/10/17.
 */

public class TypeThirdBean {

    /**
     * code : 200
     * datas : {"class_list":[{"gc_id":"525","gc_name":"女士香水"},{"gc_id":"526","gc_name":"男士香水"},{"gc_id":"527","gc_name":"组合套装"},{"gc_id":"528","gc_name":"迷你香水"},{"gc_id":"529","gc_name":"香体走珠"}]}
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
        private List<ClassListBean> class_list;

        public List<ClassListBean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListBean> class_list) {
            this.class_list = class_list;
        }

        public static class ClassListBean {
            /**
             * gc_id : 525
             * gc_name : 女士香水
             */

            private String gc_id;
            private String gc_name;

            public String getGc_id() {
                return gc_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public String getGc_name() {
                return gc_name;
            }

            public void setGc_name(String gc_name) {
                this.gc_name = gc_name;
            }
        }
    }
}
