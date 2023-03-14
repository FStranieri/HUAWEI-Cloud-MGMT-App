package com.fs.hcmgmt.data

import com.google.gson.annotations.SerializedName

data class ECSTaskResult(
    @SerializedName("job_id")
    val jobId: String
)