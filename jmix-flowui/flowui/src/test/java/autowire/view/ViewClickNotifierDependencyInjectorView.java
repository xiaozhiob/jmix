/*
 * Copyright 2024 Haulmont.
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

package autowire.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;

@Route("view-click-notifier-dependency-injector-view")
@ViewController("ViewClickNotifierDependencyInjectorView")
@ViewDescriptor("view-click-notifier-dependency-injector-view.xml")
public class ViewClickNotifierDependencyInjectorView extends StandardView {

    @ViewComponent
    public JmixButton clickListenerId;
    @ViewComponent
    public JmixButton singleClickListenerId;
    @ViewComponent
    public JmixButton doubleClickListenerId;
    @ViewComponent
    public JmixButton defaultClickListenerId;

    @Subscribe(value = "clickListenerId", subject = "clickListener")
    protected void onClick(ClickEvent<JmixButton> event) {
        clickListenerId.setText("clickListener performed");
    }

    @Subscribe(value = "singleClickListenerId", subject = "singleClickListener")
    protected void onSingleClick(ClickEvent<JmixButton> event) {
        singleClickListenerId.setText("singleClickListener performed");
    }

    @Subscribe(value = "doubleClickListenerId", subject = "doubleClickListener")
    protected void onDoubleClick(ClickEvent<JmixButton> event) {
        doubleClickListenerId.setText("doubleClickListener performed");
    }

    @Subscribe(value = "defaultClickListenerId")
    protected void onDefaultClick(ClickEvent<JmixButton> event) {
        defaultClickListenerId.setText("defaultClickListener performed");
    }
}
