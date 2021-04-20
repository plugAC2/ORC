package pl.projectorc.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String content;

    @OneToMany
    @JoinColumn(name = "actor_id")
    private Set<Actor> actors;

    @OneToMany
    @JoinColumn(name = "monster_id")
    private Set<Monster> monsters;

    @OneToMany
    @JoinColumn(name = "map_id")
    private Set<Map> maps;
}
