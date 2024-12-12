//package com.example.android_demo.video_dismension;
//
//import android.media.MediaCodecInfo;
//
//import android.media.MediaCodecList;
//
//import android.media.MediaFormat;
//import android.util.Range;
//
//
// public class VideoDimension {
//
//     public static void dissensionValue() {
//
//        final  MediaCodecList codecInfoList = new MediaCodecList(MediaCodecList.ALL_CODECS);
//
//        MediaCodecInfo[] codecInfos = codecInfoList.getCodecInfos();
//
//         System.out.println("支持的视频尺寸： codecInfos" + codecInfos.length);
//
//         for (MediaCodecInfo codecInfo : codecInfos) {
//
//            MediaCodecInfo.CodecCapabilities capabilities = codecInfo.getCapabilitiesForType(MediaFormat.MIMETYPE_VIDEO_MPEG4);
//
//            MediaCodecInfo.VideoCapabilities videoCapabilities = capabilities.getVideoCapabilities();
//
//            final Range<Integer> supportedWidths = videoCapabilities.getSupportedWidths();
//            final int widthLower =  supportedWidths.getLower();
//            final int widthUpper =  supportedWidths.getUpper();
//
//            final Range<Integer> supportedHeights = videoCapabilities.getSupportedHeights();
//            final int heightLower = supportedHeights.getLower();
//            final int heightUpper = supportedHeights.getUpper();
//
//
//            System.out.println("支持的视频尺寸： lower" + widthLower + "x" + heightLower);
//            System.out.println("支持的视频尺寸： upper" + widthUpper + "x" + heightUpper);
//
////            Dimension[] supportedDimensions = videoCapabilities.getSupportedDimensions();
////            for (Dimension dimension : supportedDimensions) {
////                System.out.println("支持的视频尺寸：" + dimension.width + "x" + dimension.height);
////            }
//        }
//
//    }
//
//
//
//}
