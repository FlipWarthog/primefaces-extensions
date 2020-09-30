/*
 * Copyright 2011-2020 PrimeFaces Extensions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.extensions.component.letteravatar;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.extensions.util.Attrs;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.LangUtils;

/**
 * Renderer for the {@link LetterAvatar} component.
 *
 * @author https://github.com/aripddev
 * @since 7.0
 */
public class LetterAvatarRenderer extends CoreRenderer {

    private static final Pattern VALUE_PATTTERN = Pattern.compile("\\b[a-zA-Z]");

    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final LetterAvatar letterAvatar = (LetterAvatar) component;

        encodeMarkup(context, letterAvatar);
    }

    public void encodeMarkup(final FacesContext context, final LetterAvatar letterAvatar) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final String clientId = letterAvatar.getClientId(context);
        final String value = letterAvatar.getValue();

        final Matcher m = VALUE_PATTTERN.matcher(value);
        final StringBuilder sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group());
        }
        String initials = sb.toString();
        initials = initials.length() == 1 ? initials : initials.charAt(0) + initials.substring(initials.length() - 1);

        final boolean rounded = letterAvatar.isRounded();

        String color = letterAvatar.getColor();
        if (LangUtils.isValueBlank(color)) {
            color = "#fff"; // keep it for mix-blend-mode
        }

        String backgroundColor = letterAvatar.getBackgroundColor();
        if (LangUtils.isValueBlank(backgroundColor)) {
            backgroundColor = "hsl(" + hue(value) + ", 100%, 50%)";
        }

        final String size = letterAvatar.getSize();
        String style = styleContainer(size, color, backgroundColor);
        if (!LangUtils.isValueBlank(letterAvatar.getStyle())) {
            style += letterAvatar.getStyle();
        }
        String styleClass = LetterAvatar.COMPONENT_CLASS;
        if (!LangUtils.isValueBlank(letterAvatar.getStyleClass())) {
            styleClass += " " + letterAvatar.getStyleClass();
        }

        writer.startElement("span", letterAvatar);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("title", value, null);

        if (rounded) {
            styleClass += " " + LetterAvatar.COMPONENT_CLASS_ROUNDED;
        }
        writer.writeAttribute(Attrs.CLASS, styleClass, "styleClass");
        writer.writeAttribute(Attrs.STYLE, style, Attrs.STYLE);

        renderChildren(context, letterAvatar);

        writer.startElement("span", letterAvatar);
        writer.writeAttribute(Attrs.CLASS, "ui-letteravatar-initials", null);
        writer.writeAttribute(Attrs.STYLE, styleInitials(size), null);
        writer.write(initials);
        writer.endElement("span");

        writer.endElement("span");
    }

    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) {
        // NOOP
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    public static int hue(final String str) {
        return Math.abs((str.hashCode() % 40) * 9);
    }

    protected String styleContainer(final String size, final String color, final String backgroundColor) {
        return "color:" + color
                    + ";background-color:" + backgroundColor
                    + ";height:" + size
                    + ";width:" + size
                    + ";";
    }

    protected String styleInitials(final String size) {
        return "font-size:calc(" + size + "/2)"
                    + ";line-height:" + size
                    + ";";
    }
}
