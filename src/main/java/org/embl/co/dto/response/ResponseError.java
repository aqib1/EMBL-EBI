package org.embl.co.dto.response;

import lombok.Builder;
import lombok.Data;
import org.embl.co.dto.base.Base;
@Data
@Builder
public class ResponseError extends Base {
    private String createdAt;
    private String detailedMessage;
    private int errorCode;
    private String exceptionName;
    private String errorMessage;
}
