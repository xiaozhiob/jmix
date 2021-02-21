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

package io.jmix.uicharts.model.date;

import io.jmix.uicharts.model.AbstractChartObject;

public class DateFormat extends AbstractChartObject {

    private static final long serialVersionUID = 2962826142162852907L;

    private DatePeriod period;

    private String format;

    public String getFormat() {
        return format;
    }

    public DateFormat setFormat(String format) {
        this.format = format;
        return this;
    }

    public DatePeriod getPeriod() {
        return period;
    }

    public DateFormat setPeriod(DatePeriod period) {
        this.period = period;
        return this;
    }
}