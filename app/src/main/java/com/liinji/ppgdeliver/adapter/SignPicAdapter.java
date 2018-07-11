package com.liinji.ppgdeliver.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.bumptech.glide.Glide;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.activity.WaitSendDetailActivity;
import com.liinji.ppgdeliver.bean.SignPic;
import com.liinji.ppgdeliver.tools.TakePhotoTools;
import com.liinji.ppgdeliver.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by pig on 2017/12/14.
 */

public class SignPicAdapter extends RecyclerView.Adapter<SignPicAdapter.ViewHolder> {
    private RecyclerView mRecyclerView;
    private List<SignPic> mPicPath = new ArrayList<>();

    public SignPicAdapter(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_pic_item, parent, false);
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
            String localPath = mPicPath.get(position).getLocalPath();
            String realPath = TextUtils.isEmpty(localPath) ? mPicPath.get(position).getRemoteUrl() : localPath;

            Glide.with(holder.itemView.getContext()).load(realPath).placeholder(R.drawable.yx_icon_default).into(holder.mPic);

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

    public void add(SignPic signPic) {
        mPicPath.add(signPic);
        notifyDataSetChanged();
    }

    public void add(List<SignPic> data) {
        mPicPath.addAll(data);
        notifyDataSetChanged();
    }

    public void onExit() {
        if (mRecyclerView != null){
            ((SignPicAdapter) mRecyclerView.getAdapter()).mRecyclerView = null;
            mRecyclerView = null;
        }

        if (mPicPath != null) {
            mPicPath.clear();
            mPicPath = null;
        }

    }

    public List<SignPic> getData() {
        return mPicPath;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView mRecyclerView;
        public ImageView mPic;
        public ImageView mDelete;

        public PopupWindow mSelectMethod;
        public ListView mMethodContent;

        public ViewHolder(View itemView, final RecyclerView recyclerView) {
            super(itemView);
            mRecyclerView = recyclerView;
            mPic = (ImageView) itemView.findViewById(R.id.pic_imgv);
            mDelete = (ImageView) itemView.findViewById(R.id.delete_imgv);
            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(v.getContext())
                            .setTitleText("提示")
                            .setContentText("是否删除图片？")
                            .setConfirmText("确定")
                            .setCancelText("取消")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    ((WaitSendDetailActivity) recyclerView.getContext()).onDeleteClick(getAdapterPosition());
                                }
                            });
                    Utils.processDialog(sweetAlertDialog);

                    sweetAlertDialog.show();

                }
            });

            mPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == mRecyclerView.getAdapter().getItemCount() - 1) {
                        select();
                    }else {
                        List<String> preList = ((SignPicAdapter) mRecyclerView.getAdapter()).getPreViewList();
                        //使用方式
                        PictureConfig config = new PictureConfig.Builder()
                                .setListData((ArrayList<String>) preList)	//图片数据List<String> list
                                .setPosition(getAdapterPosition())	//图片下标（从第position张图片开始浏览）
                                .setDownloadPath("pictureviewer")	//图片下载文件夹地址
                                .setIsShowNumber(true)//是否显示数字下标
                                .needDownload(true)	//是否支持图片下载
                                .setPlacrHolder(R.drawable.yx_icon_default)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                                .build();
                        ImagePagerActivity.startActivity(mRecyclerView.getContext(), config);
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

                                ((WaitSendDetailActivity) mRecyclerView.getContext()).setOutPutFileUri(uri);


                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                ((WaitSendDetailActivity) itemView.getContext()).startActivityForResult(intent, REQ_CAMERA);
                                break;
                            case 1:
                                TakePhotoTools.toGetPhotoMultiple(itemView.getContext(), REQ_SELECT, 1, 1, 200, 300, 4 - mRecyclerView.getAdapter().getItemCount());
                                break;
                        }
                    }
                });
                mSelectMethod = new PopupWindow(content, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ((WaitSendDetailActivity) content.getContext()).setSelectMethod(mSelectMethod);
                List names = new ArrayList(2);
                names.add("拍照");
                names.add("从相册中选取");
                mMethodContent.setAdapter(new ArrayAdapter<String>(mRecyclerView.getContext(), R.layout.my_simple, names));
            }

            mSelectMethod.showAtLocation(mRecyclerView, Gravity.NO_GRAVITY, 0, 0);
        }


    }

    private List<String> mPreList = new ArrayList<>(3);
    public List<String> getPreViewList(){

        mPreList.clear();

        for (int index = 0;index < mPicPath.size();index++){

            String localPath = mPicPath.get(index).getLocalPath();
            String remotePath = mPicPath.get(index).getRemoteUrl();
            String path = TextUtils.isEmpty(localPath) ? remotePath : localPath;

            mPreList.add(path);
        }

        return mPreList;
    }


}
