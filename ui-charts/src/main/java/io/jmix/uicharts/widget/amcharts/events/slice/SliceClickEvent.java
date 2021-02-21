/*
 * Copyright 2021 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.uicharts.widget.amcharts.events.slice;


import io.jmix.ui.data.DataItem;
import io.jmix.uicharts.widget.amcharts.JmixAmchartsScene;
import io.jmix.uicharts.widget.amcharts.events.AbstractClickEvent;

public class SliceClickEvent extends AbstractClickEvent {

    private static final long serialVersionUID = -536343761071370040L;

    private final DataItem dataItem;

    public SliceClickEvent(JmixAmchartsScene scene, DataItem dataItem, int x, int y, int absoluteX, int absoluteY) {
        super(scene, x, y, absoluteX, absoluteY);
        this.dataItem = dataItem;
    }

    public DataItem getDataItem() {
        return dataItem;
    }
}