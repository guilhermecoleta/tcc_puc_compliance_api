package puc.tcc.logistics;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.mockito.MockitoAnnotations;

public class SampleBaseTestCase {

       private AutoCloseable closeable;

       @Before("")
       public void openMocks() {
           closeable = MockitoAnnotations.openMocks(this);
       }

       @After("")
       public void releaseMocks() throws Exception {
           closeable.close();
       }
   }