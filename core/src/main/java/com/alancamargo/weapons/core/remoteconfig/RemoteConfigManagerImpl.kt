package com.alancamargo.weapons.core.remoteconfig

import com.alancamargo.weapons.core.log.Logger
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class RemoteConfigManagerImpl @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val logger: Logger
) : RemoteConfigManager {

    private var isInitialised = false

    override suspend fun getString(key: String): String = doWhenInitialised {
        firebaseRemoteConfig.getString(key)
    }

    private suspend fun <T> doWhenInitialised(action: () -> T): T {
        return if (isInitialised) {
            action()
        } else {
            initialise {
                action()
            }
        }
    }

    private suspend fun <T> initialise(onInitialised: () -> T): T {
        runCatching {
            val isActivationSuccessful = firebaseRemoteConfig.fetchAndActivate().await()
            isInitialised = isActivationSuccessful
        }.onFailure {
            logger.error(it)
        }

        return onInitialised()
    }
}
