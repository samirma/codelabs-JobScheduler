package com.codelabs.codelabs_jobscheduler

import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService


class NotificationJobService: JobService() {

    var mNotifyManager: NotificationManager? = null

    // Notification channel ID.
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"

    override fun onStopJob(p0: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }
}