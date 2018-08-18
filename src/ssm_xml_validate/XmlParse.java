package ssm_xml_validate;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.hutool.core.io.FileUtil;

public class XmlParse {

	public static void parse(String fileName) {
		fileName = fileName.replace("\\", "/");
		String readUtf8String = FileUtil.readUtf8String(fileName);
		Document parseText = null;
		try {
			parseText = DocumentHelper.parseText(readUtf8String);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element rootElement = parseText.getRootElement();
		List<Element> elements = rootElement.elements();
		for (Element element : elements) {
			String name = element.getName();
			String text = element.asXML();
			String attributeValue = element.attributeValue("id");
			System.out.println(name);
			System.out.println("------------");
			System.out.println(text);
			System.out.println("------------");
			System.out.println(attributeValue);
		}
	}

}
