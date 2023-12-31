package integration.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
@Data
@XStreamAlias("课程")
public class CourseC {
    private String cno;
    private String cnm;
    private Long ctm;
    private Long cpt;
    private String tec;
    private String pla;
    private String share;
}

