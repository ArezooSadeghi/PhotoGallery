package com.example.photogallery.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photogallery.R;
import com.example.photogallery.model.GalleryItem;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryFragment extends Fragment {

    private static final int SPAN_COUNT = 3;
    private RecyclerView mRecyclerView;

    public PhotoGalleryFragment() {

    }

    public static PhotoGalleryFragment newInstance() {
        PhotoGalleryFragment fragment = new PhotoGalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        findViews(view);
        initViews();
        setupAdapter();

        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_photo_gallery);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
    }

    private void setupAdapter() {
        List<GalleryItem> items = new ArrayList<>();
        PhotoAdapter adapter = new PhotoAdapter(items);
        mRecyclerView.setAdapter(adapter);
    }

    private class PhotoHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private GalleryItem mItem;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.txt_photo_title);
        }

        public void bindGalleryItem(GalleryItem item) {
            mItem = item;
            mTextView.setText(item.getTitle());
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        private List<GalleryItem> mItems;

        public PhotoAdapter(List<GalleryItem> items) {
            mItems = items;
        }

        public List<GalleryItem> getItems() {
            return mItems;
        }

        public void setItems(List<GalleryItem> items) {
            mItems = items;
        }

        @NonNull
        @Override
        public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.gallery_item_detail, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
            holder.bindGalleryItem(mItems.get(position));
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}