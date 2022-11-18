package themes;

public class Comment {
    String text;

    public Comment(String text){
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString()
    {
        return text;
    }
}