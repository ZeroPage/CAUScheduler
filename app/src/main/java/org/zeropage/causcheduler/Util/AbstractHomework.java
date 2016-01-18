package org.zeropage.causcheduler.Util;

/**
 * Created by Lumin on 2016-01-18.
 */
public abstract class AbstractHomework {
    public final String name;
    public final String startTime;
    public final String endTime;
    public final String extendEndTime;

    public AbstractHomework(String name, String startTime, String endTime, String extendEndTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.extendEndTime = extendEndTime;
    }
}
