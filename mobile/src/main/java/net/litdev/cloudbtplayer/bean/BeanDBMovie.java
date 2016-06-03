package net.litdev.cloudbtplayer.bean;

import java.util.List;

/**
 * Created by litde on 2016/5/30.
 */
public class BeanDBMovie extends BeanBase {
    private List<BeanDBMoveItem> subjects;

    public List<BeanDBMoveItem> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<BeanDBMoveItem> subjects) {
        this.subjects = subjects;
    }
}
