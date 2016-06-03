package net.litdev.cloudbtplayer.bean;

import java.util.List;

/**
 * 网络检索的BT数据
 */
public class BeanBTSearch extends BeanBase {
    private String title;
    private String date;
    private String size;
    private String hot;
    private String hash;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
