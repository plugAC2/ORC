package pl.projectorc.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorModel {

    private Long id = 0L;
    @NotEmpty
    private String name;
}
