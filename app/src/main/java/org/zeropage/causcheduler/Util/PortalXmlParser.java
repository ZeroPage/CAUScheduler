package org.zeropage.causcheduler.Util;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * 포탈에서 가져온 Xml 본문을 Parsing 해주는 클래스입니다.
 * Created by Lumin on 2016-01-09.
 */
public class PortalXmlParser {
    private static final String LOG_TAG = "XmlParser";

    /**
     * 강의 리스트 정보를 가지고 있는 Xml 정보를 Parsing합니다.
     *
     * @param lectureListXmlContent 강의 리스트 정보를 가지고 있는 Xml 내용물을 가리킵니다.
     * @return 해당 Xml로부터 가져올 수 있는 모든 강의 정보를 가지고 있는 List입니다.
     */
    public List<Lecture> parseLectureList(String lectureListXmlContent) {
        List<Lecture> lectureList = new ArrayList<>();

        try {
            // Encoding 재조정 작업.
            lectureListXmlContent = new String(lectureListXmlContent.getBytes("ISO_8859_1"));
            Log.e(LOG_TAG, "LectureList Parsing에 전달된 Xml : " + lectureListXmlContent);

            InputSource inputSource = new InputSource(new StringReader(lectureListXmlContent));
            Document lectureListDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xPath = XPathFactory.newInstance().newXPath();

            // map 하위의 vector id = result를 담는 노드를 탐색
            NodeList allLectureListNode = (NodeList) xPath.compile("map/vector[@id='result']/map[@id]").evaluate(lectureListDoc, XPathConstants.NODESET);

            Log.e(LOG_TAG, "전체 Parsing 대상이 되는 노드 개수 : " + allLectureListNode.getLength());

            for (int i = 0; i < allLectureListNode.getLength(); i++) {
                NodeList lectureNode = allLectureListNode.item(i).getChildNodes();

                // 하나의 강의 정보에 대해 Parsing.
                int sectionNum = Integer.parseInt(lectureNode.item(2).getAttributes().item(0).getTextContent());
                String lectureName = lectureNode.item(3).getAttributes().item(0).getTextContent();
                String professorName = lectureNode.item(4).getAttributes().item(0).getTextContent();
                String studyPeriod = lectureNode.item(5).getAttributes().item(0).getTextContent();
                String lectureDeptName = lectureNode.item(6).getAttributes().item(0).getTextContent();

                // For Logging.
                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 이름 : " + lectureName);
                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 교수자 이름 : " + professorName);
                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 분반 : " + sectionNum);
                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 개설 학과 : " + lectureDeptName);
                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 학습 기간 : " + studyPeriod);

                // 강의 리스트에 추가
                lectureList.add(new Lecture(lectureName, professorName, lectureDeptName, sectionNum, studyPeriod));
            }
        } catch (ParserConfigurationException e) {
            Log.e(LOG_TAG, "Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (IOException | SAXException e) {
            Log.e(LOG_TAG, "IO 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (XPathExpressionException e) {
            Log.e(LOG_TAG, "XPath Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        }

        return lectureList;
    }

    /**
     * 주어진 Xml 내용으로부터 식단의 상세한 내역을 가져옵니다.
     * @param mealXmlContent 식단 Request를 요청한 결과가 담겨있는 Xml을 가리킵니다.
     */
    public void parseMealInfo(String mealXmlContent) {
        try {
            // Encoding 재조정 작업.
            mealXmlContent = new String(mealXmlContent.getBytes("ISO_8859_1"));
            Log.e(LOG_TAG, "MealList Parsing에 전달된 Xml : " + mealXmlContent);

            InputSource inputSource = new InputSource(new StringReader(mealXmlContent));
            Document mealInfoDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xPath = XPathFactory.newInstance().newXPath();

            // vector id = result를 담는 노드 탐색
            NodeList mealInfoList = (NodeList) xPath.compile("map/vector[@id='result']/map[@id]").evaluate(mealInfoDoc, XPathConstants.NODESET);

            Log.e(LOG_TAG, "전체 Parsing 대상이 되는 노드 개수 : " + mealInfoList.getLength());

            for (int i = 0; i < mealInfoList.getLength(); i++) {
                NodeList infoNode = mealInfoList.item(i).getChildNodes();

                // 구체적인 Parsing 시작.
                String mealMenuName = infoNode.item(0).getAttributes().item(0).getTextContent();
                String mealTime = infoNode.item(1).getAttributes().item(0).getTextContent();
                String mealPrice = infoNode.item(2).getAttributes().item(0).getTextContent().replaceAll(" ", "");
                String mealMenuContent = infoNode.item(3).getAttributes().item(0).getTextContent().replaceAll("<br>", "\n");


                // For Logging.
                Log.e(LOG_TAG, "현재 Parsing 중인 식단의 이름 : " + mealMenuName);
                Log.e(LOG_TAG, "현재 Parsing 중인 식단 배식 시간 : " + mealTime);
                Log.e(LOG_TAG, "현재 Parsing 중인 식단의 가격 : " + mealPrice);
                Log.e(LOG_TAG, "현재 Parsing 중인 식단의 메뉴 구성 : " + mealMenuContent);
            }

        } catch (ParserConfigurationException e) {
            Log.e(LOG_TAG, "Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (IOException | SAXException e) {
            Log.e(LOG_TAG, "IO 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (XPathExpressionException e) {
            Log.e(LOG_TAG, "XPath Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        }
    }
}