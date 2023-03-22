package com.springBoot1.SB2.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailToSupplierEvent extends BaseEmailSenderEvent  {
    public SendEmailToSupplierEvent(String from, String to, String subject, String text, boolean html) {
        super(from, to, subject, text, html);
    }
}
