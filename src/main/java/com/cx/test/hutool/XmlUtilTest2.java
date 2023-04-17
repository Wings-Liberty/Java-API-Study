package com.cx.test.hutool;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.XmlUtil;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class XmlUtilTest2 {

    @Test
    public void test() {
        String xmlStr = "<rule>\n" +
                "\t<desc>policy</desc>\n" +
                "\t<source-zone>untrust</source-zone>\n" +
                "\t<destination-zone>trust</destination-zone>\n" +
                "\t<source-ip>\n" +
                "\t\t<address-set>SOC_Security_Policy</address-set>\n" +
                "    <address-get>asfasdfasf</address-get>\n" +
                "\t</source-ip>\n" +
                "\t<action>false</action>\n" +
                "</rule>";
        xmlStr = xmlStr.replaceAll("\n|\\s", "");
        Document document = XmlUtil.parseXml(xmlStr);
        Element rootElement = XmlUtil.getRootElement(document);
        dfs(rootElement);
    }

    private void dfs(Element element) {
        List<Element> elementList = XmlUtil.getElements(element, null);
        for (Element childElement : elementList) {
            if (ObjectUtil.isEmpty(XmlUtil.getElements(childElement, null))) {
                System.out.println(childElement.getLocalName() + " : " + childElement.getTextContent() + " : " + childElement.getNodeValue());
                continue;
            }
            dfs(childElement);
        }
    }


}
