package com.example.dell.newscenter.myview.mainactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.activity.MainActivity;
import com.example.dell.newscenter.utils.FTPUtil;
import com.example.dell.newscenter.utils.GetPathFromUri;
import com.example.dell.newscenter.utils.PermissionUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.OnTextChanged;

public class PublicationDynamicsActivity extends AppCompatActivity {
private static final String TAG = "PublicationDynamicsActi";
public  static  final  int TAKE_PHOTO  = 1;
public  static  final int  VIDEO_REQUEST_CODE = 2;
private Button addPhotoBtn = null;
private Button addVideoBtn = null;
private EditText addTitleET = null;
private EditText addDescribeET = null;
private ImageView photo = null;
private VideoView video = null;
private RelativeLayout videoLayout = null;
private Uri imageUri = null;
private Uri videoUri = null;
private Button publishBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_dynamics);
        addPhotoBtn = findViewById(R.id.addPhotoBtn);
        addVideoBtn = findViewById(R.id.addVideoBtn);
        addTitleET = findViewById(R.id.addTitleET);
        addDescribeET = findViewById(R.id.addDescribeET);
        photo = findViewById(R.id.photo);
        video = findViewById(R.id.video);
        videoLayout = findViewById(R.id.videoLayout);
        publishBtn = findViewById(R.id.publishBtn);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = checkInfo();
                res=null;// 先关闭检查
                if (res == null) {
                    //  开始发表
                    /**
                     *  1 、上传图片
                     *  2、上传视频
                     *  3、更改数据库
                     */
                   String path = GetPathFromUri.getPath(PublicationDynamicsActivity.this,imageUri);
                    Log.d(TAG, "path: "+path);
                   int i = path.lastIndexOf("/");
                    String localPath = path.substring(0,i);
                    String fileName = path.substring(i+1);
                    Log.d(TAG, "path: "+path);
                    Log.d(TAG, "localPath: "+localPath);
                    Log.d(TAG, "fileName: "+fileName);
                    //  1、
                    FTPUtil.uploadFtpFile(FTPUtil.FTP_IMAGE_PATH,fileName, localPath, fileName, new FTPUtil.FTPCallBack() {
                        @Override
                        public void callBack() {
                            String path = GetPathFromUri.getPath(PublicationDynamicsActivity.this,imageUri);
                            Log.d(TAG, "videoUri.getPath() "+path);
                            int i = path.lastIndexOf("/");
                            String localPath = path.substring(0,i);
                            String fileName = path.substring(i+1);
                            Log.d(TAG, "localPath: "+localPath);
                            Log.d(TAG, "fileName: "+fileName);
                    //  2、
                            FTPUtil.uploadFtpFile(FTPUtil.FTP_VIDEO_PATH,fileName, localPath, fileName, new FTPUtil.FTPCallBack() {
                                @Override
                                public void callBack() {
                     //   3、              //    写入数据库

                                }
                            });
                        }
                    });
                }else {
                    Toast.makeText(PublicationDynamicsActivity.this,res,Toast.LENGTH_SHORT).show();
                }
            }
        });

        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  AlertDialog.Builder(PublicationDynamicsActivity.this)
                        .setTitle("请选择" )
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(new  String[] {"拍照", "相册选取" },  0 ,
                                new  DialogInterface.OnClickListener() {
                                    public   void  onClick(DialogInterface dialog,  int  which) {
                                        Log.d(TAG, "onClick: "+which);
                                        switch (which){
                                            case 0: //拍照
                                                 if (PermissionUtil.requestCamera(PublicationDynamicsActivity.this)){
                                                    shootPhoto();
                                                }
                                                break;
                                            case 1: // 相册选取
                                                selectPhoto();
                                                break;
                                        }
                                        dialog.dismiss();
                                    }
                                }
                        )
                        .setNegativeButton("取消" ,  null )
                        .show();
            }
        });

        addVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(photo.getVisibility()==View.INVISIBLE){
                    Toast.makeText(PublicationDynamicsActivity.this,"请先添加封面",Toast.LENGTH_SHORT).show();
                    return;
                }
                new  AlertDialog.Builder(PublicationDynamicsActivity.this)
                        .setTitle("请选择" )
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(new  String[] {"拍摄", "文件选取" },  0 ,
                                new  DialogInterface.OnClickListener() {
                                    public   void  onClick(DialogInterface dialog,  int  which) {
                                        Log.d(TAG, "onClick: "+which);
                                        switch (which){
                                            case 0: //拍摄
                                                if (PermissionUtil.requestCamera(PublicationDynamicsActivity.this)){
                                                    shootVideo();
                                                }
                                                break;
                                            case 1: // 文件选取
                                                    selectVideo();
                                                break;
                                        }
                                        dialog.dismiss();
                                    }
                                }
                        )
                        .setNegativeButton("取消" ,  null )
                        .show();
            }
        });

        final int maxNum = 50;
        final TextView leftNum = (TextView) findViewById(R.id.leftNum);
        addDescribeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                leftNum.setText("剩余字数："+ (maxNum-s.length()));
            }
        });


    }

    private String checkInfo() {
        Log.d(TAG, "checkInfo: imageUri"+imageUri.getPath());
        Log.d(TAG, "checkInfo: videoUri"+videoUri.getPath());

        if (imageUri==null){
            return "请选择封面";
        }else if (videoUri==null){
            return "请选择视频";
        }else  if (addTitleET.getText().toString().trim().equals("")){
            return "请输入标题";
        }else if (addDescribeET.getText().toString().trim().equals("")){
            return  "请填写描述信息";
        }
        return null;
    }

    public  void  getImageUri(){
        File outputImage = new File(getExternalCacheDir(),"image.jpg");
        try {
            if (outputImage.exists())outputImage.delete();
            outputImage.createNewFile();
        } catch (IOException e) {
            Log.d(TAG, " IOException");
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(PublicationDynamicsActivity.this,"Joy",outputImage);
        }else {
            imageUri = Uri.fromFile(outputImage);
        }
    }
    public  void selectPhoto(){
            getImageUri();
        //Intent intent=new Intent(Intent.ACTION_PICK);
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,TAKE_PHOTO);
    }
    public  void  shootPhoto(){
        getImageUri();
        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);//指定拍摄后的图片存储的uri  指定后data.getData()  为null
        startActivityForResult(intent,TAKE_PHOTO);
    }
    public void selectVideo(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, VIDEO_REQUEST_CODE);

    }
    public  void  shootVideo(){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,30);//时长
//                intent.putExtra(MediaStore.EXTRA_MEDIA_ARTIST,1);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);//画质
        startActivityForResult(intent,VIDEO_REQUEST_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: ");

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case  TAKE_PHOTO:
                Log.d(TAG, "requestCode: "+requestCode);
                Log.d(TAG, "RESULT_OK: "+RESULT_OK);

                if (resultCode==RESULT_OK){
                    Uri u = null;
                    if ((u = data.getData())!=null){ //不等于null 就是从相册获取
                        imageUri = u;
                    }
                    Log.d(TAG, " imageUri "+ imageUri.getEncodedPath());
                    Log.d(TAG, " imageUri "+ imageUri.getPath());

                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        Log.d(TAG, "bitmap: "+bitmap);

                        Glide.with(PublicationDynamicsActivity.this)
                                .load(imageUri).fitCenter().into(photo);
//                        photo.setImageBitmap(bitmap);
                        photo.setVisibility(View.VISIBLE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case  VIDEO_REQUEST_CODE:
                if (resultCode == RESULT_OK){
                    videoUri = data.getData();
                    Log.d(TAG, "onActivityResult: "+videoUri.toString());
                    video.setVideoURI(videoUri);
                    video.start();
                    // 完成后继续播放来循环
                    video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            video.start();
                        }
                    });
                    video.setVisibility(View.VISIBLE);
                    videoLayout.setVisibility(View.VISIBLE);
                }
                break;

            default: break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PermissionUtil.REQUEST_CAMERA :
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 请求成功
                    shootPhoto();
                } else {
                    // 权限请求失败的操作
                    Toast.makeText(PublicationDynamicsActivity.this,"请求摄像头权限被拒绝",Toast.LENGTH_SHORT).show();
                }
                    break;
        }
    }


    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
