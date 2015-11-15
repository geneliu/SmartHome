package ml.smart_ideas.smarthome.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "Elementi")
public class Element extends Model{

    @Column(name = "Prostorija")
    private Prostorija prostorija;
}
