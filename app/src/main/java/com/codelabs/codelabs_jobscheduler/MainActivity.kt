package com.codelabs.codelabs_jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mScheduler: JobScheduler? = null
    private val JOB_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun scheduleJob(view: View) {
        val selectedNetworkID = networkOptions.getCheckedRadioButtonId()
        var selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE

        when (selectedNetworkID) {
            R.id.noNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE
            R.id.anyNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY
            R.id.wifiNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED
        }

        mScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler?;

        val serviceName = ComponentName(
            packageName,
            NotificationJobService::class.java.name
        )
        val builder = JobInfo.Builder(JOB_ID, serviceName)
            .setRequiredNetworkType(selectedNetworkOption);

        val myJobInfo = builder.build()
        mScheduler!!.schedule(myJobInfo)

        Toast.makeText(this, "Job Scheduled, job will run when " +
                "the constraints are met.", Toast.LENGTH_SHORT).show();

    }

    fun cancelJobs(view: View) {
        if (mScheduler!=null){
            mScheduler?.cancelAll();
            mScheduler = null;
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
