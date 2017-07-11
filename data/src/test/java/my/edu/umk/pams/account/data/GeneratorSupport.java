package my.edu.umk.pams.account.data;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PAMS
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public abstract class GeneratorSupport {

    protected List<String> readSource(String source) throws IOException {
        Resource resource = new ClassPathResource(source);
        List<String> sources = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getURI().getPath()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                sources.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sources;
    }
}
