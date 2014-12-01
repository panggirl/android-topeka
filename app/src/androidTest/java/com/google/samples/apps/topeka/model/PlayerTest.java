/*
 * Copyright 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.samples.apps.topeka.model;

import android.os.Parcel;
import android.test.suitebuilder.annotation.MediumTest;

import junit.framework.TestCase;

@MediumTest
public class PlayerTest extends TestCase {

    private static final Avatar AVATAR = Avatar.TWELVE;
    private static final String LAST_INITIAL = "a";
    private static final String FIRST_NAME = "first";

    public void testWriteToParcel() throws Exception {
        Player initial = getPlayerUnderTest();
        Parcel dest = Parcel.obtain();
        initial.writeToParcel(dest, 0);
        dest.setDataPosition(0);
        Player unparcelled = new Player(dest);
        assertEquals(initial, unparcelled);
    }

    private static Player getPlayerUnderTest() {
        return new Player(FIRST_NAME, LAST_INITIAL, AVATAR);
    }
}