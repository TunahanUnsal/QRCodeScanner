package com.ezdream.qrscanner.util

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SoftwareVersion

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DeviceId

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DeviceModel
