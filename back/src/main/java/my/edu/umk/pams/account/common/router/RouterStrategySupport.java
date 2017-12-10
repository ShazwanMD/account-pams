package my.edu.umk.pams.account.common.router;
import java.util.List;
public abstract class RouterStrategySupport implements RouterStrategy {

    @Override
    public List<String> findRegistererCandidates() {
        return null;
    }
    @Override
    public List<String> findVerifierCandidates() {
        return null;
    }

}
