package com.example.smartcitytraveller;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterForVenueCategory extends RecyclerView.Adapter<AdapterForVenueCategory.ViewHolder>{
    private final ArrayList<Pair<String, Integer>> categoryList;
    private static ClickListener clickListener;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvVenueCategory;
        private final ImageView ivVenueCategoryImage;

        public ViewHolder(View view) {
            super(view);

            tvVenueCategory = (TextView) view.findViewById(R.id.venueCategory);
            ivVenueCategoryImage = (ImageView) view.findViewById(R.id.venueCategoryImage);

            // Define click listener for the ViewHolder's View
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }

        public TextView getTvVenueCategory() {
            return tvVenueCategory;
        }
        public ImageView getIvVenueCategoryImage() {
            return ivVenueCategoryImage;
        }


    }

    ///////////////////////////////////////////
    public interface ClickListener{
        void onItemClick(int position, View view);
    }

    public void setOnItemClickListener(ClickListener clickListener){
        AdapterForVenueCategory.clickListener = clickListener;
    }
    ///////////////////////////////////////////////

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet ArrayList<Pair<String, String>> containing the data to populate views to be used
     * by RecyclerView.
     */
    public AdapterForVenueCategory(ArrayList<Pair<String, Integer>> dataSet) {
        categoryList = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.venue_category_row_item, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTvVenueCategory().setText(categoryList.get(position).first);      // for category name eg: food, hospital
        viewHolder.getIvVenueCategoryImage().setImageResource(categoryList.get(position).second); // for category image eg: food image

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
