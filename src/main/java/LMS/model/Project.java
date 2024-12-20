package LMS.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 100000)
    private String description;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  start_date;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Publication> publications = new ArrayList<>();
}

