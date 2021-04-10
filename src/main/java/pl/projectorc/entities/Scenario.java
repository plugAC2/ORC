package pl.projectorc.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String content;
    @ManyToMany
    private Set<Actor> actors;
    @ManyToMany
    private Set<Monster> monsters;
    @ManyToMany
    private Set<Map> maps;
}
