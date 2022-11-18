package themes;

import com.example.demo.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import themes.Theme;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ThemeController {
    private final List<Theme> themes = new ArrayList<>();

    //curl -L -X POST 'http://localhost:8080/themes' -H 'Content-Type: application/json' --data-raw '{"name":"***", "comments": ["*", "**"]}'
    @PostMapping("themes")
    public ResponseEntity<Void> addTheme(@RequestBody Theme theme) {
        themes.add(theme);
        return ResponseEntity.accepted().build();
    }

    //curl -L -X PUT 'http://localhost:8080/themes/{index}' -H 'Content-Type: application/json' --data-raw '{ "name":"***", "comments": ["*", "**"]}'
    @PutMapping("themes/{index}")
    public ResponseEntity<Void> updateTheme(
            @PathVariable("index") Integer i,
            @RequestBody Theme theme) {
        themes.remove((int) i);
        themes.add(i, theme);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE http://localhost:8080/themes/{index}
    @DeleteMapping("themes/{index}")
    public ResponseEntity<Void> deleteTheme(@PathVariable("index") Integer index) {
        themes.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl http://localhost:8080/themes
    @GetMapping("themes")
    public ResponseEntity<List<Theme>> getAllThemes() {
        return ResponseEntity.ok(themes);
    }

    //curl http://localhost:8080/themes/names
    @GetMapping("themes/names")
    public ResponseEntity<List<String>> getAllThemesNames() {
        List<String> names = new ArrayList<>();
        for (Theme theme : themes) {
            names.add(theme.getName());
        }
        return ResponseEntity.ok(names);
    }

    //curl http://localhost:8080/themes/count
    @GetMapping("themes/count")
    public ResponseEntity<Integer> getThemesCount() {
        return ResponseEntity.ok(themes.size());
    }

    //curl -X DELETE http://localhost:8080/themes
    @DeleteMapping("themes")
    public ResponseEntity<Void> deleteAll() {
        themes.clear();
        return ResponseEntity.noContent().build();
    }

    //curl http://localhost:8080/themes/{index}
    @GetMapping("themes/{index}")
    public ResponseEntity<Theme> getTheme(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(themes.get(index));
    }





    //curl -L -X PUT 'http://localhost:8080/themes/{index}/comments' -H 'Content-Type: application/json' --data-raw 'comment'
    @PutMapping("themes/{index}/comments")
    public ResponseEntity<Void> addComment(@RequestBody Comment comment, @PathVariable("index") Integer index) {
        themes.get(index).addComment(comment);
        return ResponseEntity.accepted().build();
    }

    //curl -L -X POST 'http://localhost:8080/themes/{index1}/comments/{index2}' -H 'Content-Type: application/json' --data-raw 'comment'
    @PostMapping("themes/{index1}/comments/{index2}")
    public ResponseEntity<Void> updateComment(
            @PathVariable("index1") Integer i1, @PathVariable("index2") Integer i2, @RequestBody Comment comment) {
        themes.get(i1).updateComment(i2, comment);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE 'http://localhost:8080/themes/{index1}/comments/{index2}'
    @DeleteMapping("themes/{index1}/comments/{index2}")
    public ResponseEntity<Void> deleteComment(@PathVariable("index1") Integer i1, @PathVariable("index2") Integer i2) {
        themes.get(i1).deleteComment(i2);
        return ResponseEntity.noContent().build();
    }

    //curl http://localhost:8080/themes/{index1}/comments
    @GetMapping("themes/{index}/comments")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable("index") Integer i) {
        return ResponseEntity.ok(themes.get(i).getComments());
    }





}