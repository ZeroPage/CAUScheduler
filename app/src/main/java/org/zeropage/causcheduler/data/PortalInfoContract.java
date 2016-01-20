package org.zeropage.causcheduler.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Donghwan on 2016-01-20.
 *
 * Defines
 */
public class PortalInfoContract {

    public static final String CONTENT_AUTHORITY = "org.zeropage.causcheduler";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_HOMEWORK = "homework";
    public static final String PATH_MEAL = "meal";
    public static final String PATH_LEC_NOTICE = "lecnotice";

    public static final class HomeworkEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_HOMEWORK).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_HOMEWORK;
        public static final String COTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HOMEWORK;

        public static final String TABLE_NAME = "homework";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_START_TIME = "startTime";
        public static final String COLUUMN_END_TIME = "endTime";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_ISSUBMITTED = "isSubmitted";
    }
}
