package serviteca.st.modelo.Dto;

import lombok.Builder;

@Builder
public record MailBody (String to, String subject, String text) {}