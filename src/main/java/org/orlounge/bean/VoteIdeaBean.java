package org.orlounge.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "idea_vote")
@Getter
@Setter
@EqualsAndHashCode(of = {"ideaId", "userId", "date"})
@NoArgsConstructor
public class VoteIdeaBean {

    public VoteIdeaBean(Integer ideaId, Integer userId, Date date) {
        this.ideaId = ideaId;
        this.userId = userId;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "idea_id")
    private Integer ideaId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "dt")
    private Date date;


}
