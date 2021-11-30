package com.udacity.util

import android.app.DownloadManager
import android.net.Uri

/**
 * The object contain the static method to do the download function.
 * */
object DownloadUtil {

    /**
     * The function to execute the download action. The setTitle and setDescription is used to set
     * the information in System Notification.
     *
     * @param downloadManager
     * @param uriString is the uri string of file
     * @param fileTitle is the title of file
     * @param fileDescription is the description of file
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