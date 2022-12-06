package Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CasasServicesTest {

  CasasServices casasServices;

  @BeforeEach
  public void setup() {
    casasServices = new CasasServices();
  }

  @Test
  public void shouldSearchForDateAndCountry() throws Exception {
    casasServices.searchForDateAndCountry();
  }

}
