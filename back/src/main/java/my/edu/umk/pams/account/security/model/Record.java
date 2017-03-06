package my.edu.umk.pams.account.security.model;

public interface Record {

    /**
     * record id
     *
     * @return id
     */
    public Long getId();

    /**
     * record class
     *
     * @return class
     */
    Class getObjectClass();

    /**
     * @param clazz
     */
    void setObjectClass(Class clazz);

    /**
     * transient assignee
     *
     * @return transient assignee
     */
    String getAssignee();

    /**
     * @param assignee
     */
    void setAssignee(String assignee);

    /**
     * transient candidate
     *
     * @return candidate
     */
    public String getCandidate();

    /**
     * @param candidate
     */
    void setCandidate(String candidate);
}
