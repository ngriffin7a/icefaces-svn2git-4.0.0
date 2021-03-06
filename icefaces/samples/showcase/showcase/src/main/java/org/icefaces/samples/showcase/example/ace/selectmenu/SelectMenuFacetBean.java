/*
 * Copyright 2004-2014 ICEsoft Technologies Canada Corp.
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

package org.icefaces.samples.showcase.example.ace.selectmenu;

import org.icefaces.samples.showcase.metadata.annotation.*;
import org.icefaces.samples.showcase.metadata.context.ComponentExampleImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.util.*;

@ComponentExample(
		parent = SelectMenuBean.BEAN_NAME,
        title = "example.ace.selectmenu.facet.title",
        description = "example.ace.selectmenu.facet.description",
        example = "/resources/examples/ace/selectmenu/selectMenuFacet.xhtml"
)
@ExampleResources(
        resources ={
            // xhtml
            @ExampleResource(type = ResourceType.xhtml,
                    title="selectMenuFacet.xhtml",
                    resource = "/resources/examples/ace/selectmenu/selectMenuFacet.xhtml"),
            // Java Source
            @ExampleResource(type = ResourceType.java,
                    title="SelectMenuFacetBean.java",
                    resource = "/WEB-INF/classes/org/icefaces/samples/showcase"+
                    "/example/ace/selectmenu/SelectMenuFacetBean.java")
        }
)
@ManagedBean(name= SelectMenuFacetBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class SelectMenuFacetBean extends ComponentExampleImpl< SelectMenuFacetBean > implements Serializable {

    public static final String BEAN_NAME = "selectMenuFacetBean";
	public String getBeanName() { return BEAN_NAME; }
	
	private List<Color> textColors;
	private List<Color> backgroundColors;
    
    public SelectMenuFacetBean() {
        super(SelectMenuFacetBean.class);
		
		backgroundColors = new ArrayList<Color>();
		backgroundColors.add(new Color("Alice Blue", "#F0F8FF"));
		backgroundColors.add(new Color("Beige", "#F5F5DC"));
		backgroundColors.add(new Color("Cornsilk", "#FFF8DC"));
		backgroundColors.add(new Color("Gainsboro", "#DCDCDC"));
		backgroundColors.add(new Color("Khaki", "#F0E68C"));
		backgroundColors.add(new Color("Lavender", "#E6E6FA"));
		backgroundColors.add(new Color("Lavender Blush", "#FFF0F5"));
		backgroundColors.add(new Color("Lemon Chiffon", "#FFFACD"));
		backgroundColors.add(new Color("Misty Rose", "#FFE4E1"));
		backgroundColors.add(new Color("Mint Cream", "#F5FFFA"));
		backgroundColors.add(new Color("Pale Green", "#98FB98"));
		backgroundColors.add(new Color("Papaya Whip", "#FFEFD5"));
		backgroundColors.add(new Color("Sea Shell", "#FFF5EE"));
		backgroundColors.add(new Color("White Smoke", "#F5F5F5"));
		
		textColors = new ArrayList<Color>();
		textColors.add(new Color("Aquamarine", "#7FFFD4"));
		textColors.add(new Color("Burly Wood", "#DEB887"));
		textColors.add(new Color("Cadet Blue", "#5F9EA0"));
		textColors.add(new Color("Coral", "#FF7F50"));
		textColors.add(new Color("Crimson", "#DC143C"));
		textColors.add(new Color("Fire Brick", "#B22222"));
		textColors.add(new Color("Golden Rod", "#DAA520"));
		textColors.add(new Color("Green Yellow", "#ADFF2F"));
		textColors.add(new Color("Indigo", "#4B0082"));
		textColors.add(new Color("Medium Orchid", "#BA55D3"));
		textColors.add(new Color("Midnight Blue", "#191970"));
		textColors.add(new Color("Olive Drab", "#6B8E23"));
		textColors.add(new Color("Orange", "#FFA500"));
		textColors.add(new Color("Pale Violet Red", "#DB7093"));
		textColors.add(new Color("Sandy Brown", "#F4A460"));
		textColors.add(new Color("Slate Blue", "#6A5ACD"));
    }

    @PostConstruct
    public void initMetaData() {
        super.initMetaData();
    }

	private String textColor = "";
    public String getTextColor() { return textColor; }
    public void setTextColor(String textColor) { this.textColor = textColor; }

	private String backgroundColor = "";
    public String getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(String backgroundColor) { this.backgroundColor = backgroundColor; }
	
    public List<Color> getTextColors() { return textColors; }

    public List<Color> getBackgroundColors() { return backgroundColors; }
}