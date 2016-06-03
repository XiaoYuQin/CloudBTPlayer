package net.litdev.cloudbtplayer.bean;

/**
 * Created by litde on 2016/6/1.
 */
public class BeanHashPlayer extends BeanBase {
    private String player_url;
    private String cookie;

    public String getPlayer_url() {
        return player_url;
    }

    public void setPlayer_url(String player_url) {
        this.player_url = player_url;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
