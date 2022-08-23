package by.tms.partshop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

  private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()â€\"[{}]:;',?~$^=<>]).{8,20}$";
  private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }
}
