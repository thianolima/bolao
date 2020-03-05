package br.com.bolao.handlers;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.bolao.dtos.ErroDto;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ErroDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {			
		List<ErroDto> erros = new ArrayList<>();		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			erros.add(ErroDto.builder().campo(e.getField()).mensagem(mensagem).exception(ex.getMessage()).build());
		});
		return erros;
	}
	
	//TODO Revisar esses tratamento pois o mesmos nao estao tratando o erro 
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseStatus(code = HttpStatus.FORBIDDEN)	
	public ErroDto handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		return ErroDto.builder().mensagem("Registro Duplicado !").exception(ex.getMessage()).build();
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)	
	public ErroDto handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {
		return ErroDto.builder().mensagem("Registro não encontrado !").exception(ex.getMessage()).build();
	}
	
	@ExceptionHandler({AccessDeniedException.class})
	@ResponseStatus(code = HttpStatus.FORBIDDEN)	
	public ErroDto handleAccessDeniedException(AccessDeniedException ex) {
		return ErroDto.builder().mensagem("Acesso Negado !").exception(ex.getMessage()).build();
	}

	@ExceptionHandler({RuntimeException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)	
	public ErroDto handleRunTimeExcepion(RuntimeException ex) {
		return ErroDto.builder().mensagem("Erro desconhecido !").exception(ex.getMessage()).build();
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)	
	public ErroDto handleIllegalArgumentException(IllegalArgumentException ex) {
		return ErroDto.builder().mensagem("Json com parametros errado !").exception(ex.getMessage()).build();
	}
	
}
