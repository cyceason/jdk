package com.cyc.six.stax;

import java.io.InputStream;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ListUsers {

	/**
	 * 基于流模式读取xml
	 * 
	 * @throws Exception
	 */
	public void baseOnStream() throws Exception {
		InputStream resourceAsStream = null;
		XMLInputFactory factory = null;
		XMLStreamReader reader = null;
		try {
			resourceAsStream = ListUsers.class.getResourceAsStream("users.xml");
			factory = XMLInputFactory.newFactory();
			reader = factory.createXMLStreamReader(resourceAsStream);
			// 遍历XML文档
			while (reader.hasNext()) {
				// 注意 reader.nextTag() 和 reader.next.()的区别
				int event = reader.next();
				if (event == XMLStreamConstants.START_ELEMENT) {
					if ("user".equalsIgnoreCase(reader.getLocalName())) {
						System.out.println("Name:" + reader.getAttributeValue(null, "name") + ";Age:" + reader.getAttributeValue(null, "age"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

	}

	/**
	 * 基于事件的迭代访问
	 * 
	 * @throws XMLStreamException
	 */
	@SuppressWarnings("rawtypes")
	public void baseOnEvent() throws XMLStreamException {
		InputStream resourceAsStream = null;
		XMLInputFactory factory = null;
		XMLEventReader reader = null;
		try {
			resourceAsStream = ListUsers.class.getResourceAsStream("users.xml");
			factory = XMLInputFactory.newFactory();
			reader = factory.createXMLEventReader(resourceAsStream);

			// 遍历XML文档
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				// 如果事件对象是元素的开始
				if (event.isStartElement()) {
					// 转换成开始元素事件对象
					StartElement start = event.asStartElement();
					// 打印元素标签的本地名称
					System.out.print(start.getName().getLocalPart());
					// 取得所有属性
					Iterator attrs = start.getAttributes();
					while (attrs.hasNext()) {
						// 打印所有属性信息
						Attribute attr = (Attribute) attrs.next();
						System.out.print(":" + attr.getName().getLocalPart() + "=" + attr.getValue());
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

	}

	public static void main(String[] args) throws XMLStreamException {
		ListUsers listUsers = new ListUsers();
		listUsers.baseOnEvent();
	}
}
