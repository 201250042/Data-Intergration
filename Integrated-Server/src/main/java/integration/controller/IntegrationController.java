package integration.controller;

import integration.pojo.Course;
import integration.pojo.CourseA;
import integration.pojo.CourseC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@SuppressWarnings("all")
@RequestMapping("/integration")
public class IntegrationController {
    private final String url = "http://";
//    private final String url1 = "192.168.43.58:8082";
//    private final String url2 = "192.168.43.220:8083";
//    private final String url3 = "192.168.43.26:8084";
    private final String url1 = "localhost:8082";
    private final String url2 = "localhost:8083";
    private final String url3 = "localhost:8084";
    private final String askShareurl = "/courses/searchByCno?courseXml={value}";
    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/AaskShare")
    public String AaskShare(String courseXml){
        Class<?>[] classes = new Class[]{CourseC.class, Course.class, CourseA.class};
        System.out.println(courseXml);
        String urlC = url + url3 + askShareurl;
        String response = restTemplate.getForObject(urlC, String.class, courseXml);
        if (!response.contains("<null>")) {
            System.out.println(response);
            int index1 = response.indexOf("<ctm>");
            int index2 = response.indexOf("<cpt>");
            response = response.substring(0, index1) + response.substring(index2, response.length());
            System.out.println(response);
            response = response.replace("cno", "课程编号");
            response = response.replace("cnm", "课程名称");
            response = response.replace("cpt", "学分");
            response = response.replace("tec", "授课老师");
            response = response.replace("pla", "授课地点");
            response = response.replace("share", "共享");
            System.out.println(response);
        } else {
            String urlB = url + url2 + askShareurl;
            response = restTemplate.getForObject(urlB, String.class, courseXml);
            System.out.println(response);
            int index1 = response.indexOf("<课时>");
            int index2 = response.indexOf("<学分>");
            response = response.substring(0, index1) + response.substring(index2, response.length());
            response = response.replace("编号", "课程编号");
            response = response.replace("名称", "课程名称");
            response = response.replace("老师", "授课老师");
            response = response.replace("地点", "授课地点");
            System.out.println(response);
        }

        return response;
    }

    @GetMapping("/BaskShare")
    public String BaskShare(String courseXml){
        Class<?>[] classes = new Class[]{CourseC.class, Course.class};
        System.out.println(courseXml);
        String urlC = url + url3 + askShareurl;
        String response = restTemplate.getForObject(urlC, String.class, courseXml);
        if (!response.contains("<null>")) {
            System.out.println(response);
            response = response.replace("cno", "课程编号");
            response = response.replace("cnm", "课程名称");
            response = response.replace("ctm", "课时");
            response = response.replace("cpt", "学分");
            response = response.replace("tec", "授课老师");
            response = response.replace("pla", "授课地点");
            response = response.replace("share", "共享");
            System.out.println(response);
        } else {
            String urlA = url + url1 + askShareurl;
            response = restTemplate.getForObject(urlA, String.class, courseXml);
            System.out.println(response);
            response = response.replace("课程编号", "编号");
            response = response.replace("课程名称", "名称");
            response = response.replace("授课老师", "老师");
            response = response.replace("授课地点", "地点");
        }
        return response;
    }

    @GetMapping("/CaskShare")
    public String CaskShare(String courseXml){
        Class<?>[] classes = new Class[]{CourseC.class, Course.class};
        System.out.println(courseXml);
        String urlA = url + url1 + askShareurl;
        String response = restTemplate.getForObject(urlA, String.class, courseXml);
        if (!response.contains("<null>")) {
            System.out.println(response);
            response = response.replace("课程编号", "cno");
            response = response.replace("课程名称", "cnm");
            response = response.replace("学分", "cpt");
            response = response.replace("授课老师", "tec");
            response = response.replace("授课地点", "pla");
            response = response.replace("共享", "share");
            System.out.println(response);
        } else {
            String urlB = url + url2 + askShareurl;
            response = restTemplate.getForObject(urlB, String.class, courseXml);
            System.out.println(response);
            response = response.replace("编号", "cno");
            response = response.replace("名称", "cnm");
            response = response.replace("课时", "ctm");
            response = response.replace("学分", "cpt");
            response = response.replace("老师", "tec");
            response = response.replace("地点", "pla");
            response = response.replace("共享", "share");
            System.out.println(response);
        }
        return response;
    }

    @GetMapping("/showAll")
    public String showAll(){
        String urlA_students = url + url1 + "/students"; //查询A学院学生全部信息
        String urlA_courses = url + url1 + "/courses"; //查询A学院课程全部信息
        String urlA_course_selection = url + url1 + "/courses_selection"; //查询A学院选课表全部信息
        String urlB_students = url + url2 + "/students"; //查询B学院学生全部信息
        String urlB_courses = url + url2 + "/courses"; //查询B学院课程全部信息
        String urlB_course_selection = url + url2 + "/courses_selection"; //查询B学院选课表全部信息
        String urlC_students = url + url3 + "/students";; //查询C学院学生全部信息
        String urlC_courses = url + url3 + "/courses"; //查询C学院课程全部信息
        String urlC_course_selection = url + url3 + "/courses_selection"; //查询C学院选课表全部信息
        String A_students = restTemplate.getForObject(urlA_students, String.class);
        String A_courses = restTemplate.getForObject(urlA_courses, String.class);
        String A_course_selection = restTemplate.getForObject(urlA_course_selection, String.class);
        String B_students = restTemplate.getForObject(urlB_students, String.class);
        String B_courses = restTemplate.getForObject(urlB_courses, String.class);
        String B_course_selection = restTemplate.getForObject(urlB_course_selection, String.class);
        String C_students = restTemplate.getForObject(urlC_students, String.class);
        String C_courses = restTemplate.getForObject(urlC_courses, String.class);
        String C_course_selection = restTemplate.getForObject(urlC_course_selection, String.class);
        String result = A_students + A_courses + A_course_selection + B_students + B_courses + B_course_selection + C_students + C_courses + C_course_selection;
        return result;
//        return C_students + C_courses + C_course_selection;
    }

    @GetMapping("/AshowAllcourses")
    public String AshowAllcourses(){
        String urlA_courses = url + url1 + "/courses"; //查询A学院课程全部信息
        String urlB_courses = url + url2 + "/courses"; //查询B学院课程全部信息
        String urlC_courses = url + url3 + "/courses"; //查询C学院课程全部信息
        String A_courses = restTemplate.getForObject(urlA_courses, String.class);
        int index1 = A_courses.indexOf("</list>");
        A_courses = A_courses.substring(0, index1);
        String B_courses = restTemplate.getForObject(urlB_courses, String.class);
        int Bindex0 = B_courses.indexOf("<课程>");
        int Bindex1 = B_courses.indexOf("</list>");
        B_courses = B_courses.substring(Bindex0, Bindex1);
        B_courses = B_courses.replace("编号", "课程编号");
        B_courses = B_courses.replace("名称", "课程名称");
        B_courses = B_courses.replace("老师", "授课老师");
        B_courses = B_courses.replace("地点", "授课地点");
        B_courses = B_courses.replace("课时", "课时");
        String C_courses = restTemplate.getForObject(urlC_courses, String.class);
        int Cindex0 = C_courses.indexOf("<课程>");
        C_courses = C_courses.substring(Cindex0, C_courses.length());
        C_courses = C_courses.replace("cno", "课程编号");
        C_courses = C_courses.replace("cnm", "课程名称");
        C_courses = C_courses.replace("ctm", "课时");
        C_courses = C_courses.replace("cpt", "学分");
        C_courses = C_courses.replace("tec", "授课老师");
        C_courses = C_courses.replace("pla", "授课地点");
        C_courses = C_courses.replace("share", "共享");
        String result = A_courses + B_courses + C_courses;
        System.out.println(result);
        return result;
    }

    @GetMapping("/BshowAllcourses")
    public String BshowAllcourses(){
        String urlA_courses = url + url1 + "/courses"; //查询A学院课程全部信息
        String urlB_courses = url + url2 + "/courses"; //查询B学院课程全部信息
        String urlC_courses = url + url3 + "/courses"; //查询C学院课程全部信息
        String A_courses = restTemplate.getForObject(urlA_courses, String.class);
        int Aindex1 = A_courses.indexOf("</list>");
        A_courses = A_courses.substring(0, Aindex1);
        A_courses = A_courses.replace("课程编号", "编号");
        A_courses = A_courses.replace("课程名称", "名称");
        A_courses = A_courses.replace("授课老师", "老师");
        A_courses = A_courses.replace("授课地点", "地点");
        String B_courses = restTemplate.getForObject(urlB_courses, String.class);
        int Bindex0 = B_courses.indexOf("<课程>");
        int Bindex1 = B_courses.indexOf("</list>");
        B_courses = B_courses.substring(Bindex0, Bindex1);
        String C_courses = restTemplate.getForObject(urlC_courses, String.class);
        int Cindex0 = C_courses.indexOf("<课程>");
        C_courses = C_courses.substring(Cindex0, C_courses.length());
        C_courses = C_courses.replace("cno", "课程编号");
        C_courses = C_courses.replace("cnm", "课程名称");
        C_courses = C_courses.replace("ctm", "课时");
        C_courses = C_courses.replace("cpt", "学分");
        C_courses = C_courses.replace("tec", "授课老师");
        C_courses = C_courses.replace("pla", "授课地点");
        C_courses = C_courses.replace("share", "共享");
        String result = A_courses + B_courses + C_courses;
        return result;
    }

    @GetMapping("/CshowAllcourses")
    public String CshowAllcourses(){
        String urlA_courses = url + url1 + "/courses"; //查询A学院课程全部信息
        String urlB_courses = url + url2 + "/courses"; //查询B学院课程全部信息
        String urlC_courses = url + url3 + "/courses"; //查询C学院课程全部信息
        String A_courses = restTemplate.getForObject(urlA_courses, String.class);
        int Aindex1 = A_courses.indexOf("</list>");
        A_courses = A_courses.substring(0, Aindex1);
        A_courses = A_courses.replace("课程编号", "cno");
        A_courses = A_courses.replace("课程名称", "cnm");
        A_courses = A_courses.replace("学分", "cpt");
        A_courses = A_courses.replace("授课老师", "tec");
        A_courses = A_courses.replace("授课地点", "pla");
        A_courses = A_courses.replace("共享", "share");
        String B_courses = restTemplate.getForObject(urlB_courses, String.class);
        int Bindex0 = B_courses.indexOf("<课程>");
        int Bindex1 = B_courses.indexOf("</list>");
        B_courses = B_courses.substring(Bindex0, Bindex1);
        B_courses = B_courses.replace("编号", "cno");
        B_courses = B_courses.replace("名称", "cnm");
        B_courses = B_courses.replace("课时", "ctm");
        B_courses = B_courses.replace("学分", "cpt");
        B_courses = B_courses.replace("老师", "tec");
        B_courses = B_courses.replace("地点", "pla");
        B_courses = B_courses.replace("共享", "share");
        String C_courses = restTemplate.getForObject(urlC_courses, String.class);
        int Cindex1 = C_courses.indexOf("<课程>");
        C_courses = C_courses.substring(Cindex1, C_courses.length());
        String result = A_courses + B_courses + C_courses;
        return result;
    }

    @GetMapping("/CaskCourse")
    public String CaskCourses(String snoXml){
        ArrayList<String> Cnos = new ArrayList<>();
        String urlC = url + url3 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String response = restTemplate.getForObject(urlC, String.class, snoXml);
        System.out.println(response);
        if (response.contains("<选课>")){
            int index1 = response.indexOf("<选课>");
            int index2 = response.indexOf("</list>");
            response = response.substring(index1, index2);
            String[] courses_selection = response.split("</选课>");
            System.out.println(courses_selection);
            String[] cnosC = new String[courses_selection.length];
            for (int i=0;i<courses_selection.length;i++) {
                cnosC[i] = "<课程>";
                int index3 = response.indexOf("<cno>");
                int index4 = response.indexOf("<sno>");
                cnosC[i] += courses_selection[i].substring(index3, index4);
                cnosC[i] += "</课程>";
                System.out.println(cnosC[i]);
                Cnos.add(cnosC[i]);
            }
        }

        String urlB = url + url2 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String responseB = restTemplate.getForObject(urlB, String.class, snoXml);
        System.out.println(responseB);
        if (responseB.contains("<选课>")) {
            int index1 = responseB.indexOf("<选课>");
            int index2 = responseB.indexOf("</list>");
            responseB = responseB.substring(index1, index2);
            String[] courses_selectionB = responseB.split("</选课>");
            System.out.println(courses_selectionB);
            String[] cnosB = new String[courses_selectionB.length];
            for (int i=0;i<courses_selectionB.length;i++) {
                cnosB[i] = "<课程>";
                int index3 = responseB.indexOf("<课程编号>");
                int index4 = responseB.indexOf("<得分>");
                cnosB[i] += courses_selectionB[i].substring(index3, index4);
                cnosB[i] += "</课程>";
                System.out.println(cnosB[i]);
                Cnos.add(cnosB[i]);
            }
        }

        String urlA = url + url1 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String responseA = restTemplate.getForObject(urlA, String.class, snoXml);
        System.out.println(responseA);
        if (responseA.contains("<选课>")) {
            int index1 = responseA.indexOf("<选课>");
            int index2 = responseA.indexOf("</list>");
            responseA = responseA.substring(index1, index2);
            String[] courses_selectionA = responseA.split("</选课>");
            System.out.println(courses_selectionA);
            String[] cnosA = new String[courses_selectionA.length];
            for (int i=0;i<courses_selectionA.length;i++) {
                cnosA[i] = "<课程>";
                int index3 = responseA.indexOf("<课程编号>");
                int index4 = responseA.indexOf("<成绩>");
                cnosA[i] += courses_selectionA[i].substring(index3, index4);
                cnosA[i] += "</课程>";
                System.out.println(cnosA[i]);
                Cnos.add(cnosA[i]);
            }
        }

        String courses = "<?xml version=\"1.0\" ?><list>";
        for (int i=0;i<Cnos.size();i++){
            if (Cnos.get(i).contains("<课程编号>1")) {
                String urla = url + url1 + "/courses/searchByCno?courseXml={value}";
                String A_courses = restTemplate.getForObject(urla, String.class, Cnos.get(i));
                int Aindex1 = A_courses.indexOf("<课程>");
                A_courses = A_courses.substring(Aindex1, A_courses.length());
                A_courses = A_courses.replace("课程编号", "cno");
                A_courses = A_courses.replace("课程名称", "cnm");
                A_courses = A_courses.replace("学分", "cpt");
                A_courses = A_courses.replace("授课老师", "tec");
                A_courses = A_courses.replace("授课地点", "pla");
                A_courses = A_courses.replace("共享", "share");
                System.out.println(A_courses);
                courses += A_courses;
            }
            else if (Cnos.get(i).contains("<课程编号>2")) {
                String course = Cnos.get(i).replace("课程编号", "编号");
                Cnos.set(i, course);
                String urlb = url + url2 + "/courses/searchByCno?courseXml={value}";
                String B_courses = restTemplate.getForObject(urlb, String.class, Cnos.get(i));
                int Bindex1 = B_courses.indexOf("<课程>");
                B_courses = B_courses.substring(Bindex1, B_courses.length());
                B_courses = B_courses.replace("编号", "cno");
                B_courses = B_courses.replace("名称", "cnm");
                B_courses = B_courses.replace("课时", "ctm");
                B_courses = B_courses.replace("学分", "cpt");
                B_courses = B_courses.replace("老师", "tec");
                B_courses = B_courses.replace("地点", "pla");
                B_courses = B_courses.replace("共享", "share");
                System.out.println(B_courses);
                courses += B_courses;
            }
            else {
                String urlc = url + url3 + "/courses/searchByCno?courseXml={value}";
                String C_courses = restTemplate.getForObject(urlc, String.class, Cnos.get(i));
                int Cindex1 = C_courses.indexOf("<课程>");
                C_courses = C_courses.substring(Cindex1, C_courses.length());
                System.out.println(C_courses);
                courses += C_courses;
            }
        }
        courses += "</list>";
        System.out.println(courses);
        return courses;
    }

    @GetMapping("/AaskCourse")
    public String AaskCourses(String snoXml){
        ArrayList<String> Cnos = new ArrayList<>();

        String urlC = url + url3 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String response = restTemplate.getForObject(urlC, String.class, snoXml);
        System.out.println(response);
        if (response.contains("<选课>")){
            int index1 = response.indexOf("<选课>");
            int index2 = response.indexOf("</list>");
            response = response.substring(index1, index2);
            String[] courses_selection = response.split("</选课>");
            System.out.println(courses_selection);
            String[] cnosC = new String[courses_selection.length];
            for (int i=0;i<courses_selection.length;i++) {
                cnosC[i] = "<课程>";
                int index3 = response.indexOf("<cno>");
                int index4 = response.indexOf("<sno>");
                cnosC[i] += courses_selection[i].substring(index3, index4);
                cnosC[i] += "</课程>";
                System.out.println(cnosC[i]);
                Cnos.add(cnosC[i]);
            }
        }

        String urlB = url + url2 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String responseB = restTemplate.getForObject(urlB, String.class, snoXml);
        System.out.println(responseB);
        if (responseB.contains("<选课>")) {
            int index1 = responseB.indexOf("<选课>");
            int index2 = responseB.indexOf("</list>");
            responseB = responseB.substring(index1, index2);
            String[] courses_selectionB = responseB.split("</选课>");
            System.out.println(courses_selectionB);
            String[] cnosB = new String[courses_selectionB.length];
            for (int i=0;i<courses_selectionB.length;i++) {
                cnosB[i] = "<课程>";
                int index3 = responseB.indexOf("<课程编号>");
                int index4 = responseB.indexOf("<得分>");
                cnosB[i] += courses_selectionB[i].substring(index3, index4);
                cnosB[i] += "</课程>";
                System.out.println(cnosB[i]);
                Cnos.add(cnosB[i]);
            }
        }

        String urlA = url + url1 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String responseA = restTemplate.getForObject(urlA, String.class, snoXml);
        System.out.println(responseA);
        if (responseA.contains("<选课>")) {
            int index1 = responseA.indexOf("<选课>");
            int index2 = responseA.indexOf("</list>");
            responseA = responseA.substring(index1, index2);
            String[] courses_selectionA = responseA.split("</选课>");
            System.out.println(courses_selectionA);
            String[] cnosA = new String[courses_selectionA.length];
            for (int i=0;i<courses_selectionA.length;i++) {
                cnosA[i] = "<课程>";
                int index3 = responseA.indexOf("<课程编号>");
                int index4 = responseA.indexOf("<成绩>");
                cnosA[i] += courses_selectionA[i].substring(index3, index4);
                cnosA[i] += "</课程>";
                System.out.println(cnosA[i]);
                Cnos.add(cnosA[i]);
            }
        }

        String courses = "<?xml version=\"1.0\" ?><list>";
        for (int i=0;i<Cnos.size();i++){
            if (Cnos.get(i).contains("<课程编号>1")) {
                String urla = url + url1 + "/courses/searchByCno?courseXml={value}";
                String A_courses = restTemplate.getForObject(urla, String.class, Cnos.get(i));
                int Aindex1 = A_courses.indexOf("<课程>");
                A_courses = A_courses.substring(Aindex1, A_courses.length());
                System.out.println(A_courses);
                courses += A_courses;
            }
            else if (Cnos.get(i).contains("<课程编号>2")) {
                String course = Cnos.get(i).replace("课程编号", "编号");
                Cnos.set(i, course);
                String urlb = url + url2 + "/courses/searchByCno?courseXml={value}";
                String B_courses = restTemplate.getForObject(urlb, String.class, Cnos.get(i));
                int Bindex0 = B_courses.indexOf("<课程>");
                int Bindex1 = B_courses.indexOf("<课时>");
                int Bindex2 = B_courses.indexOf("<学分>");
                B_courses = B_courses.substring(Bindex0, Bindex1) + B_courses.substring(Bindex2, B_courses.length());
                B_courses = B_courses.replace("编号", "课程编号");
                B_courses = B_courses.replace("名称", "课程名称");
                B_courses = B_courses.replace("老师", "授课老师");
                B_courses = B_courses.replace("地点", "授课地点");
                System.out.println(B_courses);
                courses += B_courses;
            }
            else {
                String urlc = url + url3 + "/courses/searchByCno?courseXml={value}";
                String C_courses = restTemplate.getForObject(urlc, String.class, Cnos.get(i));
                int Cindex0 = C_courses.indexOf("<课程>");
                int Cindex1 = C_courses.indexOf("<ctm>");
                int Cindex2 = C_courses.indexOf("<cpt>");
                C_courses = C_courses.substring(Cindex0, Cindex1) + C_courses.substring(Cindex2, C_courses.length());
                C_courses = C_courses.replace("cno", "课程编号");
                C_courses = C_courses.replace("cnm", "课程名称");
                C_courses = C_courses.replace("cpt", "学分");
                C_courses = C_courses.replace("tec", "授课老师");
                C_courses = C_courses.replace("pla", "授课地点");
                C_courses = C_courses.replace("share", "共享");
                System.out.println(C_courses);
                courses += C_courses;
            }
        }
        courses += "</list>";
        System.out.println("!!!" + courses);
        return courses;
    }

    @GetMapping("/BaskCourse")
    public String BaskCourses(String snoXml){
        ArrayList<String> Cnos = new ArrayList<>();

        String urlC = url + url3 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String response = restTemplate.getForObject(urlC, String.class, snoXml);
        System.out.println(response);
        if (response.contains("<选课>")){
            int index1 = response.indexOf("<选课>");
            int index2 = response.indexOf("</list>");
            response = response.substring(index1, index2);
            String[] courses_selection = response.split("</选课>");
            System.out.println(courses_selection);
            String[] cnosC = new String[courses_selection.length];
            for (int i=0;i<courses_selection.length;i++) {
                cnosC[i] = "<课程>";
                int index3 = response.indexOf("<cno>");
                int index4 = response.indexOf("<sno>");
                cnosC[i] += courses_selection[i].substring(index3, index4);
                cnosC[i] += "</课程>";
                System.out.println(cnosC[i]);
                Cnos.add(cnosC[i]);
            }
        }

        String urlB = url + url2 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String responseB = restTemplate.getForObject(urlB, String.class, snoXml);
        System.out.println(responseB);
        if (responseB.contains("<选课>")) {
            int index1 = responseB.indexOf("<选课>");
            int index2 = responseB.indexOf("</list>");
            responseB = responseB.substring(index1, index2);
            String[] courses_selectionB = responseB.split("</选课>");
            System.out.println(courses_selectionB);
            String[] cnosB = new String[courses_selectionB.length];
            for (int i=0;i<courses_selectionB.length;i++) {
                cnosB[i] = "<课程>";
                int index3 = responseB.indexOf("<课程编号>");
                int index4 = responseB.indexOf("<得分>");
                cnosB[i] += courses_selectionB[i].substring(index3, index4);
                cnosB[i] += "</课程>";
                System.out.println(cnosB[i]);
                Cnos.add(cnosB[i]);
            }
        }

        String urlA = url + url1 + "/courses_selection/searchBySno?courses_selectionXml={value}";
        String responseA = restTemplate.getForObject(urlA, String.class, snoXml);
        System.out.println(responseA);
        if (responseB.contains("<选课>")) {
            int index1 = responseA.indexOf("<选课>");
            int index2 = responseA.indexOf("</list>");
            responseA = responseA.substring(index1, index2);
            String[] courses_selectionA = responseA.split("</选课>");
            System.out.println(courses_selectionA);
            String[] cnosA = new String[courses_selectionA.length];
            for (int i=0;i<courses_selectionA.length;i++) {
                cnosA[i] = "<课程>";
                int index3 = responseA.indexOf("<课程编号>");
                int index4 = responseA.indexOf("<成绩>");
                cnosA[i] += courses_selectionA[i].substring(index3, index4);
                cnosA[i] += "</课程>";
                System.out.println(cnosA[i]);
                Cnos.add(cnosA[i]);
            }
        }

        String courses = "<?xml version=\"1.0\" ?><list>";
        for (int i=0;i<Cnos.size();i++){
            if (Cnos.get(i).contains("<课程编号>1")) {
                String urla = url + url1 + "/courses/searchByCno?courseXml={value}";
                String A_courses = restTemplate.getForObject(urla, String.class, Cnos.get(i));
                int Aindex1 = A_courses.indexOf("<课程>");
                A_courses = A_courses.substring(Aindex1, A_courses.length());
                A_courses = A_courses.replace("课程编号", "编号");
                A_courses = A_courses.replace("课程名称", "名称");
                A_courses = A_courses.replace("授课老师", "老师");
                A_courses = A_courses.replace("授课地点", "地点");
                System.out.println(A_courses);
                courses += A_courses;
            }
            else if (Cnos.get(i).contains("<课程编号>2")) {
                String course = Cnos.get(i).replace("课程编号", "编号");
                Cnos.set(i, course);
                String urlb = url + url2 + "/courses/searchByCno?courseXml={value}";
                String B_courses = restTemplate.getForObject(urlb, String.class, Cnos.get(i));
                int Bindex0 = B_courses.indexOf("<课程>");
                B_courses = B_courses.substring(Bindex0, B_courses.length());
                System.out.println(B_courses);
                courses += B_courses;
            }
            else {
                String urlc = url + url3 + "/courses/searchByCno?courseXml={value}";
                String C_courses = restTemplate.getForObject(urlc, String.class, Cnos.get(i));
                int Cindex0 = C_courses.indexOf("<课程>");
                C_courses = C_courses.substring(Cindex0, C_courses.length());
                C_courses = C_courses.replace("cno", "编号");
                C_courses = C_courses.replace("cnm", "名称");
                C_courses = C_courses.replace("ctm", "课时");
                C_courses = C_courses.replace("cpt", "学分");
                C_courses = C_courses.replace("tec", "老师");
                C_courses = C_courses.replace("pla", "地点");
                C_courses = C_courses.replace("share", "共享");
                System.out.println(C_courses);
                courses += C_courses;
            }
        }
        courses += "</list>";
        System.out.println("??????" + courses);
        return courses;
    }

    @GetMapping("/httpTest")
    public void httpTest(String studentXml, String courses_selectionXml, String curr, String transTo) {
        if (curr.equals("a")) {
            if (transTo.equals("b")) {
                int index1 = studentXml.indexOf("<关联账户>");
                int index2 = studentXml.indexOf("</关联账户>");
                studentXml = studentXml.substring(0, index1) + studentXml.substring(index2+1, studentXml.length());
                studentXml = studentXml.replace("院系", "专业");
                String urlb1 = url + url2 + "/students/add?studentXml={value}";
                restTemplate.getForObject(urlb1, String.class, studentXml);
                courses_selectionXml = courses_selectionXml.replace("学号", "学生编号");
                courses_selectionXml = courses_selectionXml.replace("成绩", "得分");
                String urlb2 = url + url2 + "/courses_selection/add?courses_selectionXml={value}";
                restTemplate.getForObject(urlb2, String.class, courses_selectionXml);
            }
            else {
                int index1 = studentXml.indexOf("<关联账户>");
                int index2 = studentXml.indexOf("</关联账户>");
                studentXml = studentXml.substring(0, index1) + studentXml.substring(index2+1, studentXml.length());
                studentXml = studentXml.replace("学号", "sno");
                studentXml = studentXml.replace("姓名", "snm");
                studentXml = studentXml.replace("性别", "sex");
                studentXml = studentXml.replace("院系", "sde");
                String urlc1 = url + url3 + "/students/add?studentXml={value}";
                restTemplate.getForObject(urlc1, String.class, studentXml);
                courses_selectionXml = courses_selectionXml.replace("学号", "sno");
                courses_selectionXml = courses_selectionXml.replace("课程编号", "cno");
                courses_selectionXml = courses_selectionXml.replace("成绩", "grd");
                String urlc2 = url + url3 + "/courses_selection/add?courses_selectionXml={value}";
                restTemplate.getForObject(urlc2, String.class, courses_selectionXml);
            }
        }
        else if (curr.equals("b")) {
            if (transTo.equals("a")) {
                int index1 = studentXml.indexOf("<密码>");
                int index2 = studentXml.indexOf("</密码>");
                studentXml = studentXml.substring(0, index1) + studentXml.substring(index2+1, studentXml.length());
                studentXml = studentXml.replace("专业", "院系");
                String urla1 = url + url1 + "/students/add?studentXml={value}";
                restTemplate.getForObject(urla1, String.class, studentXml);
                courses_selectionXml = courses_selectionXml.replace("学生编号", "学号");
                courses_selectionXml = courses_selectionXml.replace("得分", "成绩");
                String urla2 = url + url1 + "/courses_selection/add?courses_selectionXml={value}";
                restTemplate.getForObject(urla2, String.class, courses_selectionXml);
            }
            else {
                studentXml = studentXml.replace("学号", "sno");
                studentXml = studentXml.replace("姓名", "snm");
                studentXml = studentXml.replace("性别", "sex");
                studentXml = studentXml.replace("专业", "sde");
                studentXml = studentXml.replace("密码", "pwd");
                String urlc1 = url + url3 + "/students/add?studentXml={value}";
                restTemplate.getForObject(urlc1, String.class, studentXml);
                courses_selectionXml = courses_selectionXml.replace("课程编号", "cno");
                courses_selectionXml = courses_selectionXml.replace("学生编号", "sno");
                courses_selectionXml = courses_selectionXml.replace("得分", "grd");
                String urlc2 = url + url3 + "/courses_selection/add?courses_selectionXml={value}";
                restTemplate.getForObject(urlc2, String.class, courses_selectionXml);
            }
        }
        else {
            if (transTo.equals("a")) {
                int index1 = studentXml.indexOf("<pwd>");
                int index2 = studentXml.indexOf("</pwd>");
                studentXml = studentXml.substring(0, index1) + studentXml.substring(index2+1, studentXml.length());
                studentXml = studentXml.replace("sno", "学号");
                studentXml = studentXml.replace("snm", "姓名");
                studentXml = studentXml.replace("sex", "性别");
                studentXml = studentXml.replace("sde", "院系");
                String urla1 = url + url1 + "/students/add?studentXml={value}";
                restTemplate.getForObject(urla1, String.class, studentXml);
                courses_selectionXml = courses_selectionXml.replace("cno", "课程编号");
                courses_selectionXml = courses_selectionXml.replace("sno", "学号");
                courses_selectionXml = courses_selectionXml.replace("grd", "成绩");
                String urla2 = url + url1 + "/courses_selection/add?courses_selectionXml={value}";
                restTemplate.getForObject(urla2, String.class, courses_selectionXml);
            }
            else {
                studentXml = studentXml.replace("sno", "学号");
                studentXml = studentXml.replace("snm", "姓名");
                studentXml = studentXml.replace("sex", "性别");
                studentXml = studentXml.replace("sde", "专业");
                studentXml = studentXml.replace("pwd", "密码");
                String urlb1 = url + url2 + "/students/add?studentXml={value}";
                restTemplate.getForObject(urlb1, String.class, studentXml);
                courses_selectionXml = courses_selectionXml.replace("cno", "课程编号");
                courses_selectionXml = courses_selectionXml.replace("sno", "学生编号");
                courses_selectionXml = courses_selectionXml.replace("grd", "得分");
                String urlb2 = url + url2 + "/courses_selection/add?courses_selectionXml={value}";
                restTemplate.getForObject(urlb2, String.class, courses_selectionXml);
            }
        }
    }

    @GetMapping("/httpTestDelete")
    public void httpTestDelete(String studentXml, String courses_selectionXml, String curr, String transTo) {
        if (curr.equals("a")) {
            if (transTo.equals("b")) {
                courses_selectionXml = courses_selectionXml.replace("学号", "学生编号");
                courses_selectionXml = courses_selectionXml.replace("成绩", "得分");
                String urlb2 = url + url2 + "/courses_selection/delete?courses_selectionXml={value}";
                restTemplate.getForObject(urlb2, String.class, courses_selectionXml);
            }
            else {
                courses_selectionXml = courses_selectionXml.replace("学号", "cno");
                courses_selectionXml = courses_selectionXml.replace("学生编号", "sno");
                courses_selectionXml = courses_selectionXml.replace("成绩", "grd");
                String urlc2 = url + url3 + "/courses_selection/delete?courses_selectionXml={value}";
                restTemplate.getForObject(urlc2, String.class, courses_selectionXml);
            }
        }
        else if (curr.equals("b")) {
            if (transTo.equals("a")) {
                courses_selectionXml = courses_selectionXml.replace("学生编号", "学号");
                courses_selectionXml = courses_selectionXml.replace("得分", "成绩");
                String urla2 = url + url1 + "/courses_selection/delete?courses_selectionXml={value}";
                restTemplate.getForObject(urla2, String.class, courses_selectionXml);
            }
            else {
                courses_selectionXml = courses_selectionXml.replace("课程编号", "cno");
                courses_selectionXml = courses_selectionXml.replace("学生编号", "sno");
                courses_selectionXml = courses_selectionXml.replace("得分", "grd");
                String urlc2 = url + url3 + "/courses_selection/delete?courses_selectionXml={value}";
                restTemplate.getForObject(urlc2, String.class, courses_selectionXml);
            }
        }
        else {
            if (transTo.equals("a")) {
                courses_selectionXml = courses_selectionXml.replace("cno", "课程编号");
                courses_selectionXml = courses_selectionXml.replace("sno", "学号");
                courses_selectionXml = courses_selectionXml.replace("grd", "成绩");
                String urla2 = url + url1 + "/courses_selection/delete?courses_selectionXml={value}";
                restTemplate.getForObject(urla2, String.class, courses_selectionXml);
            }
            else {
                courses_selectionXml = courses_selectionXml.replace("cno", "课程编号");
                courses_selectionXml = courses_selectionXml.replace("sno", "学生编号");
                courses_selectionXml = courses_selectionXml.replace("grd", "得分");
                String urlb2 = url + url2 + "/courses_selection/delete?courses_selectionXml={value}";
                restTemplate.getForObject(urlb2, String.class, courses_selectionXml);
            }
        }
    }

    @GetMapping("/CgetCreditDistribution")
    public List CgetCreditDistribution(){
        String urlC = url + url3 + "/courses/getCreditDistribution";
        List<Integer> response = restTemplate.getForObject(urlC, List.class);//学分设置为3、4、5之一，res[0]为3学分课程数,res[1]为4,res[2]为5
        return response;
    }

    @GetMapping("/AgetCreditDistribution")
    public List AgetCreditDistribution(){
        String urlA = url + url1 + "/courses/getCreditDistribution";
        List<Integer> response = restTemplate.getForObject(urlA, List.class);
        return response;
    }

    @GetMapping("/BgetCreditDistribution")
    public List BgetCreditDistribution(){
        String urlB = url + url2 + "/courses/getCreditDistribution";
        List<Integer> response = restTemplate.getForObject(urlB, List.class);
        return response;
    }

    @GetMapping("/getCreditDistribution")
    public List getCreditDistribution(){
        List<Integer> response = new ArrayList<>();
        String urlA = url + url1 + "/courses/getCreditDistribution";
        List<Integer> responseA = restTemplate.getForObject(urlA, List.class);
        String urlB = url + url2 + "/courses/getCreditDistribution";
        List<Integer> responseB = restTemplate.getForObject(urlB, List.class);
        String urlC = url + url3 + "/courses/getCreditDistribution";
        List<Integer> responseC = restTemplate.getForObject(urlC, List.class);
        for (int i=0;i<responseC.size();i++){
            response.add(responseA.get(i) + responseC.get(i) + responseB.get(i));
        }
        return response;
    }

    @GetMapping("/CgetStudentDistribution")
    public List CgetStudentDistribution(String cno){
        String urlC = url + url3 + "/courses_selection/getStudentDistribution?cno={value}";
        List<Integer> response = restTemplate.getForObject(urlC, List.class, cno);//response[0]为A学院学生数,response[1]为B学院,response[2]为C学院
        return response;
    }

    @GetMapping("/AgetStudentDistribution")
    public List AgetStudentDistribution(String cno){
        String urlA = url + url1 + "/courses_selection/getStudentDistribution?cno={value}";
        List<Integer> response = restTemplate.getForObject(urlA, List.class, cno);//response[0]为A学院学生数,response[1]为B学院,response[2]为C学院
        return response;
    }

    @GetMapping("/BgetStudentDistribution")
    public List BgetStudentDistribution(String cno){
        String urlB = url + url2 + "/courses_selection/getStudentDistribution?cno={value}";
        List<Integer> response = restTemplate.getForObject(urlB, List.class, cno);//response[0]为A学院学生数,response[1]为B学院,response[2]为C学院
        return response;
    }

    @GetMapping("/getStudentDistribution")
    public List getStudentDistribution(String cno){
        List<Integer> response = new ArrayList<>();
        String urlA = url + url1 + "/courses_selection/getStudentDistribution?cno={value}";
        List<Integer> responseA = restTemplate.getForObject(urlA, List.class, cno);//response[0]为A学院学生数,response[1]为B学院,response[2]为C学院
        String urlB = url + url2 + "/courses_selection/getStudentDistribution?cno={value}";
        List<Integer> responseB = restTemplate.getForObject(urlB, List.class, cno);//response[0]为A学院学生数,response[1]为B学院,response[2]为C学院
        String urlC = url + url3 + "/courses_selection/getStudentDistribution?cno={value}";
        List<Integer> responseC = restTemplate.getForObject(urlC, List.class, cno);//response[0]为A学院学生数,response[1]为B学院,response[2]为C学院
        for (int i=0;i<responseC.size();i++){
            response.add(responseA.get(i) + responseC.get(i) + responseB.get(i));
        }
        return response;
    }

    @GetMapping("/CgetGradeDistribution")
    public List CgetGradeDistribution(String cno){
        String urlC = url + url3 + "/courses_selection/getGradeDistribution?cno={value}";
        List<Integer> response = restTemplate.getForObject(urlC, List.class, cno);//成绩设置3段，【0，60），【60，90），【90，100】，res[0]为【0，60）,res[1]为【60，90）,res[2]为【90，100】
        return response;
    }

    @GetMapping("/AgetGradeDistribution")
    public List AgetGradeDistribution(String cno){
        String urlA = url + url1 + "/courses_selection/getGradeDistribution?cno={value}";
        List<Integer> response = restTemplate.getForObject(urlA, List.class, cno);//成绩设置3段，【0，60），【60，90），【90，100】，res[0]为【0，60）,res[1]为【60，90）,res[2]为【90，100】
        return response;
    }

    @GetMapping("/BgetGradeDistribution")
    public List BgetGradeDistribution(String cno){
        String urlB = url + url2 + "/courses_selection/getGradeDistribution?cno={value}";
        List<Integer> response = restTemplate.getForObject(urlB, List.class, cno);//成绩设置3段，【0，60），【60，90），【90，100】，res[0]为【0，60）,res[1]为【60，90）,res[2]为【90，100】
        return response;
    }

    @GetMapping("/getGradeDistribution")
    public List getGradeDistribution(String cno){
        List<Integer> response = new ArrayList<>();
        String urlA = url + url1 + "/courses_selection/getGradeDistribution?cno={value}";
        List<Integer> responseA = restTemplate.getForObject(urlA, List.class, cno);
        String urlB = url + url2 + "/courses_selection/getGradeDistribution?cno={value}";
        List<Integer> responseB = restTemplate.getForObject(urlB, List.class, cno);
        String urlC = url + url3 + "/courses_selection/getGradeDistribution?cno={value}";
        List<Integer> responseC = restTemplate.getForObject(urlC, List.class, cno);
        for (int i=0;i<responseC.size();i++){
            response.add(responseA.get(i) + responseC.get(i) + responseB.get(i));
        }
        return response;
    }

    @GetMapping("/CgetMostPopularCourse")
    public List CgetMostPopularCourse(){
        String urlC = url + url3 + "/courses_selection/getMostPopularCourse";
        List<String> response = restTemplate.getForObject(urlC, List.class);//课程名：人数
        int maxIndex = findIndexOfMaxNumber(response);
        response.add(response.get(maxIndex));
        return response;
    }

    @GetMapping("/AgetMostPopularCourse")
    public List AgetMostPopularCourse(){
        String urlA = url + url1 + "/courses_selection/getMostPopularCourse";
        List<String> response = restTemplate.getForObject(urlA, List.class);//课程名：人数
        int maxIndex = findIndexOfMaxNumber(response);
        response.add(response.get(maxIndex));
        return response;
    }

    @GetMapping("/BgetMostPopularCourse")
    public List BgetMostPopularCourse(){
        String urlB = url + url2 + "/courses_selection/getMostPopularCourse";
        List<String> response = restTemplate.getForObject(urlB, List.class);//课程名：人数
        int maxIndex = findIndexOfMaxNumber(response);
        response.add(response.get(maxIndex));
        return response;
    }

    @GetMapping("/getMostPopularCourse")
    public List getMostPopularCourse(){
        List<String> response = new ArrayList<>();
        String urlA = url + url1 + "/courses_selection/getMostPopularCourse";
        List<String> responseA = restTemplate.getForObject(urlA, List.class);//课程名：人数
        for (int i=0;i<responseA.size();i++){
            response.add(responseA.get(i));
        }
        String urlB = url + url2 + "/courses_selection/getMostPopularCourse";
        List<String> responseB = restTemplate.getForObject(urlB, List.class);//课程名：人数
        for (int i=0;i<responseB.size();i++){
            response.add(responseB.get(i));
        }
        String urlC = url + url3 + "/courses_selection/getMostPopularCourse";
        List<String> responseC = restTemplate.getForObject(urlC, List.class);//课程名：人数
        for (int i=0;i<responseC.size();i++){
            response.add(responseC.get(i));
        }
        int maxIndex = findIndexOfMaxNumber(response);
        response.add(response.get(maxIndex));
        return response;
    }

    public int findIndexOfMaxNumber(List<String> list) {
        int maxIndex = -1; // 初始化最大数字的位置索引为-1
        int maxNumber = Integer.MIN_VALUE; // 初始化最大数字为最小整数值
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            int colonIndex = str.indexOf(":");
            if (colonIndex != -1 && colonIndex < str.length() - 1) {
                String numberStr = str.substring(colonIndex + 1);
                int number = Integer.parseInt(numberStr);
                if (number > maxNumber) {
                    maxNumber = number;
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }
}