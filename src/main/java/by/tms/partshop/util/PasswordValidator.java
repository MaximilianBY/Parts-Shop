package by.tms.partshop.util;

import by.tms.partshop.dto.UserLoginDto;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, UserLoginDto> {

  private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()â€\"[{}]:;',?/*~$^+=<>]).{6,20}$";
  private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

  @Override
  public boolean isValid(UserLoginDto userLoginDto, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(userLoginDto.getPassword());
    return matcher.matches();
  }
}
