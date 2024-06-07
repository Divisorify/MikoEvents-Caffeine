package com.example.MikoEvents.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;

	protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST)
				.message("Validation error")
				.errors(ex.getBindingResult()
						.getFieldErrors()
						.stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage)
						.toList())
				.build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected @NotNull ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST)
				.message("Constraint violation")
				.errors(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())
				.build(), HttpStatus.BAD_REQUEST);
	}

	protected @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
																		   HttpHeaders headers,
																		   HttpStatus status,
																		   WebRequest request) {
		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST)
				.message("Invalid JSON")
				.errors(Collections.singletonList(ex.getMessage()))
				.build(), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(ApiOperationErrorException.class)
	public ResponseEntity<ApiOperationErrorResponse> handleApiOperationErrorException(ApiOperationErrorException e) {

		HttpStatus httpStatus = Optional.ofNullable(e.getHttpStatus()).orElse(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(httpStatus.value())
				.status(httpStatus)
				.message(e.getMessage())
				.errors(null)
				.build()
				, httpStatus);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiOperationErrorResponse> handleFeedbackNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(HttpStatus.NOT_FOUND.value())
				.status(HttpStatus.NOT_FOUND)
				.message(e.getMessage())
				.errors(null)
				.build()
				, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<ApiOperationErrorResponse> handlePropertyReferenceException(PropertyReferenceException e) {
		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST)
				.message(messageSource.getMessage("error.mapping.properties", null, LocaleContextHolder.getLocale()))
				.errors(null)
				.build()
				, HttpStatus.BAD_REQUEST);
	}

	// Handler for root JPA exception
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ApiOperationErrorResponse> handleFeedbackAddingDeniedException(DataAccessException e) {
		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.message(messageSource.getMessage("error.persistence.layer", null, LocaleContextHolder.getLocale()))
				.errors(List.of(e.getCause().toString()))
				.build()
				, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomValidationException.class)
	public ResponseEntity<ApiOperationErrorResponse> handleBusinessValidationException(CustomValidationException e) {
		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST)
				.message(e.getMessage())
				.errors(null)
				.build()
				, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ApiOperationErrorResponse> handleMaxUploadSizeFileException(MaxUploadSizeExceededException e) {
		return new ResponseEntity<>(ApiOperationErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST)
				.message("Maksymalny dozwolony rozmiar pliku to 10MB.")
				.errors(List.of(e.getMessage()))
				.build(),
				HttpStatus.BAD_REQUEST);
	}
}
