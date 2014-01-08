package thymeleafexamples.layouts.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("ErrorLog");
	/**
	 * Handle exceptions thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error/general");
        Throwable rootCause = Throwables.getRootCause(exception);
        modelAndView.addObject("errorMessage", rootCause);
        LOGGER.error(rootCause.toString(), exception);
		return modelAndView;
	}
}