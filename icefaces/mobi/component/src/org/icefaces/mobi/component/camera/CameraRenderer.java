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

package org.icefaces.mobi.component.camera;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.render.Renderer;

import org.icefaces.mobi.api.IDevice;
import org.icefaces.mobi.renderkit.ResponseWriterWrapper;
import static org.icefaces.ace.util.HTML.*;
import org.icefaces.mobi.util.MobiJSFUtils;
import org.icefaces.ace.util.Utils;
import org.icefaces.mobi.util.CSSUtils;
import org.icefaces.util.EnvUtils;
import org.icefaces.util.ClientDescriptor;

public class CameraRenderer extends Renderer {
    private static final Logger logger = Logger.getLogger(CameraRenderer.class.getName());


    public void decode(FacesContext facesContext, UIComponent uiComponent) {
        Camera camera = (Camera) uiComponent;
        String clientId = camera.getClientId();
        try {
            if (!camera.isDisabled()) {
                Map<String, Object> map = new HashMap<String, Object>();
                boolean valid =  extractImages(facesContext, map, clientId);
                /* only set map to value if boolean returned from extractImages is true */
                if (valid){
                    if (map !=null){
                       camera.setValue(map);

             //   trigger valueChange and add map as newEvent value old event is NA
                       uiComponent.queueEvent(new ValueChangeEvent(uiComponent,
    		    		    null, map));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param facesContext
     * @param map
     * @param clientId
     * @return   boolean true means validation of the upload passes, false mean it does not.
     * @throws IOException
     *
     * that uploaded this component.
     */
    public boolean extractImages(FacesContext facesContext, Map map, String clientId) throws IOException {
        return MobiJSFUtils.decodeComponentFile(facesContext, clientId, map);
    }

    /*
      rendering markup moved to core renderer for use with JSP and JSF
     */
    public void encodeEnd(FacesContext facesContext, UIComponent uiComponent)
        throws IOException {
        Camera camera = (Camera) uiComponent;
		ResponseWriterWrapper writer = new ResponseWriterWrapper(facesContext.getResponseWriter());
		String clientId = camera.getClientId();
		writer.startElement(SPAN_ELEM, camera);
		writer.writeAttribute(ID_ATTR, clientId);
        String oldLabel = camera.getButtonLabel();
        if (MobiJSFUtils.uploadInProgress(camera))  {
            camera.setButtonLabel(camera.getCaptureMessageLabel()) ;
        } 
        //StringBuilder baseClass = new StringBuilder(CSSUtils.STYLECLASS_BUTTON);
        ClientDescriptor cd = camera.getClient();
		// button element
		writer.startElement(BUTTON_ELEM, camera);
		writer.writeAttribute(ID_ATTR, clientId + "_button");
		writer.writeAttribute(NAME_ATTR, clientId + "_button");
		writer.writeAttribute(TYPE_ATTR, "button");
		if (camera.isDisabled()) writer.writeAttribute(DISABLED_ATTR, "disabled");
		String style = camera.getStyle();
		if (style != null) writer.writeAttribute(STYLE_ATTR, style);
		String styleClass = camera.getStyleClass();
		if (styleClass != null) writer.writeAttribute(CLASS_ATTR, styleClass);
		writer.writeAttribute(TABINDEX_ATTR, camera.getTabindex());
		//writeStandardAttributes(writer, camera, baseClass.toString(), IDevice.DISABLED_STYLE_CLASS);
		//default value of unset in params is Integer.MIN_VALUE
		String script = "bridgeit.camera('" + clientId + "', 'callback"+clientId+"', {postURL:'" + camera.getPostURL() + "', ";
        script += "cookies:{'JSESSIONID':'" + 
                MobiJSFUtils.getSessionIdCookie(facesContext) +  "'}";
		int maxwidth = camera.getMaxwidth();
		if (maxwidth > 0) script += ", maxwidth: " + maxwidth;
		int maxheight = camera.getMaxheight();
		if (maxheight > 0) script += ", maxheight:" + maxheight;
		script += "});";
		writer.writeAttribute(ONCLICK_ATTR, script);
		writer.startElement(SPAN_ELEM, camera);
		writer.writeText(camera.getButtonLabel());
		writer.endElement(SPAN_ELEM);

		// themeroller and thumbnails support
		writer.startElement("span", camera);
		writer.startElement("script", camera);
		writer.writeAttribute("type", "text/javascript");
		writer.writeText("new ice.mobi.button('"+clientId+"_button');");
		writer.writeText("if (!window['thumbnails"+clientId+"']) window['thumbnails"+clientId+"'] = {};");
		writer.writeText("window['callback"+clientId+"'] = function(arg) {for (t in window['thumbnails"+clientId+"']) { setTimeout(function() {var e = document.getElementById(t); if (e) e.src = arg.preview; }, 350); try {document.getElementById(window['thumbnails"+clientId+"'][t]).value = arg.preview;} catch (e) {} }};");
        writer.endElement("script");
		writer.endElement("span");
		writer.endElement(BUTTON_ELEM);

		writer.endElement(SPAN_ELEM);
        camera.setButtonLabel(oldLabel);
    }

}
