package org.zeropage.causcheduler.data;

/**
 * Created by Lumin on 2016-01-18.
 */
public class DefaultHomework extends AbstractHomework {
    public final String content;

    public DefaultHomework(String name, String startTime, String endTime, String extendEndTime, String content) {
        super(name, startTime, endTime, extendEndTime);
        this.content = content;
    }
}
