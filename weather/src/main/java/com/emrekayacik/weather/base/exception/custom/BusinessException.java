package com.emrekayacik.weather.base.exception.custom;

import com.emrekayacik.weather.base.exception.BaseErrorMessage;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@EqualsAndHashCode(callSuper=false)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@RequiredArgsConstructor
@MappedSuperclass
public class BusinessException extends RuntimeException{

    private final BaseErrorMessage baseErrorMessage;
}