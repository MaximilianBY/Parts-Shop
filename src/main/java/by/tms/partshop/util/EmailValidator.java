package by.tms.partshop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

  private static final String EMAIL_PATTERN = "^(?=.{1,20}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]"
      + "+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }
}
