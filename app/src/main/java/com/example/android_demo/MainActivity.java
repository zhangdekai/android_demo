package com.example.android_demo;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.CamcorderProfile;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;

//import com.example.android_demo.share.ShareActivity;
//import com.example.android_demo.video_dismension.VideoDimension;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.android_demo.databinding.ActivityMainBinding;

import android.util.Log;
import android.util.Range;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show());

        setTextViewText();

        buttonClick();

        buttonClick1();
        buttonClick2();
    }

    void setTextViewText() {
        TextView textView = findViewById(R.id.textview_first);

        textView.setText("你好，安卓的世界");
        textView.setTextSize(20);
        textView.setTextColor(0xffff0000);
        textView.setTypeface(null, Typeface.BOLD);
    }



    void buttonClick() {
        Button button = findViewById(R.id.button_second);
        button.setOnClickListener(view -> {

            List<String> list = dissensionValue();

            String temp = String.join(",", list);

            Log.d("d10", "33333支持的视频尺寸4： supportedResolutions" + temp);

//            final  List<String> list = getSupportedResolutions();
//
//            Log.d("d4", "list" + list.size());
//
//
//            final  List<String> list1 = getSupportedCamcorderResolutions();
//            Log.d("d5", "list1 - " + list1.size());

//            Intent intent = new Intent();
//            intent .setClass(MainActivity.this, MainActivity2.class);
//            startActivity(intent);
        });
    }

    void buttonClick1() {
        Button button = findViewById(R.id.button_third);
        button.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent .setClass(MainActivity.this, MainActivity3.class);
            startActivity(intent);
        });
    }

    void buttonClick2() {
        Button button = findViewById(R.id.button_fourth);
        button.setOnClickListener(view -> {

//            VideoDimension.dissensionValue();

            dissensionValue();

          final  List<String> list = getSupportedResolutions();

          Log.d("d4", "list" + list.size());


            final  List<String> list1 = getSupportedCamcorderResolutions();
            Log.d("d5", "list1 - " + list1.size());

//            Intent intent = new Intent();
//            intent.setClass(MainActivity.this, ShareActivity.class);
//            startActivity(intent);
        });
    }

    public static List<String> getSupportedCamcorderResolutions() {
        List<String> supportedResolutions = new ArrayList<>();
        int[] qualities = {
                CamcorderProfile.QUALITY_LOW,
                CamcorderProfile.QUALITY_HIGH,
                CamcorderProfile.QUALITY_480P,
                CamcorderProfile.QUALITY_720P,
                CamcorderProfile.QUALITY_1080P,
                CamcorderProfile.QUALITY_2160P,
        };

        for (int quality : qualities) {
            if (CamcorderProfile.hasProfile(quality)) {
                CamcorderProfile profile = CamcorderProfile.get(quality);
                String resolution = profile.videoFrameWidth + "x" + profile.videoFrameHeight;
                Log.d("d5", "resolution: " + resolution);
                supportedResolutions.add(resolution);
            }
        }

        return supportedResolutions;
    }

    public static List<String> dissensionValue() {

        final MediaCodecList codecInfoList = new MediaCodecList(MediaCodecList.REGULAR_CODECS);

        MediaCodecInfo[] codecInfos = codecInfoList.getCodecInfos();

        List<String> supportedResolutions = new ArrayList<String>();
        for (MediaCodecInfo codecInfo : codecInfos) {
            if (!codecInfo.isEncoder()) {
               String dimension =  getSupportVideoDimension(getVideoCapabilities(codecInfo));

               if(!dimension.isEmpty() && !supportedResolutions.contains(dimension)) {
                   supportedResolutions.add(dimension);
               }

                Log.d("d7", "支持的视频尺寸： dimension - " + dimension);

            }
        }

        Log.d("d3", "33333支持的视频尺寸： supportedResolutions" + supportedResolutions.size());

        return supportedResolutions;

    }

    private static String getSupportVideoDimension(MediaCodecInfo.VideoCapabilities videoCapabilities) {

        if(videoCapabilities != null) {
            final Range<Integer> supportedWidths = videoCapabilities.getSupportedWidths();
            final int widthLower =  supportedWidths.getLower();
            final int widthUpper =  supportedWidths.getUpper();

            final Range<Integer> supportedHeights = videoCapabilities.getSupportedHeights();
            final int heightLower = supportedHeights.getLower();
            final int heightUpper = supportedHeights.getUpper();

            Log.d("d6", "支持的视频尺寸1： width - " + widthLower + "x" + widthUpper);
            Log.d("d6", "支持的视频尺寸1： height - " + heightLower + "x" + heightUpper);


            Log.d("d3", "支持的视频尺寸1： lower - " + widthLower + "x" + heightLower);
            Log.d("d3", "支持的视频尺寸1： upper - " + widthUpper + "x" + heightUpper);

            return widthUpper + "x" + heightUpper;
        }
        return  "";
    }


    public static List<String> getSupportedResolutions() {

        List<String> supportedResolutions = new ArrayList<>();
        MediaCodecList codecList = new MediaCodecList(MediaCodecList.ALL_CODECS);
        MediaCodecInfo[] codecInfos = codecList.getCodecInfos();

        for (MediaCodecInfo codecInfo : codecInfos) {
            if (!codecInfo.isEncoder()) {
                MediaCodecInfo.VideoCapabilities videoCapabilities = getVideoCapabilities(codecInfo);
                if (videoCapabilities != null) {
                    for (int width = 320; width <= 3840; width += 320) {  // 适当调整分辨率范围
                        for (int height = 240; height <= 2160; height += 240) {
                            if (videoCapabilities.isSizeSupported(width, height)) {
                                Log.d("d4", "4支持的视频尺寸： " + width + "x" + height);
                                supportedResolutions.add(width + "x" + height);
                            }
                        }
                    }
                }
            }
        }

        return supportedResolutions;
    }

    private static MediaCodecInfo.VideoCapabilities getVideoCapabilities(MediaCodecInfo codecInfo) {
        String[] types = codecInfo.getSupportedTypes();
//        String[] needTypes = [MediaFormat.MIMETYPE_VIDEO_AVC, MediaFormat.MIMETYPE_VIDEO_MPEG4];
        for (String type : types) {
            if (type.startsWith("video/")) {
                try {
                    MediaCodecInfo.CodecCapabilities capabilities = codecInfo.getCapabilitiesForType(type);
                    Log.d("d3", " MediaFormat type  ===  " + type);
                    return capabilities.getVideoCapabilities();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}