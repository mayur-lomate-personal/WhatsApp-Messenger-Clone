package com.mayur.authoriser.ResponsePayloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UnAuthorisedResponse {
    private String message;
    private long status;
}
