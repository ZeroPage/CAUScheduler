package org.zeropage.causcheduler.data.original;

/**
 * Created by Lumin on 2016-01-18.
 */
public class HomeworkContent extends AbstractHomework {
    public final String content;

    public HomeworkContent(String name, String startTime, String endTime, String extendEndTime, String content) {
        super(name, startTime, endTime, extendEndTime);
        this.content = content;
    }
}
