package udit.android.twininterviewtask.model;

public class Food {

    private String id;
    private String title;
    private String image_url;
    private boolean is_recent;
    private boolean is_tfy;

    public Food(String id, String title, String image_url, boolean is_recent, boolean is_tfy) {
        this.id = id;
        this.title = title;
        this.image_url = image_url;
        this.is_recent = is_recent;
        this.is_tfy = is_tfy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isIs_recent() {
        return is_recent;
    }

    public void setIs_recent(boolean is_recent) {
        this.is_recent = is_recent;
    }

    public boolean isIs_tfy() {
        return is_tfy;
    }

    public void setIs_tfy(boolean is_tfy) {
        this.is_tfy = is_tfy;
    }

    @Override
    public String toString() {
        return "food{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", image_url='" + image_url + '\'' +
                ", is_recent=" + is_recent +
                ", is_tfy=" + is_tfy +
                '}';
    }
}
