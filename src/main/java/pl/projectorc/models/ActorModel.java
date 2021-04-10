package pl.projectorc.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorModel {

    @NotEmpty
    private String name;
}
