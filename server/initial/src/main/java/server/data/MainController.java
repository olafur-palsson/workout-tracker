package server.data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController    // This means that this class is a Controller
public class MainController {

    @CrossOrigin
    @RequestMapping( value = "/**", method = RequestMethod.OPTIONS )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
