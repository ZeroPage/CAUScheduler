package org.zeropage.causcheduler.util;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
import java.util.Locale;

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
            NodeList allLectureInfoGroup = (NodeList) xPath.compile("/map/vector[@id='result']/map[@id]").evaluate(lectureListDoc, XPathConstants.NODESET);

            //Log.e(LOG_TAG, "전체 Parsing 대상이 되는 노드 개수 : " + allLectureListNode.getLength());

            for (int i = 0; i < allLectureInfoGroup.getLength(); i++) {
                Element oneLectureInfo = (Element) allLectureInfoGroup.item(i);

                // 하나의 강의 정보에 대해 Parsing.
                int lectureNum = Integer.parseInt(getFirstAttributeValueByTagName(oneLectureInfo, "lectureno"));
                int sectionNum = Integer.parseInt(getFirstAttributeValueByTagName(oneLectureInfo, "lecturenum"));
                String lectureName = getFirstAttributeValueByTagName(oneLectureInfo, "lecturenamenum");
                String professorName = getFirstAttributeValueByTagName(oneLectureInfo, "profname");
                String studyPeriod = getFirstAttributeValueByTagName(oneLectureInfo, "studydate");
                String lectureDeptName = getFirstAttributeValueByTagName(oneLectureInfo, "subjectname");

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
//            Log.e(LOG_TAG, "MealList Parsing에 전달된 Xml : " + mealXmlContent);

            InputSource inputSource = new InputSource(new StringReader(mealXmlContent));
            Document mealInfoDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xPath = XPathFactory.newInstance().newXPath();

            // today value
            String today = (String) xPath.compile("/map/today/@value").evaluate(mealInfoDoc, XPathConstants.STRING);

            // TODO : 날짜가 이상하게 뽑혀나오는 이유는...?
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            Date date = dateFormat.parse(today);

//            Log.e(LOG_TAG, "현재 Parsing 중인 식단의 원본 날짜 : " + today);
//            Log.e(LOG_TAG, "현재 Parsing 중인 식단의 날짜 : " + dateFormat.format(date));

            // vector id = result를 담는 노드 탐색
            NodeList totalMealInfoGroup = (NodeList) xPath.compile("/map/vector[@id='result']/map[@id]").evaluate(mealInfoDoc, XPathConstants.NODESET);

//            Log.e(LOG_TAG, "전체 Parsing 대상이 되는 노드 개수 : " + mealInfoList.getLength());

            for (int i = 0; i < totalMealInfoGroup.getLength(); i++) {
                Element oneMealInformation = (Element) totalMealInfoGroup.item(i);

                // 구체적인 Parsing 시작.
                String mealName = getFirstAttributeValueByTagName(oneMealInformation, "menunm");
                String mealTime = getFirstAttributeValueByTagName(oneMealInformation, "tm");
                int mealPrice = Integer.parseInt(getFirstAttributeValueByTagName(oneMealInformation, "amt").split(" ")[0]);

                String[] mealContent = getFirstAttributeValueByTagName(oneMealInformation, "menunm1").split("<br>");
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
            NodeList allHomeworkInfoGroup = (NodeList) xmlPath.compile("/map/vector[@id='tasklist']/map[@id]").evaluate(homeworkDocument, XPathConstants.NODESET);

            for (int i = 0; i < allHomeworkInfoGroup.getLength(); i++) {
                Element oneHomeworkInformation = (Element) allHomeworkInfoGroup.item(i);

                int homeworkOrderNum = Integer.parseInt(getFirstAttributeValueByTagName(oneHomeworkInformation, "taskno"));
                String homeworkName = getFirstAttributeValueByTagName(oneHomeworkInformation, "tasktitle");
                String homeworkStartTime = getFirstAttributeValueByTagName(oneHomeworkInformation, "taskstart");
                String homeworkEndTime = getFirstAttributeValueByTagName(oneHomeworkInformation, "taskend");
                String currentHomeworkStatus = getFirstAttributeValueByTagName(oneHomeworkInformation, "taskendyn");
                String homeworkExtendEndTime = getFirstAttributeValueByTagName(oneHomeworkInformation, "taskextend");

                int submitStudentNum = Integer.parseInt(getFirstAttributeValueByTagName(oneHomeworkInformation, "submit"));
                int totalStudentNum = Integer.parseInt(getFirstAttributeValueByTagName(oneHomeworkInformation, "total"));

                boolean currentSubmitStatus = getFirstAttributeValueByTagName(oneHomeworkInformation, "submitstatetxt").equals("제출");
                boolean isOpenTask = getFirstAttributeValueByTagName(oneHomeworkInformation, "taskopenyn").equals("Y");

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
            Log.e(LOG_TAG, "HomeworkView Parsing에 전달된 Xml : " + homeworkContentXmlContent);

            InputSource inputSource = new InputSource(new StringReader(homeworkContentXmlContent));
            Document homeworkDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xmlPath = XPathFactory.newInstance().newXPath();

            // 노드 탐색
            NodeList contentNodes = (NodeList) xmlPath.compile("/map/map[@id='task']/taskContent[@value]").evaluate(homeworkDocument, XPathConstants.NODESET);

            if (contentNodes != null && contentNodes.getLength() > 0) {
                String homeworkContent = contentNodes.item(0).getAttributes().item(0).getTextContent();
                Log.e(LOG_TAG, "현재 Parsing 중인 과제의 내용 : " + homeworkContent);
                return homeworkContent;
            }

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
                Element oneNoticeInformation = (Element) noticeNodeGroup.item(i);

                // 구체적인 Parsing 시작.
                String noticeTitle = getFirstAttributeValueByTagName(oneNoticeInformation, "boardtitle");
                String noticeContent = getFirstAttributeValueByTagName(oneNoticeInformation, "boardintro");
                String noticeAuthor = getFirstAttributeValueByTagName(oneNoticeInformation, "username");
                String noticeWrittenDate = getFirstAttributeValueByTagName(oneNoticeInformation, "boarddate");
                int noticeHitCount = Integer.parseInt(getFirstAttributeValueByTagName(oneNoticeInformation, "boardhit"));
                boolean isImportantNotice = getFirstAttributeValueByTagName(oneNoticeInformation, "boardcheck").equals("Y");

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

    private String getFirstAttributeValueByTagName(Element nodeElem, String tagName)
    {
        try {
            return nodeElem.getElementsByTagName(tagName).item(0).getAttributes().item(0).getTextContent();
        } catch (NullPointerException e) {
            return SharedConstant.EMPTY_STRING;
        }
    }
}