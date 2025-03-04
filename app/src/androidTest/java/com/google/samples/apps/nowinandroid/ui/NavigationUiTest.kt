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

package com.google.samples.apps.nowinandroid.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.google.accompanist.testharness.TestHarness
import com.google.samples.apps.nowinandroid.core.data.util.NetworkMonitor
import com.moriatsushi.koject.inject
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 * Tests that the navigation UI is rendered correctly on different screen sizes.
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class NavigationUiTest {
    @get:Rule(order = 0)
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @get:Rule(order = 1)
    val kojectTestRule = KojectTestRule(tmpFolder)

    /**
     * Use a test activity to set the content on.
     */
    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val networkMonitor: NetworkMonitor
        get() = inject()

    @Test
    fun compactWidth_compactHeight_showsNavigationBar() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(400.dp, 400.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaBottomBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaNavRail").assertDoesNotExist()
    }

    @Test
    fun mediumWidth_compactHeight_showsNavigationRail() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(610.dp, 400.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaNavRail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaBottomBar").assertDoesNotExist()
    }

    @Test
    fun expandedWidth_compactHeight_showsNavigationRail() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(900.dp, 400.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaNavRail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaBottomBar").assertDoesNotExist()
    }

    @Test
    fun compactWidth_mediumHeight_showsNavigationBar() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(400.dp, 500.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaBottomBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaNavRail").assertDoesNotExist()
    }

    @Test
    fun mediumWidth_mediumHeight_showsNavigationRail() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(610.dp, 500.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaNavRail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaBottomBar").assertDoesNotExist()
    }

    @Test
    fun expandedWidth_mediumHeight_showsNavigationRail() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(900.dp, 500.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaNavRail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaBottomBar").assertDoesNotExist()
    }

    @Test
    fun compactWidth_expandedHeight_showsNavigationBar() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(400.dp, 1000.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaBottomBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaNavRail").assertDoesNotExist()
    }

    @Test
    fun mediumWidth_expandedHeight_showsNavigationRail() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(610.dp, 1000.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaNavRail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaBottomBar").assertDoesNotExist()
    }

    @Test
    fun expandedWidth_expandedHeight_showsNavigationRail() {
        composeTestRule.setContent {
            TestHarness(size = DpSize(900.dp, 1000.dp)) {
                BoxWithConstraints {
                    NiaApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(
                            DpSize(maxWidth, maxHeight),
                        ),
                        networkMonitor = networkMonitor,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("NiaNavRail").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NiaBottomBar").assertDoesNotExist()
    }
}
