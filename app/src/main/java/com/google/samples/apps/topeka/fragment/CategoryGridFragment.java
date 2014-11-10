/*
 * Copyright 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.topeka.fragment;

import com.google.samples.apps.topeka.R;
import com.google.samples.apps.topeka.activity.QuizActivity;
import com.google.samples.apps.topeka.adapter.CategoryCursorAdapter;
import com.google.samples.apps.topeka.persistence.CategoryCursor;
import com.google.samples.apps.topeka.widget.CategoryLayout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class CategoryGridFragment extends Fragment implements AdapterView.OnItemClickListener {

    private CategoryCursorAdapter mAdapter;

    public static CategoryGridFragment newInstance() {
        return new CategoryGridFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setUpQuizGrid((GridView) view.findViewById(R.id.categories));
        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpQuizGrid(GridView categoriesView) {
        categoriesView.setOnItemClickListener(this);
        mAdapter = new CategoryCursorAdapter(getActivity());
        categoriesView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Activity activity = getActivity();
        //TODO: finalize the animations
        if (view instanceof CategoryLayout) {
            CategoryLayout categoryLayout = (CategoryLayout) view;
            Pair[] participants = getTransitionParticipants(activity, categoryLayout);
            ActivityOptions sceneTransitionAnimation = ActivityOptions
                    .makeSceneTransitionAnimation(activity, participants);
            CategoryCursor item = (CategoryCursor) mAdapter.getItem(position);
            activity.startActivity(QuizActivity.getStartIntent(activity, item.getCategory()),
                    sceneTransitionAnimation.toBundle());
        } else {
            throw new UnsupportedOperationException("Only CategoryLayout is supported");
        }
    }

    private Pair[] getTransitionParticipants(Activity activity, CategoryLayout categoryLayout) {
        Pair[] participants = new Pair[2];
        participants[0] = new Pair<View, String>(categoryLayout.getIcon(),
                activity.getString(R.string.transition_background));
        View toolbar = getActivity().findViewById(R.id.toolbar_player);
        if (null != toolbar) {
            participants[1] = new Pair<View, String>(
                    toolbar.findViewById(R.id.avatar),
                    activity.getString(R.string.transition_avatar));
        }
        return participants;
    }
}