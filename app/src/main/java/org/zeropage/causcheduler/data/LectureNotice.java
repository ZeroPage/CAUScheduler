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
    @Ignore
    private int hitCount;               // 조회수
    private boolean isImportant;       // 중요 여부
}
