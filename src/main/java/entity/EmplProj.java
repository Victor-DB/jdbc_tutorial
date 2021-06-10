package entity;

public class EmplProj {
    private Long employeedId;
    private Long projectId;

    public EmplProj() {}

    public Long getEmployeedId() {
        return employeedId;
    }

    public void setEmployeedId(Long employeedId) {
        this.employeedId = employeedId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "EmplProj{" +
                "employeedId=" + employeedId +
                ", projectId=" + projectId +
                '}';
    }
}
