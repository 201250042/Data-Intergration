package integration.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("课程")
public class CourseA {
    @XStreamAlias("课程编号")
    private String cno;
    @XStreamAlias("课程名称")
    private String cName;
    @XStreamAlias("学分")
    private String points;
    @XStreamAlias("授课老师")
    private String teacher;
    @XStreamAlias("授课地点")
    private String place;
    @XStreamAlias("共享")
    private String shared;
}
