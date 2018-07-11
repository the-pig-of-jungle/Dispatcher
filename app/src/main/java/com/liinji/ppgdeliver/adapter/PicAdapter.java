package com.liinji.ppgdeliver.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.activity.ProblemCopyActivty;
import com.liinji.ppgdeliver.tools.TakePhotoTools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 朱志强 on 2017/7/14.
 */

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {
    private RecyclerView mRecyclerView;
    private List<String> mPicPath = new ArrayList<>();

    public PicAdapter(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_pic_item, parent, false);
        return new ViewHolder(itemView, mRecyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            if (position > 2) {
                holder.itemView.setVisibility(View.GONE);
            } else {
                holder.itemView.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView.getContext()).load(R.drawable.yx_icon_upload_photo).into(holder.mPic);
                holder.mDelete.setVisibility(View.GONE);
            }
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.mDelete.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView.getContext()).load(mPicPath.get(position)).into(holder.mPic);
        }
    }

    @Override
    public int getItemCount() {
        return mPicPath.size() + 1;
    }

    public int getRealCount() {
        return mPicPath.size();
    }

    public void remove(int pos) {
        mPicPath.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, getItemCount() - pos);
    }

    public void add(String path) {
        mPicPath.add(path);
        notifyDataSetChanged();
    }

    public void add(List<String> data) {
        mPicPath.addAll(data);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView mRecyclerView;
        public ImageView mPic;
        public ImageView mDelete;

        public PopupWindow mSelectMethod;
        public ListView mMethodContent;

        public ViewHolder(View itemView, RecyclerView recyclerView) {
            super(itemView);
            mRecyclerView = recyclerView;
            mPic = (ImageView) itemView.findViewById(R.id.pic_imgv);
            mDelete = (ImageView) itemView.findViewById(R.id.delete_imgv);
            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PicAdapter) mRecyclerView.getAdapter()).remove(getAdapterPosition());
                }
            });

            mPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == mRecyclerView.getAdapter().getItemCount() - 1) {
                        select();
                    }
                }
            });
        }


        public static final int REQ_CAMERA = 0x4598;
        public static final int REQ_SELECT = 0x4597;

        private void select() {

            if (mSelectMethod == null) {
                ViewGroup content = (ViewGroup) LayoutInflater.from(mRecyclerView.getContext()).inflate(R.layout.select_method, new LinearLayout(mRecyclerView.getContext()), false);
                mMethodContent = (ListView) content.findViewById(R.id.list_view);
                mMethodContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mSelectMethod.dismiss();
                        switch (position) {
                            case 0:
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                //文件夹aaaa
                                String path = Environment.getExternalStorageDirectory().toString() + "/aaaa";
                                File path1 = new File(path);
                                if (!path1.exists()) {
                                    path1.mkdirs();
                                }
                                File file = new File(path1, System.currentTimeMillis() + ".jpg");
                                Uri uri = Uri.fromFile(file);
                                ((ProblemCopyActivty) mRecyclerView.getContext()).setOutPutFileUri(uri);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                ((ProblemCopyActivty) itemView.getContext()).startActivityForResult(intent, REQ_CAMERA);
                                break;
                            case 1:
                                TakePhotoTools.toGetPhotoMultiple(itemView.getContext(), REQ_SELECT, 1, 1, 200, 300, 4 - mRecyclerView.getAdapter().getItemCount());
                                break;
                        }
                    }
                });
                mSelectMethod = new PopupWindow(content, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ((ProblemCopyActivty) content.getContext()).setSelectMethod(mSelectMethod);
                List names = new ArrayList(2);
                names.add("拍照");
                names.add("从相册中选取");
                mMethodContent.setAdapter(new ArrayAdapter<String>(mRecyclerView.getContext(), R.layout.my_simple, names));
            }

            mSelectMethod.showAtLocation(mRecyclerView, Gravity.NO_GRAVITY, 0, 0);
        }


    }


}
