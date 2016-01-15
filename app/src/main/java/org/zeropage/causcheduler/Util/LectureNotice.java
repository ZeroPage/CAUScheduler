package org.zeropage.causcheduler.Util;

/**
 * 과목 공지사항에 대한 정보를 담고 있는 클래스입니다.
 * Created by Lumin on 2016-01-15.
 */
public class LectureNotice {
    public final String title;
    public final String content;
    public final String authorName;
    public final String writtenDate;
    public final int hitCount;
    public final boolean isImportant;

    public LectureNotice(String title, String content, String authorName, String writtenDate, int hitCount, boolean isImportant) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.writtenDate = writtenDate;
        this.hitCount = hitCount;
        this.isImportant = isImportant;
    }
}