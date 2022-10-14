package com.example.postsapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postsapplication.data.model.PostResponseItem;
import com.example.postsapplication.data.source.remote.RetrofitClient;
import com.example.postsapplication.databinding.FragmentPostsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsFragment extends Fragment implements PostsAdapter.PostAction {

    private FragmentPostsBinding binding;
    private PostsAdapter postsAdapter;

    public PostsFragment() {
    }

    private void initUI(){
        postsAdapter = new PostsAdapter();
        binding.recyclerView.setAdapter(postsAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

private void getPosts() {

    RetrofitClient.getService()
            .getposts().enqueue(new Callback<List<PostResponseItem>>() {
                @Override
                public void onResponse(Call<List<PostResponseItem>> call, Response<List<PostResponseItem>> response) {
                    if (response.isSuccessful())
                        postsAdapter.addPosts(response.body(),PostsFragment.this);

                }

                @Override
                public void onFailure(Call<List<PostResponseItem>> call, Throwable t) {
                    Log.d("dddd", "onFailure: " + t.getLocalizedMessage());

                }
            });
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPostsBinding.bind(view);
        initUI();
        getPosts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void postClick(PostResponseItem post) {

        Navigation.findNavController(getView())
                .navigate(PostsFragmentDirections.actionPostsFragmentToPostsDetailsFragment(post.getId()));
    }
}

