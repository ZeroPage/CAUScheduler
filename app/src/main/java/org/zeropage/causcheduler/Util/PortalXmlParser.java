package org.zeropage.causcheduler.util;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.zeropage.causcheduler.data.*;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
     * @param lectureListXmlContent 강의 리스트 정보를 가지고 있는 Xml 내용물을 가리킵니다.
     * @return 해당 Xml로부터 가져올 수 있는 모든 강의 정보를 가지고 있는 List입니다.
     */
    public List<Lecture> parseLectureList(String lectureListXmlContent) {
        List<Lecture> lectureList = new ArrayList<>();

        try {
            // Encoding 재조정 작업.
            lectureListXmlContent = new String(lectureListXmlContent.getBytes("ISO_8859_1"));
            //Log.e(LOG_TAG, "LectureList Parsing에 전달된 Xml : " + lectureListXmlContent);

            InputSource inputSource = new InputSource(new StringReader(lectureListXmlContent));
            Document lectureListDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xPath = XPathFactory.newInstance().newXPath();

            // map 하위의 vector id = result를 담는 노드를 탐색
            NodeList allLectureListNode = (NodeList) xPath.compile("/map/vector[@id='result']/map[@id]").evaluate(lectureListDoc, XPathConstants.NODESET);

            //Log.e(LOG_TAG, "전체 Parsing 대상이 되는 노드 개수 : " + allLectureListNode.getLength());

            for (int i = 0; i < allLectureListNode.getLength(); i++) {
                NodeList lectureNode = allLectureListNode.item(i).getChildNodes();

                // 하나의 강의 정보에 대해 Parsing.
                int lectureNum = Integer.parseInt(lectureNode.item(1).getAttributes().item(0).getTextContent());
                int sectionNum = Integer.parseInt(lectureNode.item(2).getAttributes().item(0).getTextContent());
                String lectureName = lectureNode.item(3).getAttributes().item(0).getTextContent();
                String professorName = lectureNode.item(4).getAttributes().item(0).getTextContent();
                String studyPeriod = lectureNode.item(5).getAttributes().item(0).getTextContent();
                String lectureDeptName = lectureNode.item(6).getAttributes().item(0).getTextContent();

                // For Logging.
//                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 번호 : " + lectureNum);
//                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 이름 : " + lectureName);
//                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 교수자 이름 : " + professorName);
//                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 분반 : " + sectionNum);
//                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 개설 학과 : " + lectureDeptName);
//                Log.e(LOG_TAG, "현재 Parsing 중인 강의의 학습 기간 : " + studyPeriod);

                // 강의 리스트에 추가
                // TODO 너무 긴 생성자를 고쳐야 함
                lectureList.add(new Lecture(lectureName, lectureNum, professorName, lectureDeptName, sectionNum, studyPeriod));
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
     * @return 해당 Xml로부터 가져올 수 있는 모든 식단들을 저장하고 있는 리스트입니다.
     */
    public List<Meal> parseMealInfo(String mealXmlContent, Restaurant restaurant) {
        List<Meal> mealList = new ArrayList<>();

        try {
            // Encoding 재조정 작업.
            mealXmlContent = new String(mealXmlContent.getBytes("ISO_8859_1"));
            //Log.e(LOG_TAG, "MealList Parsing에 전달된 Xml : " + mealXmlContent);

            InputSource inputSource = new InputSource(new StringReader(mealXmlContent));
            Document mealInfoDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xPath = XPathFactory.newInstance().newXPath();

            // today value
            String today = (String)xPath.compile("/map/today/@value").evaluate(mealInfoDoc, XPathConstants.STRING);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = dateFormat.parse(today);

//            Log.e(LOG_TAG, "현재 Parsing 중인 식단의 날짜 : " + dateFormat.format(date));

            // vector id = result를 담는 노드 탐색
            NodeList mealInfoList = (NodeList) xPath.compile("/map/vector[@id='result']/map[@id]").evaluate(mealInfoDoc, XPathConstants.NODESET);

            //Log.e(LOG_TAG, "전체 Parsing 대상이 되는 노드 개수 : " + mealInfoList.getLength());

            for (int i = 0; i < mealInfoList.getLength(); i++) {
                NodeList infoNode = mealInfoList.item(i).getChildNodes();

                // 구체적인 Parsing 시작.
                String mealName = infoNode.item(0).getAttributes().item(0).getTextContent();
                String mealTime = infoNode.item(1).getAttributes().item(0).getTextContent();
                int mealPrice = Integer.parseInt(infoNode.item(2).getAttributes().item(0).getTextContent().split(" ")[0]);

                String[] mealContent = infoNode.item(3).getAttributes().item(0).getTextContent().split("<br>");
                float mealTotalCalorie = Float.parseFloat(mealContent[0].replaceAll("Kcal", ""));
                String[] mealMenu = Arrays.copyOfRange(mealContent, 1, mealContent.length);

                // For Logging.
//                Log.e(LOG_TAG, "현재 Parsing 중인 식단의 이름 : " + mealName);
//                Log.e(LOG_TAG, "현재 Parsing 중인 식단 배식 시간 : " + mealTime);
//                Log.e(LOG_TAG, "현재 Parsing 중인 식단의 가격 : " + mealPrice);
//                Log.e(LOG_TAG, "현재 Parsing 중인 식단의 총 칼로리 : " + mealTotalCalorie);
//                Log.e(LOG_TAG, "현재 Parsing 중인 식단의 메뉴 구성 : " + Arrays.toString(mealMenu));
                // TODO 너무 긴 생성자를 고쳐야 함
                Meal meal = new Meal(mealName, restaurant.code, date, mealPrice, mealTotalCalorie, mealTime, Arrays.toString(mealMenu));
                mealList.add(meal);
            }

        } catch (ParserConfigurationException|ParseException e) {
            Log.e(LOG_TAG, "Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (IOException | SAXException e) {
            Log.e(LOG_TAG, "IO 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (XPathExpressionException e) {
            Log.e(LOG_TAG, "XPath Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        }

        return mealList;
    }

    /**
     * 주어진 Xml 내용으로부터 과제의 리스트를 가져옵니다.
     * @param homeworkListXmlContent 과제 리스트 Request를 요청한 결과가 담겨있는 Xml을 가리킵니다.
     * @return 해당 Xml로부터 가져올 수 있는 모든 과제들을 저장하고 있는 리스트입니다.
     */
    public List<Homework> parseHomeworkList(String homeworkListXmlContent, Lecture lecture) {
        List<Homework> homeworkList = new ArrayList<>();

        try {
            // Encoding 재조정 작업.
            homeworkListXmlContent = new String(homeworkListXmlContent.getBytes("ISO_8859_1"));
            //Log.e(LOG_TAG, "HomeworkList Parsing에 전달된 Xml : " + homeworkListXmlContent);

            InputSource inputSource = new InputSource(new StringReader(homeworkListXmlContent));
            Document homeworkDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xmlPath = XPathFactory.newInstance().newXPath();

            // vector id = tasklist를 담는 노드를 탐색
            NodeList homeworkNodeList = (NodeList) xmlPath.compile("/map/vector[@id='tasklist']/map[@id]").evaluate(homeworkDocument, XPathConstants.NODESET);

            for (int i = 0; i < homeworkNodeList.getLength(); i++) {
                NodeList homeworkNode = homeworkNodeList.item(i).getChildNodes();
                int pumpedIndex = 0;

                // 과제 연장이 있는 경우
                if (homeworkNode.item(5).getNodeName().equals("taskextend")) {
                    pumpedIndex = 1;
                }

                int homeworkOrderNum = Integer.parseInt(homeworkNode.item(0).getAttributes().item(0).getTextContent());

                String homeworkName = homeworkNode.item(2).getAttributes().item(0).getTextContent();
                String homeworkStartTime = homeworkNode.item(3).getAttributes().item(0).getTextContent();
                String homeworkEndTime = homeworkNode.item(4).getAttributes().item(0).getTextContent();
                String currentHomeworkStatus = homeworkNode.item(5 + pumpedIndex).getAttributes().item(0).getTextContent();
                String homeworkExtendEndTime = (pumpedIndex == 0) ? SharedConstant.EMPTY_STRING : homeworkNode.item(5).getAttributes().item(0).getTextContent();

                int submitStudentNum = Integer.parseInt(homeworkNode.item(7 + pumpedIndex).getAttributes().item(0).getTextContent());
                int totalStudentNum = Integer.parseInt(homeworkNode.item(8 + pumpedIndex).getAttributes().item(0).getTextContent());

                boolean currentSubmitStatus = homeworkNode.item(12 + pumpedIndex).getAttributes().item(0).getTextContent().equals("제출");
                boolean isOpenTask = homeworkNode.item(6 + pumpedIndex).getAttributes().item(0).getTextContent().equals("Y");

                // For Logging.
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 번호 : " + homeworkOrderNum);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 이름 : " + homeworkName);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 시작 시간 : " + homeworkStartTime);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 종료 시간 : " + homeworkEndTime);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 연장 종료 시간 : " + homeworkExtendEndTime);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 진행 상황 : " + currentHomeworkStatus);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 제출 학생 수 : " + submitStudentNum);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제를 수행하는 총 학생 수 : " + totalStudentNum);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 제출 여부 : " + currentSubmitStatus);
//                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 공개 여부 : " + isOpenTask);
                // TODO 너무 긴 생성자를 고쳐야 함
                homeworkList.add(new Homework(homeworkName, lecture, homeworkStartTime, homeworkEndTime, homeworkExtendEndTime, currentSubmitStatus, isOpenTask, currentHomeworkStatus, submitStudentNum, totalStudentNum, homeworkOrderNum));
            }
        } catch (ParserConfigurationException e) {
            Log.e(LOG_TAG, "Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (IOException | SAXException e) {
            Log.e(LOG_TAG, "IO 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (XPathExpressionException e) {
            Log.e(LOG_TAG, "XPath Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        }

        return homeworkList;
    }

    /**
     * 주어진 Xml 내용으로부터 특정 과제의 상세 내용을 가져옵니다.
     * @param homeworkContentXmlContent Request를 요청한 결과가 담겨있는 Xml을 가리킵니다.
     * @return 해당 Xml로부터 가져올 수 있는 과제 내용을 담고있는 객체입니다.
     */
    public String parseHomeworkContent(String homeworkContentXmlContent) {
        try {
            // Encoding 재조정 작업.
            homeworkContentXmlContent = new String(homeworkContentXmlContent.getBytes("ISO_8859_1"));
            //Log.e(LOG_TAG, "HomeworkView Parsing에 전달된 Xml : " + homeworkContentXmlContent);

            InputSource inputSource = new InputSource(new StringReader(homeworkContentXmlContent));
            Document homeworkDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xmlPath = XPathFactory.newInstance().newXPath();

            // 노드 탐색
            String homeworkContent = (String)xmlPath.compile("/map/map[@id='task']/taskContent/@value").evaluate(homeworkDocument, XPathConstants.STRING);
//            Log.e(LOG_TAG, "현재 Parsing 중인 과제의 내용 : " + homeworkContent);
            if(homeworkContent != null) return homeworkContent;
        } catch (ParserConfigurationException e) {
            Log.e(LOG_TAG, "Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (IOException | SAXException e) {
            Log.e(LOG_TAG, "IO 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (XPathExpressionException e) {
            Log.e(LOG_TAG, "XPath Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        }

        return SharedConstant.EMPTY_STRING;
    }

    /**
     * 주어진 Xml 내용으로부터 공지사항의 내용을 가져옵니다.
     * @param noticeXmlContent Request를 요청한 결과가 담겨있는 Xml을 가리킵니다.
     * @return 해당 Xml로부터 가져올 수 있는 공지사항 내용을 담고있는 객체입니다.
     */
    public List<LectureNotice> parseNotice(String noticeXmlContent, Lecture lecture) {
        List<LectureNotice> noticeList = new ArrayList<>();

        try {
            // Encoding 재조정 작업.
            noticeXmlContent = new String(noticeXmlContent.getBytes("ISO_8859_1"));
            //Log.e(LOG_TAG, "Notice Parsing에 전달된 Xml : " + noticeXmlContent);

            InputSource inputSource = new InputSource(new StringReader(noticeXmlContent));
            Document homeworkDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xmlPath = XPathFactory.newInstance().newXPath();

            // 노드 탐색
            NodeList noticeNodeGroup = (NodeList) xmlPath.compile("/map/vector[@id='result']/map[@id]").evaluate(homeworkDocument, XPathConstants.NODESET);

            for (int i = 0; i < noticeNodeGroup.getLength(); i++) {
                NodeList noticeNode = noticeNodeGroup.item(i).getChildNodes();

                String noticeTitle = noticeNode.item(3).getAttributes().item(0).getTextContent();
                String noticeContent = noticeNode.item(4).getAttributes().item(0).getTextContent();
                String noticeAuthor = noticeNode.item(6).getAttributes().item(0).getTextContent();
                String noticeWrittenDate = noticeNode.item(7).getAttributes().item(0).getTextContent();
                int noticeHitCount = Integer.parseInt(noticeNode.item(8).getAttributes().item(0).getTextContent());
                boolean isImportantNotice = noticeNode.item(13).getAttributes().item(0).getTextContent().equals("Y");

                // For Logging.
//                Log.e(LOG_TAG, "현재 Parsing 중인 공지사항의 제목 : " + noticeTitle);
//                Log.e(LOG_TAG, "현재 Parsing 중인 공지사항의 내용 : " + noticeContent);
//                Log.e(LOG_TAG, "현재 Parsing 중인 공지사항의 작성자 : " + noticeAuthor);
//                Log.e(LOG_TAG, "현재 Parsing 중인 공지사항의 작성 날짜 : " + noticeWrittenDate);
//                Log.e(LOG_TAG, "현재 Parsing 중인 공지사항의 조회수 : " + noticeHitCount);
//                Log.e(LOG_TAG, "현재 Parsing 중인 공지사항의 중요 여부 : " + isImportantNotice);
                // TODO 너무 긴 생성자를 고쳐야 함
                noticeList.add(new LectureNotice(noticeTitle, lecture, noticeContent, noticeAuthor, noticeWrittenDate, isImportantNotice, noticeHitCount));
            }

        } catch (ParserConfigurationException e) {
            Log.e(LOG_TAG, "Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (IOException | SAXException e) {
            Log.e(LOG_TAG, "IO 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        } catch (XPathExpressionException e) {
            Log.e(LOG_TAG, "XPath Parsing 중 오류가 발생하였습니다. 다음의 메시지를 참고하세요." + e.getMessage());
        }

        return noticeList;
    }
}