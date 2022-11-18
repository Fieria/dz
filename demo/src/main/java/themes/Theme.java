package themes;

import themes.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Theme {
    List<Comment> comments= new ArrayList<Comment>();
    String name;

    public Theme(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addComment(int index, Comment comment) {
        this.comments.add(index, comment);
    }

    public void updateComment(int index, Comment comment) {
        this.comments.remove(index);
        this.comments.add(index, comment);
    }

    public void deleteComment(int index) {
        this.comments.remove(index);
    }

    public String getName() {
        return name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString()
    {
        return "theme:" + name + "\n" + "comments:" + "\n" + comments.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
