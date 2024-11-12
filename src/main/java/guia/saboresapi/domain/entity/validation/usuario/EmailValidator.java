package guia.saboresapi.domain.entity.validation.usuario;

public class EmailValidator {
  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

  public static boolean isValid(String email) {
    return email.matches(EMAIL_REGEX);
  }

  private EmailValidator() {
  }
}
