package thymeleafexamples.layouts.task;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @Version
    private Calendar created = Calendar.getInstance();

    public Calendar getDueTo() {
        return dueTo;
    }

    public void setDueTo(Calendar dueTo) {
        this.dueTo = dueTo;
    }

    private Calendar dueTo;

    public Task() {
    }

    public Task(String title, String text, String date) {
        this.title = title;
        this.text = text;
        this.dueTo = toCalendar(date);
    }

    private Calendar toCalendar(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
