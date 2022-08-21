package by.tms.partshop.util;

import by.tms.partshop.dto.UserDto;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, UserDto> {

  private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]"
      + "+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  @Override
  public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(userDto.getEmail());
    return matcher.matches();
  }
}
