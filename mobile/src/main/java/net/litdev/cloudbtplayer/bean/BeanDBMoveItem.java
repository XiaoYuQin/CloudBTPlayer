package net.litdev.cloudbtplayer.bean;

/**
 * Created by litde on 2016/5/30.
 */
public class BeanDBMoveItem extends BeanBase {
    private String rate;
    private int cover_x;
    private boolean is_beetle_subject;
    private String title;
    private String url;
    private boolean playable;
    private String cover;
    private String id;
    private int cover_y;
    private boolean is_new;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getCover_x() {
        return cover_x;
    }

    public void setCover_x(int cover_x) {
        this.cover_x = cover_x;
    }

    public boolean is_beetle_subject() {
        return is_beetle_subject;
    }

    public void setIs_beetle_subject(boolean is_beetle_subject) {
        this.is_beetle_subject = is_beetle_subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public String getCover() {
        return cover.replace("\\","");
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCover_y() {
        return cover_y;
    }

    public void setCover_y(int cover_y) {
        this.cover_y = cover_y;
    }

    public boolean is_new() {
        return is_new;
    }

    public void setIs_new(boolean is_new) {
        this.is_new = is_new;
    }
}
