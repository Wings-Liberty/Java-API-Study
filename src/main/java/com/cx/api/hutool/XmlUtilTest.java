package com.cx.api.hutool;

import cn.hutool.core.lang.func.Func;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.XmlUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.xpath.XPathConstants;
import java.util.List;
import java.util.Map;

public class XmlUtilTest {

    @Test
    public void visit() {
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
//        xmlStr = xmlStr.replaceAll("\n|\\s", "");


        Document document = XmlUtil.parseXml(xmlStr);
        Element rootElement = XmlUtil.getRootElement(document);
        List<String> xmlPathList = Lists.newArrayList();
        String currentPath = "/";

        Map<String, String> pathMap = Maps.newHashMap();

        dfs(rootElement, xmlPathList, currentPath);
        System.out.println("===== 输出所有 xml path =====");
//        xmlPathList.forEach(System.out::println);
        System.out.println("===== 输出所有 xml path 下的值=====");

        xmlPathList.forEach(xmlPath->{
            Object value = XmlUtil.getByXPath(xmlPath, document, XPathConstants.STRING);
            System.out.println(value);
        });

    }

    private void dfs(Element element, List<String> xmlPathList, String currentPath) {
        List<Element> elementList = XmlUtil.getElements(element, null);
        for (Element childElement : elementList) {
            if (ObjectUtil.isEmpty(XmlUtil.getElements(childElement, null))) {
//                System.out.println(childElement.getLocalName() + " : " + childElement.getTextContent() + " : " + childElement.getNodeValue());
                xmlPathList.add(currentPath + "/" + childElement.getLocalName());
                continue;
            }
            dfs(childElement, xmlPathList, currentPath + "/" + childElement.getLocalName());
        }
    }

    @Test
    public void getValueByPath() {
        String xmlStr = "<returnsms> \n" +
                "  <returnstatus>Success（成功）</returnstatus>  \n" +
                "  <message>200</message>  \n" +
                "  <remainpoint>1490</remainpoint>  \n" +
                "  <taskID>885</taskID>  \n" +
                "  <successCounts>1</successCounts> \n" +
                "</returnsms>";
        xmlStr = xmlStr.replaceAll("\n|\\s", "");
        Document docResult = XmlUtil.parseXml(xmlStr);
        // 结果为“ok”
        Object value = XmlUtil.getByXPath("//returnsms/message", docResult, XPathConstants.STRING);
        System.out.println(value);
    }

    private static void parseVariableXmlPath(Element element, Map<String, String> xmlPathMap, String currentXmlPath) {
        List<Element> childElementList = XmlUtil.getElements(element, null);
        if(ObjectUtil.isEmpty(childElementList)){
            xmlPathMap.put(element.getLocalName(), currentXmlPath);
            return;
        }
        for (Element childElement : childElementList) {
            String nextXmlPath = currentXmlPath + "/" + childElement.getLocalName();
            parseVariableXmlPath(childElement, xmlPathMap, nextXmlPath);
        }
    }
}
