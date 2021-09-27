package com.marvin.roleservice.rest.exceptionhandler

import com.marvin.roleservice.exception.NotFoundException
import com.marvin.roleservice.rest.dto.ResponseErrorDto

import org.springframework.core.MethodParameter
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import spock.lang.Specification

import static org.springframework.http.HttpStatus.*

class ControllerExceptionHandlerSpec extends Specification {

    ControllerExceptionHandler controllerExceptionHandler = new ControllerExceptionHandler()

    def 'MethodArgumentNotValidException must send a ResponseErrorDto with an error message'() {
        given: 'a MethodArgumentNotValidException'
        String message = 'field1 [must be valid], field2 [must be valid]'

        BindingResult bindingResult = Mock(BindingResult)

        FieldError fieldError1 = Mock(FieldError)
        fieldError1.field >> 'field1'
        fieldError1.defaultMessage >> 'must be valid'

        FieldError fieldError2 = Mock(FieldError)
        fieldError2.field >> 'field2'
        fieldError2.defaultMessage >> 'must be valid'

        bindingResult.fieldErrors >> [fieldError1, fieldError2]

        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(Mock(MethodParameter), bindingResult)

        when: 'Bad Request is captured'
        ResponseEntity<ResponseErrorDto> responseEntity = controllerExceptionHandler.handleBadRequest(methodArgumentNotValidException)

        then: 'ResponseEntity with errors is returned'
        responseEntity.statusCode == BAD_REQUEST
        responseEntity.body.message == message
    }

    def 'NotFoundException must send a ResponseErrorDto with an error message'() {
        given: 'a NotFoundException'
        String message = 'entity not found'
        NotFoundException notFoundException = new NotFoundException(message)

        when: 'Not Found is captured'
        ResponseEntity<ResponseErrorDto> responseEntity = controllerExceptionHandler.handleNotFound(notFoundException)

        then: 'ResponseEntity with errors is returned'
        responseEntity.statusCode == NOT_FOUND
        responseEntity.body.message == message
    }

    def 'HttpMediaTypeNotSupportedException must send a ResponseErrorDto with an error message'() {
        given: 'a HttpMediaTypeNotSupportedException'
        String message = 'Content type \'application/xml\' is not supported'
        HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException = new HttpMediaTypeNotSupportedException(message)

        when: 'Unsupported Media Type is captured'
        ResponseEntity<ResponseErrorDto> responseEntity = controllerExceptionHandler.handleUnsupportedMediaType(httpMediaTypeNotSupportedException)

        then: 'ResponseEntity with errors is returned'
        responseEntity.statusCode == UNSUPPORTED_MEDIA_TYPE
        responseEntity.body.message == message
    }

    def 'Exception inesperada must send a ResponseErrorDto with an error message'() {
        given: 'a Exception'
        String message = 'unexpected error'
        Exception exception = new Exception(message)

        when: 'Internal Server Error is captured'
        ResponseEntity<ResponseErrorDto> responseEntity = controllerExceptionHandler.handleInternalServerError(exception)

        then: 'ResponseEntity with errors is returned'
        responseEntity.statusCode == INTERNAL_SERVER_ERROR
        responseEntity.body.message == message
    }
}
