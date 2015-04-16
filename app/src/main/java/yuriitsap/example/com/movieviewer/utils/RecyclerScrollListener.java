package yuriitsap.example.com.movieviewer.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by yuriitsap on 15.04.15.
 */
public abstract class RecyclerScrollListener extends RecyclerView.OnScrollListener {

    private boolean mIsLoading = false;
    private int mCurrentPage;

    protected RecyclerScrollListener(int currentPage) {
        mCurrentPage = currentPage;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                .getLayoutManager();
        if (mCurrentPage == ((MovieAdapter) recyclerView.getAdapter()).getPagesLoaded()) {
            mIsLoading = false;
        }

        if (linearLayoutManager.getChildCount() + linearLayoutManager
                .findFirstVisibleItemPosition() == linearLayoutManager.getItemCount()
                && !mIsLoading) {
            Log.e("TAG", "entered load");
            mCurrentPage++;
            loadMovies(mCurrentPage);
            mIsLoading = true;
        }
    }

    public abstract void loadMovies(int page);
}
