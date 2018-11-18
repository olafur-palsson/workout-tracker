package server.data.history;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.data.history.HistoryEntity;
import server.data.history.HistoryRepository;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class HistoryController {
  
  @Autowired
  private HistoryRepository historyRepository;

  @CrossOrigin
  @GetMapping(path = "/addHistory")
  public @ResponseBody Long addNewHistory(
  @RequestParam(required = false) Long id,
  // Preference of ArrayList<> over the obvious hashmap since ArrayList is slice-able and holds
  // also information about the last added exercises.
  @RequestParam(required = false) ArrayList<Long> routineIds,
  @RequestParam(required = false) ArrayList<Long> timeStamps
  ) {
    HistoryEntity h = new HistoryEntity();
    if(id != null)        h.setId(id);
    h = historyRepository.save(h);
    return h.getId();
  }

  @CrossOrigin
  @GetMapping(path = {"/oneHistory", "/userEnabled/oneHistory"})
  public @ResponseBody HistoryEntity getOneHistory(
  @RequestParam Long id
  ) {
    return historyRepository.findOne(id);
  }

  @CrossOrigin
  @GetMapping(path = "/removeHistory")
  public @ResponseBody String removeHistory(
  @RequestParam Long id
  ) {
    historyRepository.delete(id);
    return "Deleted";
  }
}
