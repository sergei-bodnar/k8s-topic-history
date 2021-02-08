package topic.history.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class TopicsRepository {
  private final Path topicsRepositoryPath;

  public TopicsRepository(@Value("${config.topics.repository.path}") String fileLocation) throws IOException {
    topicsRepositoryPath = Paths.get(fileLocation);
    init(fileLocation);
  }

  public List<String> listTopics() throws IOException {
    return Files.lines(topicsRepositoryPath)
        .collect(Collectors.toList());
  }

  public void addTopic(String topic) throws IOException {
    Files.write(topicsRepositoryPath, List.of(topic), StandardOpenOption.APPEND);
  }

  private void init(String fileLocation) throws IOException {
    var file = new File(fileLocation);
    var createFile = file.createNewFile();
    log.info("createFile: {}", createFile);
  }
}
