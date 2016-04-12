package com.tong.bookstore.bookstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tong.bookstore.R;
import com.tong.bookstore.database.DataOperator;
import com.tong.bookstore.database.SQLiteHelper;
import com.tong.bookstore.service.BookService;
import com.tong.bookstore.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tong.zhang on 2016/3/11.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {

    private List<BookBean> mDataList = null;
    private Context context = null;

    public MyRecycleViewAdapter(Context context) {
        mDataList = new ArrayList<BookBean>();
        this.context = context;
    }

    public void setData(List<BookBean> list) {
        if (list != null && list.size() != 0) {
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BookBean bookBean = mDataList.get(position);
        holder.binder(bookBean);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView desc;
        private View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            desc = (TextView) itemView.findViewById(R.id.text_view);
            this.itemView = itemView;
        }

        public void binder(final BookBean bookBean) {
            imageView.setImageResource(bookBean.imageId);
            desc.setText(bookBean.desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(v.getContext(), bookBean.desc);
                    new DataOperator(SQLiteHelper.getDB(v.getContext().getApplicationContext())).insert(bookBean.imageId, bookBean.desc);
                    BookService.startBookService(v.getContext().getApplicationContext(), bookBean.desc);
                }
            });

        }
    }


    public static class BookBean {
        public int imageId;
        public String desc;
    }
}
