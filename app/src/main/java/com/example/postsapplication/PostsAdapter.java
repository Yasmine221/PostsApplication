package com.example.postsapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsapplication.data.model.PostResponseItem;
import com.example.postsapplication.databinding.ItemPostLayoutBinding;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostHolder> {

    private List<PostResponseItem>posts;
    private PostAction postAction;

    public void addPosts(List<PostResponseItem>posts, PostAction postAction){
        this.posts=posts;
        this.postAction=postAction;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemPostLayoutBinding binding = ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.binding.postId.setText(String.valueOf(posts.get(position).getId()));
        holder.binding.postTitle.setText(posts.get(position).getTitle());

    }

    @Override
    public int getItemCount() {

        if (posts != null)
            return posts.size();

        return 0;
    }

    class PostHolder extends RecyclerView.ViewHolder {

        ItemPostLayoutBinding binding;

        public PostHolder(ItemPostLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postAction.postClick(posts.get(getLayoutPosition()));
                }
            });
        }
    }
    interface PostAction {

        void postClick(PostResponseItem post);
    }
}
