package themes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import themes.Theme;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ThemeController {
    private final List<Theme> themes = new ArrayList<>();

    //curl -X POST -H "Content-Type: application/json" -d '{"name":"hi"}' http://localhost:8080/themes
    @PostMapping("themes")
    public ResponseEntity<Void> addTheme(@RequestBody Theme theme) {
        themes.add(theme);
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
    public ResponseEntity<Theme> getMessage(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(themes.get(index));
    }


}