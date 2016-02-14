package org.zeropage.causcheduler.data;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Required;

/**
 * Created by Donghwan on 2016-01-21.
 */
public class LectureNotice extends RealmObject {
    @Required
    private String title;               // 제목
    private String content;             // 내용
    @Required
    private String authorName;          // 작성자
    @Required
    private String writtenDate;         // 작성 일자
    private boolean isImportant;       // 중요 여부

    @Ignore
    private int hitCount;               // 조회수

    public LectureNotice() {}

    /**
     * LectureNotice의 인스턴스를 초기화합니다
     * @param title 제목을 가리킵니다.
     * @param content 내용을 가리킵니다.
     * @param authorName 작성자를 가리킵니다.
     * @param writtenDate 작성 일자를 가리킵니다.
     * @param hitCount 조회수를 가리킵니다.
     * @param isImportant 중요 여부를 가리킵니다.
     */
    public LectureNotice(String title, String content, String authorName, String writtenDate, int hitCount, boolean isImportant) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.writtenDate = writtenDate;
        this.hitCount = hitCount;
        this.isImportant = isImportant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(String writtenDate) {
        this.writtenDate = writtenDate;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setIsImportant(boolean isImportant) {
        this.isImportant = isImportant;
    }
}
