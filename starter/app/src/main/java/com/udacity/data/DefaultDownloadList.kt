package com.udacity.data

import com.udacity.R
import com.udacity.util.Constants

/**
 * The object is used to store the fixed list of the radio group.
 * */
object DefaultDownloadList {
    val downloadItemGlide = DownloadItem(Constants.DOWNLOAD_URL_GLIDE, R.string.file_name_glide, R.string.file_description_glide)
    val downloadItemLoadApp = DownloadItem(Constants.DOWNLOAD_URL_LOAD_APP, R.string.file_name_loadapp, R.string.file_description_loadapp)
    val downloadItemRetrofit = DownloadItem(Constants.DOWNLOAD_URL_RETROFIT, R.string.file_name_retrofit, R.string.file_description_retrofit)
}