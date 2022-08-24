package by.tms.partshop.util;

import by.tms.partshop.dto.UserLoginDto;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<LoginConstraint, String> {

  private static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{5,20}$";
  private static final Pattern pattern = Pattern.compile(LOGIN_PATTERN);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }
}
