import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Project {
    private Long id;
    private String projectName;
    private List<Project> projects;

    public static void main(String[] args) {
        Project project1 = new Project();
        Project project2 = new Project();
        project1.setProjects(Arrays.asList(project2));
        project2.setProjects(Arrays.asList(project1));
        System.out.println(project1.hashCode());
    }
}
