package my.edu.umk.pams.account.common.model;


import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * STUDY MODE :-
 * UNDECIDED
 * FULL-TIME
 * PART-TIME
 *
 */
public interface AcStudyMode extends AcMetaObject {

    String getCode();

    void setCode(String code);

    String getPrefix();

    void setPrefix(String prefix);

    String getDescriptionMs();

    void setDescriptionMs(String descriptionMs);

	String getDescriptionEn();

	void setDescriptionEn(String descriptionEn);

}
