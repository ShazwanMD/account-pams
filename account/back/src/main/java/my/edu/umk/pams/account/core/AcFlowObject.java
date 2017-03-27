package my.edu.umk.pams.account.core;

/**
 * @author canang technologies
 * @since 4/2/2016.
 */
public interface AcFlowObject extends AcMetaObject {
    /**
     * get flow data
     *
     * @return
     */
    AcFlowdata getFlowdata();

    /**
     * set flow data
     *
     * @param flowdata
     */
    void setFlowdata(AcFlowdata flowdata);

}
