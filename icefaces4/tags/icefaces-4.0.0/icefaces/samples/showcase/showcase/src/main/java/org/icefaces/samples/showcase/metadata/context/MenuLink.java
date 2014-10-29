/*
 * Copyright 2004-2013 ICEsoft Technologies Canada Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.icefaces.samples.showcase.metadata.context;

import java.io.Serializable;

public class MenuLink implements Serializable {

    private String title;
    private boolean isDisabled;
    private boolean isDefault;
    private boolean isNew;
    private boolean isFullPageLoad;
    private String exampleBeanName;
	private int group = -1;

    public MenuLink(String title, boolean aDefault, boolean aNew, boolean isDisabled, boolean isFullPageLoad, String exampleBeanName, int group) {
        this.title = title;
        this.isDisabled = isDisabled;
        isDefault = aDefault;
        isNew = aNew;
		this.isFullPageLoad = isFullPageLoad;
        this.exampleBeanName = exampleBeanName;
		this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isFullPageLoad() {
        return isFullPageLoad;
    }

    public String getExampleBeanName() {
        return exampleBeanName;
    }

	public int getGroup() {
		return group;
	}
}
