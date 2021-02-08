package topic.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import topic.history.repository.TopicsRepository;

import java.io.IOException;
import java.util.List;

@RestController
public class TopicHistoryController {
  @Autowired
  TopicsRepository topicsRepository;

  @RequestMapping("/list")
  public List<String> list() throws IOException {
    return topicsRepository.listTopics();
  }

  @RequestMapping("/addTopic")
  public void addTopic(@RequestParam String topic) throws IOException {
    topicsRepository.addTopic(topic);
  }
}
