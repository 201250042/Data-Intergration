package integration.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
@Data
@XStreamAlias("课程")
public class Course {
    @XStreamAlias("cno")
    private String id;
    private String name;
    private String teacher;
    private String pla;
}

