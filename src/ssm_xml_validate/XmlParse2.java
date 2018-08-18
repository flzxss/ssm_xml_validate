package ssm_xml_validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.map.MapUtil;

public class XmlParse2 {

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
		List<String> list = new ArrayList<String>();
		for (Element element : elements) {
			String name = element.getName();
			String text = element.asXML();
			String id = element.attributeValue("id");
			list.add(id);
		}
		Map<String, Integer> countMap = CollUtil.countMap(list);
		List<String> listRepeat = listRepeat(countMap);
		System.out.println(listRepeat);
	}

	private static List<String> listRepeat(Map<String, Integer> countMap) {
		Editor<Entry<String, Integer>> filter = new Editor<Entry<String, Integer>>() {

			@Override
			public Entry<String, Integer> edit(Entry<String, Integer> entry) {
				Integer value = entry.getValue();
				if (value.intValue() > 1) {
					return entry;
				}
				return null;
			}
		};
		Map<String, Integer> filter2 = MapUtil.filter(countMap, filter);
		return new ArrayList<String>(filter2.keySet());
	}

}
