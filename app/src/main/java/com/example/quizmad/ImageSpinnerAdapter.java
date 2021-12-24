package com.example.quizmad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ImageSpinnerAdapter extends ArrayAdapter<String> {


    private Context context;
    private List<String> contentArray;
    private List<Integer> imageArray;


    public ImageSpinnerAdapter(@NonNull Context context , List<String> contentArray , List<Integer> imageArray) {
        super(context, R.layout.spinner_view,contentArray);
        this.context = context;
        this.imageArray = imageArray;
        this.contentArray = contentArray;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position , convertView,parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position , convertView,parent);
    }

    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView= inflater.inflate(R.layout.spinner_view , parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.spinner_name);
        textView.setText(contentArray.get(position));

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        imageView.setImageResource(imageArray.get(position));

        return rowView;
    }
}
