package com.mvp.base.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.mikepenz.material_design_iconic_typeface_library
        .MaterialDesignIconic;


import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.VideoInfo;
import com.mvp.base.model.bean.VideoType;
import com.mvp.base.model.bean.WorkmateBean;
import com.mvp.base.presenter.VideoInfoPresenter;
import com.mvp.base.presenter.contract.MineContract;
import com.mvp.base.ui.activitys.CollectionActivity;
import com.mvp.base.ui.activitys.HistoryActivity;
import com.mvp.base.ui.activitys.MainActivity;
import com.mvp.base.ui.activitys.SettingActivity;
import com.mvp.base.ui.adapter.MineHistoryVideoListAdapter;
import com.mvp.base.ui.fragments.MineFragment;
import com.mvp.base.utils.BeanUtil;
import com.mvp.base.utils.EventUtil;
import com.mvp.base.utils.GsonUtil;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.ScreenUtil;
import com.mvp.base.utils.StringUtils;
import com.mvp.base.widget.theme.ColorTextView;
import com.sina.cloudstorage.auth.AWSCredentials;
import com.sina.cloudstorage.auth.BasicAWSCredentials;
import com.sina.cloudstorage.services.scs.SCS;
import com.sina.cloudstorage.services.scs.SCSClient;
import com.sina.cloudstorage.services.scs.model.PutObjectResult;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mvp.base.R.id.recyclerView;
import static com.mvp.base.R.id.riv_avatar;
import static com.mvp.base.R.id.swipe_deck;


/**
 * Description: MineView
 * Creator: cp
 * date: 2016/9/29 12:16
 */
public class MineView extends RootView<MineContract.Presenter> implements
        MineContract.View {

    MineHistoryVideoListAdapter mAdapter;
    VideoInfo videoInfo;
    @BindView(R.id.title_name)
    ColorTextView titleName;
    @BindView(R.id.rl_them)
    RelativeLayout rlThem;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(recyclerView)
    EasyRecyclerView mRecyclerView;
    @BindView(R.id.tv_history)
    TextView mTvHistory;
    @BindView(R.id.tv_down)
    TextView tvDown;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.tv_them)
    TextView tvThem;
    ImageView iv_avatar;
    Button picture;
    Button photo;
    Button back;
    Dialog dialog;

    public MineView(Context context) {
        super(context);
    }

    public MineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.fragment_mine_view, this);
    }

    @Override
    protected void initView() {
       iv_avatar= (ImageView) findViewById(R.id.uc_avater);
        ((AppCompatActivity) getContext()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        titleName.setText(getResources().getString(R.string.mine_title));
        StringUtils.setIconDrawable(mContext, mTvHistory,
                MaterialDesignIconic.Icon.gmi_account_calendar, 16, 15);
        StringUtils.setIconDrawable(mContext, tvDown, MaterialDesignIconic
                .Icon.gmi_time_countdown, 16, 15);
        StringUtils.setIconDrawable(mContext, tvCollection,
                MaterialDesignIconic.Icon.gmi_collection_bookmark, 16, 15);
        StringUtils.setIconDrawable(mContext, tvThem, MaterialDesignIconic
                .Icon.gmi_palette, 16, 15);
        mRecyclerView.setAdapter(mAdapter = new MineHistoryVideoListAdapter
                (mContext));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,
                3);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp
                (3));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil
                .dip2px(mContext, 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);
        ImageLoader.load(mContext, PreUtils.getString(getContext(),"avatar", ""), iv_avatar);
        iv_avatar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
  }

    @Override
    protected void initEvent() {
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter
                .OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                videoInfo = BeanUtil.VideoType2VideoInfo(mAdapter.getItem
                        (position), videoInfo);
            }
        });
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<VideoType> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
        if (list.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void postSuccess() {

    }

    @Override
    public void postFailed() {

    }


    @OnClick({R.id.rl_record, R.id.rl_down, R.id.rl_collection, R.id.rl_them,
            R.id.img_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_record:
                getContext().startActivity(new Intent(mContext,
                        HistoryActivity.class));
                break;
            case R.id.rl_down:
                EventUtil.showToast(getContext(), "敬请期待");
                break;
            case R.id.rl_collection:
                getContext().startActivity(new Intent(mContext,
                        CollectionActivity.class));
                break;
            case R.id.rl_them:
                EventBus.getDefault().post("", MineFragment.SET_THEME);
                break;
            case R.id.img_setting:
                getContext().startActivity(new Intent(mContext,
                        SettingActivity.class));
                break;
        }
    }

    @Subscriber(tag = VideoInfoPresenter.Refresh_History_List)
    public void setData(String tag) {
        mPresenter.getHistoryData();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }

    private void showDialog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout
                .photo_choose_dialog, null);
        dialog = new Dialog(getContext(), R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup
                .LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        final WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 1280;
//        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        picture = (Button) view.findViewById(R.id.picture);
        photo = (Button) view.findViewById(R.id.photo);
        back = (Button) view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        picture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mContext instanceof MainActivity) {
                    CompressConfig compressConfig = new CompressConfig
                            .Builder().setMaxPixel(320).create();
                    ((MainActivity) mContext).getTakePhoto().onEnableCompress
                            (compressConfig, true);
                    ((MainActivity) mContext).getTakePhoto()
                            .onPickFromGallery();
                }
                dialog.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mContext instanceof MainActivity) {
                    File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".");
                    if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                    Uri imageUri = Uri.fromFile(file);
//                    CropOptions compressConfig = new CropOptions
//                            .Builder().setMaxPixel(320).create();
//                    ((MainActivity) mContext).getTakePhoto().onEnableCompress
//                            (compressConfig, true);
                    ((MainActivity) mContext).getTakePhoto().onPickFromCapture(imageUri);
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private String compressPath = "";
    private String originalPath = "";

    @Subscriber(tag = MainActivity.UPDATE_AVATAR)
    public void updateAvatar(TResult result) {

        compressPath = result.getImage().getCompressPath() ;
        originalPath = result.getImage().getOriginalPath() ;
        ImageLoader.load(mContext, originalPath, iv_avatar);

        WorkmateBean bean = new WorkmateBean();
        if(!TextUtils.isEmpty(compressPath)) {
            bean.setAvatar("http://cdn.sinacloud.net/diancai/dish/" + compressPath.substring(compressPath.lastIndexOf("/") + 1));
            putObjectWithCustomRequestHeader(compressPath, bean);
        }


    }


    String accessKey = "18fj8yd7wTMClAV82gph";
    String secretKey = "d6e28b0c3631d16901a2a6cbeb29ecf6bff40193";
    AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    SCS conn = new SCSClient(credentials);
    /**
     * 上传文件 自定义请求头
     */
    public void putObjectWithCustomRequestHeader(final String filepath, final WorkmateBean bean){
        new Thread(){
            public void run(){

                Map<String, String> headers = new HashMap<String, String>();
                headers.put("x-amz-acl", "public-read");
                PutObjectResult putObjectResult = conn.putObject("diancai",
                        "dish/"+filepath.substring(filepath.lastIndexOf("/")+1),
                        new File(filepath), headers);
                System.out.println(putObjectResult);//服务器响应结果

                if(putObjectResult != null && !TextUtils.isEmpty(putObjectResult.getContentMd5())){
                    updateWorkmateBean(bean);
                }


            }
        }.start();

    }

    public void updateWorkmateBean(WorkmateBean bean){
        mPresenter.updateMyInfo(mContext, GsonUtil.getJson(bean));
    }
}
