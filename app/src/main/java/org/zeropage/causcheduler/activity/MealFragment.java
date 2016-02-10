package org.zeropage.causcheduler.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.*;

import android.widget.ListView;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.adapter.MealsAdapter;
import org.zeropage.causcheduler.data.Meal;


public class MealFragment extends Fragment {

	private Realm realm;

	public static MealFragment newInstance() {
		MealFragment fragment = new MealFragment();
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
		View rootView = inflater.inflate(R.layout.fragment_meal, container, false);
		ListView listView = (ListView)rootView.findViewById(R.id.listView_meal);
		// TODO ListView에 어댑터 달고, 과제 정보 받아 와야함.
		RealmResults<Meal> meals = realm.where(Meal.class).findAll();
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
		inflater.inflate(R.menu.menu_meal, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
