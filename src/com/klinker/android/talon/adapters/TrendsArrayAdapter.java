package com.klinker.android.talon.adapters;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.klinker.android.talon.R;
import com.klinker.android.talon.settings.AppSettings;
import com.klinker.android.talon.ui.drawer_activities.trends.SearchedTrendsActivity;

import java.util.ArrayList;

import twitter4j.User;

/**
 * Created by luke on 11/27/13.
 */
public class TrendsArrayAdapter extends ArrayAdapter<User> {

    private Context context;

    private ArrayList<String> text;

    private LayoutInflater inflater;
    private AppSettings settings;

    public static class ViewHolder {
        public TextView text;
    }

    public TrendsArrayAdapter(Context context, ArrayList<String> text) {
        super(context, R.layout.tweet);

        this.context = context;
        this.text = text;

        settings = new AppSettings(context);
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return text.size();
    }

    public View newView(ViewGroup viewGroup) {
        View v;
        final ViewHolder holder;

        v = inflater.inflate(R.layout.text, viewGroup, false);

        holder = new ViewHolder();

        holder.text = (TextView) v.findViewById(R.id.text);

        // sets up the font sizes
        holder.text.setTextSize(24);

        v.setTag(holder);
        return v;
    }

    public void bindView(final View view, Context mContext, final String trend) {
        final ViewHolder holder = (ViewHolder) view.getTag();

        holder.text.setText(trend);

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(context, SearchedTrendsActivity.class);
                search.setAction(Intent.ACTION_SEARCH);
                search.putExtra(SearchManager.QUERY, trend);
                context.startActivity(search);
            }
        });

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;
        if (convertView == null) {

            v = newView(parent);

        } else {
            v = convertView;

            final ViewHolder holder = (ViewHolder) v.getTag();
        }

        bindView(v, context, text.get(position));

        return v;
    }
}
