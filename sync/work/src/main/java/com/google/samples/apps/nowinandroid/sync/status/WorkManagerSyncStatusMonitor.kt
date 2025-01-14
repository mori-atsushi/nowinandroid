/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.nowinandroid.sync.status

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.work.WorkInfo
import androidx.work.WorkInfo.State
import androidx.work.WorkManager
import com.google.samples.apps.nowinandroid.core.data.util.SyncStatusMonitor
import com.google.samples.apps.nowinandroid.sync.initializers.SyncWorkName
import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate

/**
 * [SyncStatusMonitor] backed by [WorkInfo] from [WorkManager]
 */
@Provides
@Binds
class WorkManagerSyncStatusMonitor(
    context: Context,
) : SyncStatusMonitor {
    override val isSyncing: Flow<Boolean> =
        WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(SyncWorkName)
            .map(MutableList<WorkInfo>::anyRunning)
            .asFlow()
            .conflate()
}

private val List<WorkInfo>.anyRunning get() = any { it.state == State.RUNNING }
