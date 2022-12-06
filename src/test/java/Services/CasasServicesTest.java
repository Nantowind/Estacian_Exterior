package Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CasasServicesTest {

  CasasServices casasServices;

  @BeforeEach
  public void setup() {
    casasServices = new CasasServices();
  }

  @Test
  @Disabled("Necesitar refactorizacion")
  public void shouldSearchForDateAndCountry() throws Exception {
    casasServices.searchForDateAndCountry();
  }

  @Test
  public void shouldDoSomething() throws Exception {
    System.out.println("Something!!!");
  }

}
