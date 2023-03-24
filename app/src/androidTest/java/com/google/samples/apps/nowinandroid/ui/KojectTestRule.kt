/*
 * Copyright 2023 The Android Open Source Project
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

package com.google.samples.apps.nowinandroid.ui

import androidx.test.core.app.ApplicationProvider
import com.google.samples.apps.nowinandroid.core.datastore.test.TestExtras
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.test.startTest
import org.junit.rules.TemporaryFolder
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class KojectTestRule(
    private val tmpFolder: TemporaryFolder,
) : TestWatcher() {
    @OptIn(ExperimentalKojectApi::class)
    override fun starting(description: Description) {
        Koject.startTest {
            application(ApplicationProvider.getApplicationContext())
            addExtras(TestExtras(tmpFolder))
        }
    }

    override fun finished(description: Description) {
        Koject.stop()
    }
}
