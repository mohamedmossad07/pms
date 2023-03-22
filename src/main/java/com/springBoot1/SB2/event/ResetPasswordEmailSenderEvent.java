package com.springBoot1.SB2.event;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class ResetPasswordEmailSenderEvent extends BaseEmailSenderEvent  {
        public ResetPasswordEmailSenderEvent(String from, String to, String subject, String text, boolean html) {
        super(from, to, subject, text, html);
    }
}
