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

package com.google.samples.apps.nowinandroid.core.datastore.test

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.google.samples.apps.nowinandroid.core.datastore.UserPreferences
import com.google.samples.apps.nowinandroid.core.datastore.UserPreferencesSerializer
import com.google.samples.apps.nowinandroid.core.network.Dispatcher
import com.google.samples.apps.nowinandroid.core.network.NiaDispatchers.IO
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.test.TestProvides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.junit.rules.TemporaryFolder

object TestDataStoreModule {

    @TestProvides
    @Singleton
    fun providesUserPreferencesDataStore(
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        userPreferencesSerializer: UserPreferencesSerializer,
        tmpFolder: TemporaryFolder,
    ): DataStore<UserPreferences> =
        tmpFolder.testUserPreferencesDataStore(
            // TODO: Provide an application-wide CoroutineScope in the DI graph
            coroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher),
            userPreferencesSerializer = userPreferencesSerializer,
        )
}

fun TemporaryFolder.testUserPreferencesDataStore(
    coroutineScope: CoroutineScope,
    userPreferencesSerializer: UserPreferencesSerializer = UserPreferencesSerializer(),
) = DataStoreFactory.create(
    serializer = userPreferencesSerializer,
    scope = coroutineScope,
) {
    newFile("user_preferences_test.pb")
}
