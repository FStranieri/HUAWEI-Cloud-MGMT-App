package com.fs.hcmgmt.data

import com.google.gson.annotations.SerializedName


data class QueryECSResult (
    val count: Long,
    val servers: List<Server>
)

data class Server (
    val fault: Any? = null,
    val id: String,
    val name: String,
    val flavor: Flavor,
    val accessIPv4: String,
    val accessIPv6: String,
    val status: String,
    val progress: Long? = null,

    @SerializedName("hostId")
    val hostID: String,

    val updated: String,
    val created: String,
    val metadata: Metadata,
    val tags: List<Any?>,
    val description: String,
    val locked: Boolean,

    @SerializedName("config_drive")
    val configDrive: String,

    @SerializedName("tenant_id")
    val tenantID: String,

    @SerializedName("user_id")
    val userID: String,

    @SerializedName("key_name")
    val keyName: String,

    @SerializedName("os-extended-volumes:volumes_attached")
    val osExtendedVolumesVolumesAttached: List<OSExtendedVolumesVolumesAttached>,

    @SerializedName("OS-EXT-STS:task_state")
    val osEXTSTSTaskState: Any? = null,

    @SerializedName("OS-EXT-STS:power_state")
    val osEXTSTSPowerState: Long,

    @SerializedName("OS-EXT-STS:vm_state")
    val osEXTSTSVMState: String,

    @SerializedName("OS-EXT-SRV-ATTR:host")
    val osEXTSRVATTRHost: String,

    @SerializedName("OS-EXT-SRV-ATTR:instance_name")
    val osEXTSRVATTRInstanceName: String,

    @SerializedName("OS-EXT-SRV-ATTR:hypervisor_hostname")
    val osEXTSRVATTRHypervisorHostname: String,

    @SerializedName("OS-DCF:diskConfig")
    val osDCFDiskConfig: String,

    @SerializedName("OS-EXT-AZ:availability_zone")
    val osEXTAZAvailabilityZone: String,

    @SerializedName("OS-EXT-SRV-ATTR:root_device_name")
    val osEXTSRVATTRRootDeviceName: String,

    @SerializedName("OS-EXT-SRV-ATTR:ramdisk_id")
    val osEXTSRVATTRRamdiskID: String,

    @SerializedName("enterprise_project_id")
    val enterpriseProjectID: String,

    @SerializedName("OS-EXT-SRV-ATTR:user_data")
    val osEXTSRVATTRUserData: Any? = null,

    @SerializedName("OS-SRV-USG:launched_at")
    val osSRVUSGLaunchedAt: String,

    @SerializedName("OS-EXT-SRV-ATTR:kernel_id")
    val osEXTSRVATTRKernelID: String,

    @SerializedName("OS-EXT-SRV-ATTR:launch_index")
    val osEXTSRVATTRLaunchIndex: Long,

    @SerializedName("host_status")
    val hostStatus: String,

    @SerializedName("OS-EXT-SRV-ATTR:reservation_id")
    val osEXTSRVATTRReservationID: String,

    @SerializedName("OS-EXT-SRV-ATTR:hostname")
    val osEXTSRVATTRHostname: String,

    @SerializedName("OS-SRV-USG:terminated_at")
    val osSRVUSGTerminatedAt: Any? = null,

    @SerializedName("sys_tags")
    val sysTags: List<SysTag>,

    @SerializedName("security_groups")
    val securityGroups: List<SecurityGroup>,

    val image: Image,
    val hypervisor: Any? = null,

    @SerializedName("auto_terminate_time")
    val autoTerminateTime: String,

    @SerializedName("cpu_options")
    val cpuOptions: CPUOptions
)

data class CPUOptions (
    @SerializedName("hw:cpu_threads")
    val hwCPUThreads: Any? = null
)

data class Flavor (
    val disk: String,
    val vcpus: String,
    val ram: String,
    val id: String,
    val name: String
)

data class Image (
    val id: String
)

data class Metadata (
    @SerializedName("cascaded.instance_extrainfo")
    val cascadedInstanceExtrainfo: String,

    @SerializedName("metering.imagetype")
    val meteringImagetype: String,

    @SerializedName("os_bit")
    val osBit: String,

    @SerializedName("metering.resourcetype")
    val meteringResourcetype: String,

    @SerializedName("metering.image_id")
    val meteringImageID: String,

    @SerializedName("os_type")
    val osType: String,

    @SerializedName("vpc_id")
    val vpcID: String,

    @SerializedName("charging_mode")
    val chargingMode: String,

    @SerializedName("metering.resourcespeccode")
    val meteringResourcespeccode: String,

    @SerializedName("image_name")
    val imageName: String,

    @SerializedName("EcmResStatus")
    val ecmResStatus: String? = null,

    @SerializedName("lockSourceId")
    val lockSourceID: String? = null,

    val lockCheckEndpoint: String? = null,
    val lockSource: String? = null,
    val lockScene: String? = null,

    @SerializedName("metering.order_id")
    val meteringOrderID: String? = null,

    @SerializedName("metering.product_id")
    val meteringProductID: String? = null
)

data class OSExtendedVolumesVolumesAttached (
    val id: String,

    @SerializedName("delete_on_termination")
    val deleteOnTermination: String,

    val bootIndex: String,
    val device: String
)

data class SecurityGroup (
    val id: String,
    val name: String
)

data class SysTag (
    val key: String,
    val value: String
)
