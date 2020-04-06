/**
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.LangUtils;

/**
 * Renderer for the {@link LetterAvatar} component.
 *
 * @author https://github.com/aripddev
 * @since 7.0
 */
@FacesRenderer(componentFamily = LetterAvatar.COMPONENT_FAMILY, rendererType = LetterAvatar.DEFAULT_RENDERER)
public class LetterAvatarRenderer extends CoreRenderer {

    private static final Pattern VALUE_PATTTERN = Pattern.compile("\\b[a-zA-Z]");

    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final LetterAvatar letterAvatar = (LetterAvatar) component;

        encodeMarkup(context, letterAvatar);
    }

    public void encodeMarkup(FacesContext context, LetterAvatar letterAvatar) throws IOException {
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

        final Boolean rounded = letterAvatar.isRounded();

        String color = letterAvatar.getColor();
        if (LangUtils.isValueBlank(color)) {
            color = "#fff"; // keep it for mix-blend-mode
        }

        String backgroundColor = letterAvatar.getBackgroundColor();
        if (LangUtils.isValueBlank(backgroundColor)) {
            backgroundColor = "hsl(" + hue(value) + ", 100%, 50%)";
        }

        final String size = letterAvatar.getSize();
        String style = letterAvatar.getStyle();
        style = style == null ? styleDiv(size, color, backgroundColor, rounded) : styleDiv(size, color, backgroundColor, rounded) + StringUtils.SPACE + style;
        String styleClass = letterAvatar.getStyleClass();
        styleClass = styleClass == null ? LetterAvatar.COMPONENT_CLASS : LetterAvatar.COMPONENT_CLASS + StringUtils.SPACE + styleClass;

        writer.startElement("div", letterAvatar);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("title", value, null);

        if (rounded) {
            styleClass = styleClass + " " + LetterAvatar.COMPONENT_CLASS_ROUNDED;
        }
        writer.writeAttribute("class", styleClass, "styleClass");

        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }

        writer.startElement("span", letterAvatar);
        writer.writeAttribute("class", "ui-letteravatar-initials", null);
        writer.writeAttribute("style", styleSpan(size), null);
        writer.write(initials);
        writer.endElement("span");

        writer.endElement("div");
    }

    private int hash(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = (result << 5) - result + str.charAt(i);
            result |= 0;
        }
        return result;
    }

    private int hue(String str) {
        return Math.abs(hash(str) % 360);
    }

    private String styleDiv(String size, String color, String backgroundColor, boolean rounded) {
        final Map<String, String> map = new LinkedHashMap<>(8);
        map.put("color", color);
        map.put("background-color", backgroundColor);
        if (rounded) {
            map.put("border-radius", "50%");
        }
        map.put("height", size);
        map.put("text-align", "center");
        map.put("width", size);
        return map.entrySet()
                    .stream()
                    .map(e -> e.getKey() + ":" + e.getValue())
                    .collect(Collectors.joining(";"));
    }

    private String styleSpan(String size) {
        final Map<String, String> map = new LinkedHashMap<>(8);
        map.put("font-size", "calc(" + size + " / 2)"); // 50% of parent
        map.put("line-height", "1");
        map.put("position", "relative");
        map.put("top", "calc(" + size + " / 4)"); // 25% of parent
        map.put("mix-blend-mode", "difference");
        return map.entrySet()
                    .stream()
                    .map(e -> e.getKey() + ":" + e.getValue())
                    .collect(Collectors.joining(";"));
    }

}
