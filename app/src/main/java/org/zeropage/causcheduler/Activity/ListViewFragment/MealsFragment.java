package org.zeropage.causcheduler.activity.ListViewFragment;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.*;

import android.widget.ListView;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.adapter.MealsAdapter;
import org.zeropage.causcheduler.data.Meal;


public class MealsFragment extends Fragment {
	private final String LOG_TAG = MealsFragment.class.getSimpleName();
	private Realm realm;

	public static MealsFragment newInstance() {
		MealsFragment fragment = new MealsFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		realm = Realm.getDefaultInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		getActivity().setTitle(R.string.label_meals);
		View rootView = inflater.inflate(R.layout.fragment_meals, container, false);
		ListView listView = (ListView)rootView.findViewById(R.id.listView_meal);
		RealmResults<Meal> meals = realm.where(Meal.class).findAll();
		Log.d(LOG_TAG, "realmResultSize: " + meals.size());
		final MealsAdapter mealsAdapter = new MealsAdapter(getActivity().getApplicationContext(), meals, true);
		listView.setAdapter(mealsAdapter);
		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		realm.close();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_meals, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
