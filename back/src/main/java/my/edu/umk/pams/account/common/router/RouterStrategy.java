package my.edu.umk.pams.account.common.router;

import java.util.List;

public interface RouterStrategy {

	public List<String> findRegistererCandidates();

	public List<String> findVerifierCandidates();

}