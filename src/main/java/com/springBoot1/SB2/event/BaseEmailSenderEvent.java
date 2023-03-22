package com.springBoot1.SB2.event;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class BaseEmailSenderEvent {
    protected String from;
    protected String to;
    protected String subject;
    protected String text;
    protected boolean html;

}
