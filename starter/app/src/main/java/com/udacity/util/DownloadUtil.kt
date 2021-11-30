package com.udacity.util

import android.app.DownloadManager
import android.net.Uri

object DownloadUtil {

    /**
     * The function to execute the download action. The setTitle and setDescription is used to set
     * the information in System Notification.
     * */
    fun download(
        downloadManager: DownloadManager,
        uriString: String,
        fileTitle: String,
        fileDescription: String
    ): Long {
        val request = DownloadManager.Request(Uri.parse(uriString))
            .setTitle(fileTitle)
            .setDescription(fileDescription)
            .setRequiresCharging(false)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
        return downloadManager.enqueue(request)
    }
}