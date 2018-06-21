package com.coodays.repairrent.utli

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

/**
 * Created by payne on 2018/3/9.
 */
class BitmapUtil {
    companion object {
        /**
         * 压缩图片并转换为二进制文件
         */
        fun compressBmpFromSDCard(imgPath: String): ByteArray {
            //第一步，缩小图片尺寸
            val bitmap = getSmallBitmap(imgPath, 500, 500)//保证宽不大于500
            //第二部，压缩图片质量
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            var quality = 100
            while (baos.toByteArray().size > 1000 * 1024) {
                baos.reset()
                quality -= 5
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
            }
            return baos.toByteArray()
        }

        private fun getSmallBitmap(filePath: String, reqWidth: Int, reqHeight: Int): Bitmap {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(filePath, options)

            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

            options.inJustDecodeBounds = false

            return BitmapFactory.decodeFile(filePath, options)
        }

        private fun calculateInSampleSize(
                options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
            // 原始图片的宽高
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1

            if (height > reqHeight || width > reqWidth) {

                val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
                val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
                // Choose the smallest ratio as inSampleSize value, this will
                // guarantee
                // a final image with both dimensions larger than or equal to the
                // requested height and width.
                inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
            }

            return inSampleSize
        }
    }

}