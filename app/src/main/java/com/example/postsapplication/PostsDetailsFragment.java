package com.example.postsapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postsapplication.data.model.PostResponseItem;
import com.example.postsapplication.data.source.remote.RetrofitClient;
import com.example.postsapplication.databinding.FragmentPostsDetailsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsDetailsFragment extends Fragment {

   FragmentPostsDetailsBinding binding;

    public PostsDetailsFragment() {
    }

  private void bindUI(PostResponseItem post) {
        binding.postId.setText(String.valueOf(post.getId()));
        binding.postUserId.setText(String.valueOf(post.getUserId()));
        binding.postTitle.setText(post.getTitle());
        binding.postDescription.setText(post.getBody());
}

    private void getPosts( int postId) {

        RetrofitClient.getService().getPost(postId)
                .enqueue(new Callback<PostResponseItem>() {
                    @Override
                    public void onResponse(Call<PostResponseItem> call, Response<PostResponseItem> response) {
                        if (response.isSuccessful())
                            bindUI(response.body());
                    }
                    @Override
                    public void onFailure(Call<PostResponseItem> call, Throwable t) {
                        Log.d("dddddd", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }

    PostResponseItem post;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int postId = PostsDetailsFragmentArgs.fromBundle(getArguments()).getPostId();

        getPosts(postId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentPostsDetailsBinding.bind(view);

        if (post !=null )
            bindUI(post);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
