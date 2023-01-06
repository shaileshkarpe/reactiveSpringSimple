package com.crossasyst.reactivespring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest extends Mono<PersonRequest> {
    private String firstname;
    private String lastname;
    private String dob;


    @Override
    public void subscribe(CoreSubscriber<? super PersonRequest> coreSubscriber) {

    }
}
