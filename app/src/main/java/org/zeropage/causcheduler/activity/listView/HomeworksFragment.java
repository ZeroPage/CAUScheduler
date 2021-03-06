package org.zeropage.causcheduler.activity.listView;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.*;

import android.widget.AdapterView;
import android.widget.ListView;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.activity.detailView.DetailHomeworkActivity;
import org.zeropage.causcheduler.adapter.HomeworksAdapter;
import org.zeropage.causcheduler.data.Homework;
import org.zeropage.causcheduler.data.Lecture;
import org.zeropage.causcheduler.dialog.LectureChangeDialogFragment;
import org.zeropage.causcheduler.util.SharedConstant;

/**
 * 과제 목록을 출력하는 화면입니다.
 */
public class HomeworksFragment extends Fragment implements LectureChangeDialogFragment.LectureChangeListener{
    private final String LOG_TAG = HomeworksFragment.class.getSimpleName();
    private Realm realm;
    private HomeworksAdapter homeworksAdapter;
    private RealmResults<Homework> homeworks;

    public static HomeworksFragment newInstance() {
        return new HomeworksFragment();
    }

    public HomeworksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.label_homework);
        View rootView = inflater.inflate(R.layout.fragment_homeworks, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.listView_homework);
        // TODO ListView에 어댑터 달고, 과제 정보 받아 와야함.
        final Lecture lecture = realm.where(Lecture.class).findFirst();
        if(lecture != null) homeworks = realm.where(Homework.class).equalTo("lecture.name", lecture.getName()).findAll();
        homeworksAdapter = new HomeworksAdapter(getActivity().getApplicationContext(), homeworks, true);
        listView.setAdapter(homeworksAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Homework homework = homeworks.get(position);

                Intent detailViewIntent = new Intent(getActivity().getApplicationContext(), DetailHomeworkActivity.class);
                startActivity(detailViewIntent);
            }
        });
        return rootView;
    }

    @Override
    public void onDialogLectureNameClick(String name) {
        homeworks = realm.where(Homework.class).equalTo("lecture.name", name).findAll();
        homeworksAdapter.updateRealmResults(homeworks);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_homeworks, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_change_lecture:
                LectureChangeDialogFragment changeLectureDialog = new LectureChangeDialogFragment();
                changeLectureDialog.show(getActivity().getFragmentManager(), LectureChangeDialogFragment.class.getSimpleName());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
