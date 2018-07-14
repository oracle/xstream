/*
 * Copyright (C) 2005 Joe Walnes.
 * Copyright (C) 2006, 2007, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 *
 * Created on 06. April 2005 by Joe Walnes
 */
package com.thoughtworks.xstream.converters.enums;

import java.util.EnumMap;

import com.thoughtworks.xstream.XStream;

import junit.framework.TestCase;


public class EnumMapConverterTest extends TestCase {

    private XStream xstream;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        xstream = new XStream();
    }

    public void testIncludesEnumTypeInSerializedForm() {
        xstream.alias("simple", SimpleEnum.class);
        final EnumMap<SimpleEnum, String> map = new EnumMap<SimpleEnum, String>(SimpleEnum.class);
        map.put(SimpleEnum.BLUE, "sky");
        map.put(SimpleEnum.GREEN, "grass");

        final String expectedXml = ""
            + "<enum-map enum-type=\"simple\">\n"
            + "  <entry>\n"
            + "    <simple>GREEN</simple>\n"
            + "    <string>grass</string>\n"
            + "  </entry>\n"
            + "  <entry>\n"
            + "    <simple>BLUE</simple>\n"
            + "    <string>sky</string>\n"
            + "  </entry>\n"
            + "</enum-map>";

        assertEquals(expectedXml, xstream.toXML(map));
        assertEquals(map, xstream.fromXML(expectedXml));
    }

}
