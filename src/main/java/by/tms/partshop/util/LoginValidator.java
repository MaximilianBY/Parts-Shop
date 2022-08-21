package by.tms.partshop.util;

import by.tms.partshop.dto.UserLoginDto;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<LoginConstraint, UserLoginDto> {

  private static final String LOGIN_PATTERN = "^[a-zA-Z0-9_-]{5,15}$";
  private static final Pattern pattern = Pattern.compile(LOGIN_PATTERN);

  @Override
  public boolean isValid(UserLoginDto value, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(value.getLogin());
    return matcher.matches();
  }
}
