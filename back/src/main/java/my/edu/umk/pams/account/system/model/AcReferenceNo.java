package my.edu.umk.pams.account.system.model;

/**
 * FORMAT
 * [a] = prefix
 * [b] = long year
 * [c] = short year
 * [d] = long month
 * [e] = short month
 * [f] = long day
 * [g] = short day
 * [h] = long hour  (24)
 * [i] = short hour
 * [j] = sequence
 * reference format = {#a}{#b}{#j} = prefix|year|sequence
 * <p/>
 * Example: REQ-{#b}{#e}: REQ2014000001
 *
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcReferenceNo extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    String getDescription();

    String getPrefix();

    String getSequenceFormat();

    String getReferenceFormat();

    Integer getIncrementValue();

    void setIncrementValue(Integer incrementValue);

    Integer getCurrentValue();

    void setCurrentValue(Integer currentValue);
}
